package varilla.joseph.growintandem.http

import io.vertx.core.Vertx
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Route
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import kotlinx.coroutines.launch
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.koin.core.KoinComponent
import varilla.joseph.growintandem.utils.http.sendAsJSONWithStatusCode
import varilla.joseph.growintandem.utils.models.Plant
import kotlin.coroutines.CoroutineContext

class HttpRouterImpl(private val vertx : Vertx,
                     private val coroutineContext :CoroutineContext) : HttpRouter, KoinComponent {
  private val coroutineScope = CoroutineScope(coroutineContext)

  override suspend fun getRouter(): Router {
    val router = Router.router(vertx)

    // Add body parsing capability to router
    router.route().handler(BodyHandler.create())

    // API v1 base
    val apiBase1 = "/api/v1"

    // Default Route
    router.get("$apiBase1")
      .handler{ routingContext: RoutingContext ->
        val response = routingContext.response()
        response.putHeader("content-type", "text/plain")
        response.end("Hello from Vert.x")
      }


      router.get("$apiBase1/plant").coroutineHandler(this::getPlantsHandler)
      router.get("$apiBase1/plant/:id").coroutineHandler(this::getPlantHandler)
    return router
  }

  override suspend fun getPlantsHandler(event :RoutingContext) {
    val plant = Plant("123", "Money Tree", 14).toJsonObject()
    val plants = listOf<JsonObject>(plant, plant, plant)
    val msg = Json.encodePrettily(plants)

    val response = event.response()
    response.sendAsJSONWithStatusCode(msg, 200)
  }

  override suspend fun getPlantHandler(event :RoutingContext) {
    val plant = Plant("123", "Money Tree", 14).toJsonObject()
    val msg = Json.encodePrettily(plant)
    event.response().sendAsJSONWithStatusCode(msg, 200)
  }

  // This is needed to make coroutines work. Don't worry about how it works for now.
  private fun Route.coroutineHandler(fn: suspend (RoutingContext) -> Unit) {
      handler { ctx ->
        coroutineScope
          .launch(ctx.vertx().dispatcher()) {
          try {
            fn(ctx)
          } catch (e: Throwable) {
            ctx.fail(e)
          }
        }
      }
    }



}
