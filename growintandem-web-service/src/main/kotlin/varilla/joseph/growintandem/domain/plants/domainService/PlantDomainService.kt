package varilla.joseph.growintandem.domain.plants.domainService

import org.koin.core.KoinComponent
import varilla.joseph.growintandem.utils.models.Plant

interface PlantDomainService :KoinComponent {

  /**
   * Get list of plants
   * @return List of plants as DTO's
   */
  suspend fun getPlantsList() :List<Plant>

  /**
   * Get a plant by id
   * @return A plant as DTO
   */
  suspend fun getPlantById(id :String) :Plant

}
