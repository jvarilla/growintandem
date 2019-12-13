package varilla.joseph.growintandem.application

import io.vertx.core.json.Json
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import org.koin.core.KoinComponent
import varilla.joseph.growintandem.utils.models.InvalidPlantException
import varilla.joseph.growintandem.utils.models.Plant

class ApplicationServiceImpl :ApplicationService, KoinComponent {

  override suspend fun getPlantsList(): JsonArray {
    try {

      val plant = Plant("123", "Money Tree", 14).toJsonObject()
      return JsonArray(listOf<JsonObject>(plant, plant, plant))

    } catch(throwable :Throwable) {
      throw throwable
    }
  }


  override suspend fun getPlantById(id: String): JsonObject {
    try {

      return Plant("123", "Money Tree", 14).toJsonObject()

    } catch (throwable: Throwable) {

      throw throwable

    }
  }

}
