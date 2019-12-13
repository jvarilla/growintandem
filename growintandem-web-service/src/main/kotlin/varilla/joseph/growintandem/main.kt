package varilla.joseph.growintandem

import io.vertx.core.Verticle
import io.vertx.core.Vertx
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject
import org.koin.core.module.Module
import varilla.joseph.growintandem.modules.application.VertxModule
import varilla.joseph.growintandem.modules.http.HttpRouterModules


class Main :KoinComponent{
  fun main() {
      // Declare modules and build dependency trees
      startKoin {
        modules(listOf<Module>(VertxModule, HttpRouterModules))
      }

      // Inject the vertx context
      val vertx by inject<Vertx>()
      val mainVerticle by inject<Verticle>()
      vertx.deployVerticle(mainVerticle)
  }

}

fun main() {
  val main = Main()
  main.main()
}

