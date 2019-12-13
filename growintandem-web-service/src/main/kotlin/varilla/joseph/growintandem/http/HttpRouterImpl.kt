package varilla.joseph.growintandem.http

import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import org.koin.core.KoinComponent

class HttpRouterImpl(private val vertx : Vertx) : HttpRouter, KoinComponent {

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


    return router
  }

  override suspend fun getPlantHandler() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override suspend fun getPlantsHandler() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
