package com.quadstingray.mongo.rest.server

import com.quadstingray.mongo.rest.config.Config
import com.quadstingray.mongo.rest.interceptor.cors.CorsInterceptor
import com.quadstingray.mongo.rest.interceptor.{ MongoRestAkkaHttpServerLog, MongoRestDefaultDecodeFailureHandler, MongoRestExceptionHandler }
import com.typesafe.scalalogging.LazyLogging
import sttp.tapir.server.akkahttp.{ AkkaHttpServerInterpreter, AkkaHttpServerOptions }

object AkkaHttpServer extends LazyLogging with Config {

  private val serverOptions: AkkaHttpServerOptions = {
    AkkaHttpServerOptions.customInterceptors
      .exceptionHandler(new MongoRestExceptionHandler())
      .decodeFailureHandler(MongoRestDefaultDecodeFailureHandler.handler)
      .addInterceptor(new CorsInterceptor())
      .serverLog(new MongoRestAkkaHttpServerLog())
      .options
  }

  val akkaHttpServerInterpreter: AkkaHttpServerInterpreter = AkkaHttpServerInterpreter(serverOptions)

}
