package varilla.joseph.growintandem.http

import io.vertx.core.Vertx
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Route
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import kotlinx.coroutines.launch
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.core.json.jsonObjectOf
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.koin.core.KoinComponent
import org.koin.core.inject
import varilla.joseph.growintandem.application.ApplicationService
import varilla.joseph.growintandem.utils.http.*
import varilla.joseph.growintandem.utils.models.Plant
import java.lang.Exception
import java.time.Instant
import kotlin.coroutines.CoroutineContext

class HttpRouterImpl(private val vertx : Vertx,
                     private val coroutineContext :CoroutineContext) : HttpRouter, KoinComponent {

  private val applicationService by inject<ApplicationService>()
  private val coroutineScope = CoroutineScope(coroutineContext)

  override suspend fun getRouter(): Router {
    val router = Router.router(vertx)

    // Add body parsing capability to router
    router.route().handler(BodyHandler.create())

    // API v1 base
    val apiBase1 = "/api/v1"

    // Default Route
    router.get("$apiBase1")
      .handler{ routingContext: RoutingContext ->
        val response = routingContext.response()
        response.putHeader("content-type", "text/plain")
        response.end("Hello from Vert.x")
      }


    // Add Routes
    router.get("$apiBase1/plants").coroutineHandler(this::getPlantsListHandler)
    router.get("$apiBase1/plants/:id").coroutineHandler(this::getPlantByIdHandler)

    // Example host:port/api/v1/plants/123/watering-schedule?weeks=12&startdate=2003-11-20T11:11:11Z&allowweekends=false
    router.get("$apiBase1/plants/:id/watering-schedule").coroutineHandler(this::getPlantWateringSchedule)
    return router
  }

  override suspend fun getPlantsListHandler(event :RoutingContext) {

    var request = event.request()
    var response = event.response()

    try {
      // Get the plants list from the application service
      val plants = applicationService.getPlantsList()

      // Serialize it
      val msg = Json.encodePrettily(plants)

      // Send the message
      response.sendAsJSONWithStatusCode(msg, 200)
    } catch (reqErrorException :RequestErrorException) {

      // Send the request error message
      response.sendAsJSONWithStatusCode(
          Json.encodePrettily(reqErrorException.toErrorMessageObj()), reqErrorException.statusCode)
    } catch(throwable :Throwable) {
        when(throwable) {
          else -> { // If unknown send 500 error
            response.sendAsJSONWithStatusCode(
              Json.encodePrettily(SERVER_ERROR_MESSAGE_OBJECT),
              SERVER_ERROR_MESSAGE_OBJECT.statusCode) }
        }
    }
  }

  override suspend fun getPlantByIdHandler(event :RoutingContext) {

    val request = event.request()
    val response = event.response()

    try {

      // Get the plants by id from the application service
      val id = request.getParam("id") ?: "id"
      val plant = applicationService.getPlantById(id)
      val msg = Json.encodePrettily(plant)
      event.response().sendAsJSONWithStatusCode(msg, 200)

    } catch (reqErrorException :RequestErrorException) {

      // Send any identified request error
      response.sendAsJSONWithStatusCode(
        Json.encodePrettily(reqErrorException.toErrorMessageObj()), reqErrorException.statusCode)

    } catch(throwable :Throwable) {

      when(throwable) {
        else -> { // Otherwise send 500 error
          response.sendAsJSONWithStatusCode(
            Json.encodePrettily(SERVER_ERROR_MESSAGE_OBJECT),
            SERVER_ERROR_MESSAGE_OBJECT.statusCode) }
      }
    }
  }


  override suspend fun getPlantWateringSchedule(event :RoutingContext) {

    var request = event.request()
    var response = event.response()

    try {

      // TODO: Fix so that missing parameters are accounted for

      // Get the query string parameters
      val plantId = request.getParam("id").trim().toLowerCase()

      // Get the number of weeks to get the schedule for TODO: default to 1 week if not specified
      val numWeeksToGetScheduleFor = Integer.parseInt(request.getParam("weeks").trim())

      // Parse the start date but default to today's date
      val scheduleStartDate = Instant.parse(request.getParam("startdate").trim()) ?: Instant.now()

      // Make alloweekends false by default if not true
      val scheduleForWeekends =
        request.getParam("allowweekends").trim().toLowerCase() == "true"


      // Get the plants list from the application service
      //val plants = applicationService.getPlantsList()
      val receivedParams = jsonObjectOf(
        "id" to plantId,
        "weeks" to numWeeksToGetScheduleFor,
        "startdate" to scheduleStartDate,
        "allowweekends" to scheduleForWeekends
      )

      // Serialize it
      val msg = Json.encodePrettily(receivedParams)

      // Send the message
      response.sendAsJSONWithStatusCode(msg, 200)
    } catch (reqErrorException :RequestErrorException) {

      // Send the request error message
      response.sendAsJSONWithStatusCode(
        Json.encodePrettily(reqErrorException.toErrorMessageObj()), reqErrorException.statusCode)
    } catch(throwable :Throwable) {
      when(throwable) {
        else -> { // If unknown send 500 error
          response.sendAsJSONWithStatusCode(
            Json.encodePrettily(SERVER_ERROR_MESSAGE_OBJECT),
            SERVER_ERROR_MESSAGE_OBJECT.statusCode) }
      }
    }
  }

  // This is needed to make coroutines work. Don't worry about how it works for now.
  private fun Route.coroutineHandler(fn: suspend (RoutingContext) -> Unit) {
      handler { ctx ->
        coroutineScope
          .launch(ctx.vertx().dispatcher()) {
          try {
            fn(ctx)
          } catch (e: Throwable) {
            ctx.fail(e)
          }
        }
      }
    }



}
