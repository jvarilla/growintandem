package varilla.joseph.growintandem

import io.vertx.core.Vertx

fun main() {
  val vertx = Vertx.vertx()
  vertx.deployVerticle("varilla.joseph.growintandem.MainVerticle")
}
