package varilla.joseph.growintandem

import io.vertx.core.Vertx
import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject
import org.koin.core.module.Module
import varilla.joseph.growintandem.http.HttpRouter
import varilla.joseph.growintandem.modules.application.VertxModule
import varilla.joseph.growintandem.modules.http.HttpRouterModules

class MainVerticle : CoroutineVerticle(), KoinComponent {

  override suspend fun start() {
    this.coroutineContext
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

  // This is needed to make coroutines work. Don't worry about how it works for now.
  private fun Route.coroutineHandler(fn: suspend (RoutingContext) -> Unit) {
    handler { ctx ->
      launch(ctx.vertx().dispatcher()) {
        try {
          fn(ctx)
        } catch (e: Throwable) {
          ctx.fail(e)
        }
      }
    }
  }
}


