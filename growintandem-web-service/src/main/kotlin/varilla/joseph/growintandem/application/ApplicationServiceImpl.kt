package varilla.joseph.growintandem.application

import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import org.koin.core.KoinComponent
import org.koin.core.inject
import varilla.joseph.growintandem.domain.plants.domainService.PlantDomainService
import varilla.joseph.growintandem.utils.domain.PlantNotFoundException
import varilla.joseph.growintandem.utils.http.RequestErrorException
import java.time.Instant

class ApplicationServiceImpl :ApplicationService, KoinComponent {
  private val plantDomainService by inject<PlantDomainService>()

  override suspend fun getPlantsList(): JsonArray {
    try {

      // Get the plants list from the domain service
      val plants = plantDomainService.getPlantsList()

      return JsonArray(plants)

    } catch(throwable :Throwable) {
        when(throwable) {
          else -> throw throwable
        }
    }
  }


  override suspend fun getPlantById(id: String): JsonObject {
    try {

      // Get a plant by id from the domain service
      val plant = plantDomainService.getPlantById(id)

      return plant.toJsonObject()

    } catch (throwable: Throwable) {
        when(throwable) {
          is PlantNotFoundException -> throw RequestErrorException(404, "Plant Not Found")
          else -> throw throwable
        }
    }
  }


  override suspend fun getPlantWateringSchedule(id :String, startDate : Instant,
                                                numWeeks :Int, allowWeekends :Boolean) : JsonObject {
    try {
      // Get the plants watering schedule
      return plantDomainService.getPlantWateringSchedule(id, startDate, numWeeks, allowWeekends).toJsonObject()

    } catch (throwable: Throwable) {
      when(throwable) {
        is PlantNotFoundException -> throw RequestErrorException(404, "Plant Not Found")
        else -> throw throwable
      }
    }
  }

}
