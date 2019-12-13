package varilla.joseph.growintandem

import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject
import org.koin.core.module.Module
import varilla.joseph.growintandem.http.HttpRouter
import varilla.joseph.growintandem.modules.application.VertxModule
import varilla.joseph.growintandem.modules.http.HttpRouterModules

class MainVerticle : CoroutineVerticle(), KoinComponent {

  override suspend fun start() {

    // Declare modules and build dependency trees
    startKoin {
      modules(listOf<Module>(VertxModule, HttpRouterModules))
    }

    // Inject the vertx context
    val vertx by inject<Vertx>()

    // Create the httpServer
    val httpServer = vertx.createHttpServer()

    // Inject the httpRouter
    val httpRouter by inject<HttpRouter>()

    // Get the router
    val router = httpRouter.getRouter() // Router.router(vertx)

    // Start the http server with the router
    httpServer.requestHandler(router)
       .listen(7777)

      println("Verticle Running")
    }
}
