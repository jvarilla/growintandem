package varilla.joseph.growintandem

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle

class MainVerticle : CoroutineVerticle() {

  override suspend fun start() {
    val vertx = this.vertx

    val httpServer = vertx.createHttpServer()
    val router = Router.router(vertx)

    // Add body parsing capability to router
    router.route().handler(BodyHandler.create())

    // API v1 base
    val apiBase1 = "/api/v1"

    // Default Route
    router.get("$apiBase1/")
      .handler{ routingContext: RoutingContext ->
        val response = routingContext.response()
        response.putHeader("content-type", "text/plain")
        response.end("Hello from Vert.x")
      }

      httpServer.requestHandler(router)
       .listen(7777)

      println("Verticle Running")
    }
}
