package varilla.joseph.growintandem

import io.vertx.core.Vertx
import io.vertx.core.cli.annotations.CLIConfigurator.inject
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import varilla.joseph.growintandem.http.HttpRouter
import varilla.joseph.growintandem.modules.http.HttpRouterModules

fun main() {
  val vertx = Vertx.vertx()
  vertx.deployVerticle("varilla.joseph.growintandem.MainVerticle")
}
