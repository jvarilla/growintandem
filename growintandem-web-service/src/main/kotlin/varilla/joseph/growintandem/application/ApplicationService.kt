package varilla.joseph.growintandem.application

import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import org.koin.core.KoinComponent

interface ApplicationService :KoinComponent{

  /**
   * Get a list of plants
   * @return  A JsonArray of plants
   */
  suspend fun getPlantsList() :JsonArray

  /**
   * Get a plant by id
   * @return A JsonObject representation of a plantn
   */
  suspend fun getPlantById(id :String) : JsonObject
}
