package varilla.joseph.growintandem

import io.vertx.core.Vertx
import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.CoroutineScope
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
    CoroutineScope(this.coroutineContext)


    // Create the httpServer
    val httpServer = vertx.createHttpServer()

    // Inject the httpRouter

    val httpRouter by inject<HttpRouter>()

    // Get the router
    val router = httpRouter.getRouter()

    // Start the http server with the router
    httpServer.requestHandler(router)
       .listen(7777)

      println("Verticle Running")
    }


}


