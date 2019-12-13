package varilla.joseph.growintandem.utils.http

import io.vertx.core.http.HttpServerResponse

/***
 * Send a message as a json string with a status code
 */
fun HttpServerResponse.sendAsJSONWithStatusCode(message:String, code:Int) {
  this.putHeader("content-type", "application/json")
    .setStatusCode(code)
    .setChunked(true)
    .write(message)
    .end()
}
