package dev.mongocamp.server.route.docs

import dev.mongocamp.server.BuildInfo
import dev.mongocamp.server.config.ConfigHolder
import dev.mongocamp.server.exception.ErrorDescription
import dev.mongocamp.server.route.BaseRoute
import sttp.capabilities.WebSockets
import sttp.capabilities.akka.AkkaStreams
import sttp.model.{ Method, StatusCode }
import sttp.tapir._
import sttp.tapir.docs.openapi.OpenAPIDocsInterpreter
import sttp.tapir.openapi.OpenAPI
import sttp.tapir.openapi.circe.yaml.RichOpenAPI
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.swagger.{ SwaggerUI, SwaggerUIOptions }

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Future

object ApiDocsRoutes extends BaseRoute {
  val nameAsyncApiDocsYamlName = "asyncapidocs.yaml"
  val nameOpenApiDocsYamlName  = "docs.yaml"

  lazy val isSwaggerEnabled: Boolean = ConfigHolder.docsUseSwagger.value
  lazy val isOpenApiEnabled: Boolean = ConfigHolder.docsUseOpenApi.value

  def docsYamlEndpoint(yamlName: String, content: String): ServerEndpoint[AkkaStreams with WebSockets, Future] = {
    def contentToResponse(): Future[Either[(StatusCode, ErrorDescription, ErrorDescription), (String, Long)]] = {
      Future.successful(Right({
        (content, content.getBytes.length)
      }))
    }

    val endpoint = baseEndpoint
      .in("docs" / yamlName)
      .out(stringBody)
      .out(header("Content-Type", "text/yaml"))
      .out(header("Content-Disposition", "inline; filename=\"%s\"".format(yamlName)))
      .out(header[Long]("Content-Length"))
      .tag("Docs")
      .method(Method.GET)
      .name(yamlName)
      .serverLogic(_ => contentToResponse())

    endpoint
  }

  def addDocsRoutes(serverEndpoints: List[ServerEndpoint[AkkaStreams with WebSockets, Future]]): List[ServerEndpoint[AkkaStreams with WebSockets, Future]] = {
    val docs = ArrayBuffer[ServerEndpoint[AkkaStreams with WebSockets, Future]]()

    if (isSwaggerEnabled || isOpenApiEnabled) {
      val openApiDocs: OpenAPI = OpenAPIDocsInterpreter().toOpenAPI(
        serverEndpoints.map(_.endpoint),
        BuildInfo.name,
        BuildInfo.version
      )
      val openApiYml: String = openApiDocs.toYaml

      if (isSwaggerEnabled) {
        val swaggerUIRoute = SwaggerUI[Future](openApiYml, SwaggerUIOptions(List("docs"), nameOpenApiDocsYamlName, List()))
        docs ++= swaggerUIRoute
      }

      if (!isSwaggerEnabled) {
        docs += docsYamlEndpoint(nameOpenApiDocsYamlName, openApiYml)
      }
    }

    docs.toList
  }

}
