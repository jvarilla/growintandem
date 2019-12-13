package varilla.joseph.growintandem.modules.application


import io.vertx.core.Verticle
import io.vertx.core.Vertx
import org.koin.dsl.module
import varilla.joseph.growintandem.MainVerticle
import kotlin.coroutines.CoroutineContext

val VertxModule = module {
  single { Vertx.vertx() as Vertx }

  var mainVerticle = MainVerticle()
  single { mainVerticle as Verticle }
  single { mainVerticle.coroutineContext as CoroutineContext }
}
