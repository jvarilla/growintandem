package varilla.joseph.growintandem

import io.vertx.core.Vertx
import io.vertx.core.cli.annotations.CLIConfigurator.inject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import varilla.joseph.growintandem.http.HttpRouter
import varilla.joseph.growintandem.modules.http.HttpRouterModules

fun main() {

  GlobalScope.launch{

  }
  val vertx = Vertx.vertx()
  vertx.deployVerticle("varilla.joseph.growintandem.MainVerticle")
}
