package varilla.joseph.growintandem.domain.plants.domainService

import org.koin.core.KoinComponent
import org.koin.core.inject
import varilla.joseph.growintandem.domain.plants.objectRepo.PlantObjectRepo
import varilla.joseph.growintandem.utils.models.Plant

class PlantDomainServiceImpl :PlantDomainService, KoinComponent {

  private val plantObjectRepo :PlantObjectRepo by inject()

  override suspend fun getPlantsList(): List<Plant> {
    try {
      // Get the plant list from the obj repo
      return plantObjectRepo.getPlantsList()

    } catch (throwable :Throwable) {
      throw throwable
    }
  }

  override suspend fun getPlantById(id: String): Plant {
    try {

      // Get a plant by id from the object repo
      return plantObjectRepo.getPlantById(id)

    } catch (throwable :Throwable) {
      throw throwable
    }
  }

}
