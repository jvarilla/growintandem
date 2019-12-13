package varilla.joseph.growintandem.modules.application


import io.vertx.core.Vertx
import org.koin.dsl.module

val VertxModule = module {
  single { Vertx.vertx() as Vertx }
}
