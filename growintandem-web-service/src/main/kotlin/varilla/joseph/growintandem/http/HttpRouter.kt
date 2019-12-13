package varilla.joseph.growintandem.http

import org.koin.core.KoinComponent

interface HttpRouter :KoinComponent {

  /**
   * Returns all the plants in the system
   */
  suspend fun getPlantsHandler(): Unit;

  /**
   * Returns a plant with a specific id
   */
  suspend fun getPlantHandler(): Unit;



}
