package varilla.joseph.growintandem.utils.models

import io.vertx.core.json.JsonObject
import io.vertx.kotlin.core.json.jsonObjectOf
import org.omg.CORBA.DynAnyPackage.Invalid

data class Plant (var id:String, var name:String, var waterEveryNumDays :Int ) {
  fun toJsonObject() : JsonObject {
    return jsonObjectOf(
      "id" to this.id,
      "name" to this.name,
      "water_after" to this.waterEveryNumDays
    )
  }
}

fun JsonObject.toPlant() :Plant {
  try {
    return Plant(
      id = this.getString("id"),
      name = this.getString("name"),
      waterEveryNumDays = this.getInteger("water_after")
    )
  } catch (exception :Exception) {
      throw InvalidPlantException()
  }

}

class InvalidPlantException :Exception()
