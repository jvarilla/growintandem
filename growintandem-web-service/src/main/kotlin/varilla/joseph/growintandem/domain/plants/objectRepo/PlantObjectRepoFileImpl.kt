package varilla.joseph.growintandem.domain.plants.objectRepo

import org.koin.core.KoinComponent
import varilla.joseph.growintandem.utils.models.Plant

class PlantObjectRepoFileImpl :PlantObjectRepo , KoinComponent {
  private val plantMap = mutableMapOf<String, Plant>(
    "123" to Plant("123", "Money Tree", 14),
    "456" to Plant("456", "Apple Tree", 7),
    "789" to Plant("789", "Plant of Plants", 9001)
  )

  override suspend fun getPlantsList(): List<Plant> {
      try {
        return plantMap.values.toList()
      } catch(throwable :Throwable) {
        throw throwable
      }
  }

  override suspend fun getPlantById(id :String): Plant {
      try {
          return plantMap.get(id) ?: throw Exception()
      } catch (throwable :Throwable) {
        throw throwable
      }

  }
}
