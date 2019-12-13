package varilla.joseph.growintandem.http

import io.vertx.ext.web.Router
import org.koin.core.KoinComponent

interface HttpRouter :KoinComponent {
  /**
   * Returns the router
   */
  suspend fun getRouter(): Router

  /**
   * Returns all the plants in the system
   */
  suspend fun getPlantsHandler(): Unit;

  /**
   * Returns a plant with a specific id
   */
  suspend fun getPlantHandler(): Unit;



}
