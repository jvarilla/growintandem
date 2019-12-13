package varilla.joseph.growintandem.application

import io.vertx.core.json.Json
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import org.koin.core.KoinComponent
import org.koin.core.inject
import varilla.joseph.growintandem.domain.plants.domainService.PlantDomainService
import varilla.joseph.growintandem.utils.models.InvalidPlantException
import varilla.joseph.growintandem.utils.models.Plant

class ApplicationServiceImpl :ApplicationService, KoinComponent {
  private val plantDomainService by inject<PlantDomainService>()

  override suspend fun getPlantsList(): JsonArray {
    try {

      val plants = plantDomainService.getPlantsList()
      return JsonArray(plants)

    } catch(throwable :Throwable) {
      throw throwable
    }
  }


  override suspend fun getPlantById(id: String): JsonObject {
    try {
      val plant = plantDomainService.getPlantById(id)
      return plant.toJsonObject()

    } catch (throwable: Throwable) {

      throw throwable

    }
  }

}
