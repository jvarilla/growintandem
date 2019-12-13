package varilla.joseph.growintandem.domain.plants.domainService

import org.koin.core.KoinComponent
import varilla.joseph.growintandem.utils.models.Plant

class PlantDomainServiceImpl :PlantDomainService, KoinComponent {

  override suspend fun getPlantsList(): List<Plant> {
    try {
      return listOf<Plant>(
        Plant("123", "Money Tree", 14),
        Plant("123", "Money Tree", 14),
        Plant("123", "Money Tree", 14)
      )
    } catch (throwable :Throwable) {
      throw throwable
    }
  }

  override suspend fun getPlantById(id: String): Plant {
    try {

      return Plant("123", "Money Tree", 14)

    } catch (throwable :Throwable) {

      throw throwable

    }
  }

}
