/** mongocamp-server No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
  *
  * The version of the OpenAPI document: 1.4.0
  *
  * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech). https://openapi-generator.tech Do not edit the class manually.
  */
package dev.mongocamp.server.test.client.api

import dev.mongocamp.server.converter.CirceSchema
import dev.mongocamp.server.test.TestServer
import dev.mongocamp.server.test.client.core.JsonSupport._
import dev.mongocamp.server.test.client.model._
import sttp.client3._
import sttp.model.Method

object DocumentApi {

  def apply(baseUrl: String = TestServer.serverBaseUrl) = new DocumentApi(baseUrl)
}

class DocumentApi(baseUrl: String) extends CirceSchema {

  /** Delete one Document from given Collection
    *
    * Expected answers: code 200 : DeleteResponse () code 0 : ErrorDescription () Headers : x-error-code - Error Code x-error-message - Message of the
    * MongoCampException x-error-additional-info - Additional information for the MongoCampException
    *
    * Available security schemes: httpAuth1 (http) httpAuth (http) apiKeyAuth (apiKey)
    *
    * @param collectionName
    *   The name of your MongoDb Collection
    * @param documentId
    *   DocumentId to delete
    */
  def delete(username: String, password: String, bearerToken: String, apiKey: String)(collectionName: String, documentId: String) =
    basicRequest
      .method(Method.DELETE, uri"$baseUrl/mongodb/collections/${collectionName}/documents/${documentId}")
      .contentType("application/json")
      .auth
      .basic(username, password)
      .auth
      .bearer(bearerToken)
      .header("X-AUTH-APIKEY", apiKey)
      .response(asJson[DeleteResponse])

  /** Delete many Document in given Collection
    *
    * Expected answers: code 200 : DeleteResponse () code 400 : String (Invalid value for: body) code 0 : ErrorDescription () Headers : x-error-code - Error
    * Code x-error-message - Message of the MongoCampException x-error-additional-info - Additional information for the MongoCampException
    *
    * Available security schemes: httpAuth1 (http) httpAuth (http) apiKeyAuth (apiKey)
    *
    * @param collectionName
    *   The name of your MongoDb Collection
    * @param requestBody
    */
  def deleteMany(username: String, password: String, bearerToken: String, apiKey: String)(collectionName: String, requestBody: Map[String, Any]) =
    basicRequest
      .method(Method.DELETE, uri"$baseUrl/mongodb/collections/${collectionName}/documents/many/delete")
      .contentType("application/json")
      .auth
      .basic(username, password)
      .auth
      .bearer(bearerToken)
      .header("X-AUTH-APIKEY", apiKey)
      .body(requestBody)
      .response(asJson[DeleteResponse])

  /** Alternative to GET Route for more complex queries and URL max. Length
    *
    * Expected answers: code 200 : Seq[Map[String, Any]] () Headers : x-pagination-count-rows - count all elements x-pagination-rows-per-page - Count elements
    * per page x-pagination-current-page - Current page x-pagination-count-pages - Count pages code 400 : String (Invalid value for: body, Invalid value for:
    * query parameter rowsPerPage, Invalid value for: query parameter page) code 0 : ErrorDescription () Headers : x-error-code - Error Code x-error-message -
    * Message of the MongoCampException x-error-additional-info - Additional information for the MongoCampException
    *
    * Available security schemes: httpAuth1 (http) httpAuth (http) apiKeyAuth (apiKey)
    *
    * @param collectionName
    *   The name of your MongoDb Collection
    * @param mongoFindRequest
    * @param rowsPerPage
    *   Count elements per page
    * @param page
    *   Requested page of the ResultSets
    */
  def find(
      username: String,
      password: String,
      bearerToken: String,
      apiKey: String
  )(collectionName: String, mongoFindRequest: MongoFindRequest, rowsPerPage: Option[Long] = None, page: Option[Long] = None) =
    basicRequest
      .method(Method.POST, uri"$baseUrl/mongodb/collections/${collectionName}/documents?rowsPerPage=${rowsPerPage}&page=${page}")
      .contentType("application/json")
      .auth
      .basic(username, password)
      .auth
      .bearer(bearerToken)
      .header("X-AUTH-APIKEY", apiKey)
      .body(mongoFindRequest)
      .response(asJson[Seq[Map[String, Any]]])

  /** Get one Document from given Collection
    *
    * Expected answers: code 200 : Map[String, Any] () code 0 : ErrorDescription () Headers : x-error-code - Error Code x-error-message - Message of the
    * MongoCampException x-error-additional-info - Additional information for the MongoCampException
    *
    * Available security schemes: httpAuth1 (http) httpAuth (http) apiKeyAuth (apiKey)
    *
    * @param collectionName
    *   The name of your MongoDb Collection
    * @param documentId
    *   DocumentId to read
    */
  def getDocument(username: String, password: String, bearerToken: String, apiKey: String)(collectionName: String, documentId: String) =
    basicRequest
      .method(Method.GET, uri"$baseUrl/mongodb/collections/${collectionName}/documents/${documentId}")
      .contentType("application/json")
      .auth
      .basic(username, password)
      .auth
      .bearer(bearerToken)
      .header("X-AUTH-APIKEY", apiKey)
      .response(asJson[Map[String, Any]])

  /** Insert one Document in given Collection
    *
    * Expected answers: code 200 : InsertResponse () code 400 : String (Invalid value for: body) code 0 : ErrorDescription () Headers : x-error-code - Error
    * Code x-error-message - Message of the MongoCampException x-error-additional-info - Additional information for the MongoCampException
    *
    * Available security schemes: httpAuth1 (http) httpAuth (http) apiKeyAuth (apiKey)
    *
    * @param collectionName
    *   The name of your MongoDb Collection
    * @param requestBody
    *   JSON Representation for your Document.
    */
  def insert(username: String, password: String, bearerToken: String, apiKey: String)(collectionName: String, requestBody: Map[String, Any]) =
    basicRequest
      .method(Method.PUT, uri"$baseUrl/mongodb/collections/${collectionName}/documents")
      .contentType("application/json")
      .auth
      .basic(username, password)
      .auth
      .bearer(bearerToken)
      .header("X-AUTH-APIKEY", apiKey)
      .body(requestBody)
      .response(asJson[InsertResponse])

  /** Insert many documents in given Collection
    *
    * Expected answers: code 200 : InsertResponse () code 400 : String (Invalid value for: body) code 0 : ErrorDescription () Headers : x-error-code - Error
    * Code x-error-message - Message of the MongoCampException x-error-additional-info - Additional information for the MongoCampException
    *
    * Available security schemes: httpAuth1 (http) httpAuth (http) apiKeyAuth (apiKey)
    *
    * @param collectionName
    *   The name of your MongoDb Collection
    * @param requestBody
    */
  def insertMany(username: String, password: String, bearerToken: String, apiKey: String)(collectionName: String, requestBody: Seq[Map[String, Any]]) =
    basicRequest
      .method(Method.PUT, uri"$baseUrl/mongodb/collections/${collectionName}/documents/many/insert")
      .contentType("application/json")
      .auth
      .basic(username, password)
      .auth
      .bearer(bearerToken)
      .header("X-AUTH-APIKEY", apiKey)
      .body(requestBody)
      .response(asJson[InsertResponse])

  /** Get Documents paginated from given Collection
    *
    * Expected answers: code 200 : Seq[Map[String, Any]] () Headers : x-pagination-count-rows - count all elements x-pagination-rows-per-page - Count elements
    * per page x-pagination-current-page - Current page x-pagination-count-pages - Count pages code 400 : String (Invalid value for: query parameter filter,
    * Invalid value for: query parameter sort, Invalid value for: query parameter projection, Invalid value for: query parameter rowsPerPage, Invalid value for:
    * query parameter page) code 0 : ErrorDescription () Headers : x-error-code - Error Code x-error-message - Message of the MongoCampException
    * x-error-additional-info - Additional information for the MongoCampException
    *
    * Available security schemes: httpAuth1 (http) httpAuth (http) apiKeyAuth (apiKey)
    *
    * @param collectionName
    *   The name of your MongoDb Collection
    * @param filter
    *   Lucene Query by all filter. More Information: <a target=\"_blank\"
    *   href=\"https://mongodb-driver.mongocamp.dev/documentation/database/lucene.html\">Lucene Query</a>
    * @param sort
    *   Sorting by with an list of keys. Leading minus (-) means desc.
    * @param projection
    *   MongoDB projection
    * @param rowsPerPage
    *   Count elements per page
    * @param page
    *   Requested page of the ResultSets
    */
  def listDocuments(username: String, password: String, bearerToken: String, apiKey: String)(
      collectionName: String,
      filter: Option[String] = None,
      sort: Seq[String],
      projection: Seq[String],
      rowsPerPage: Option[Long] = None,
      page: Option[Long] = None
  ) =
    basicRequest
      .method(
        Method.GET,
        uri"$baseUrl/mongodb/collections/${collectionName}/documents?filter=${filter}&sort=${sort}&projection=${projection}&rowsPerPage=${rowsPerPage}&page=${page}"
      )
      .contentType("application/json")
      .auth
      .basic(username, password)
      .auth
      .bearer(bearerToken)
      .header("X-AUTH-APIKEY", apiKey)
      .response(asJson[Seq[Map[String, Any]]])

  /** 'Replace' one Document with the new document from Request in Collection
    *
    * Expected answers: code 200 : UpdateResponse () code 400 : String (Invalid value for: body) code 0 : ErrorDescription () Headers : x-error-code - Error
    * Code x-error-message - Message of the MongoCampException x-error-additional-info - Additional information for the MongoCampException
    *
    * Available security schemes: httpAuth1 (http) httpAuth (http) apiKeyAuth (apiKey)
    *
    * @param collectionName
    *   The name of your MongoDb Collection
    * @param documentId
    *   DocumentId to update
    * @param requestBody
    */
  def update(username: String, password: String, bearerToken: String, apiKey: String)(
      collectionName: String,
      documentId: String,
      requestBody: Map[String, Any]
  ) =
    basicRequest
      .method(Method.PATCH, uri"$baseUrl/mongodb/collections/${collectionName}/documents/${documentId}")
      .contentType("application/json")
      .auth
      .basic(username, password)
      .auth
      .bearer(bearerToken)
      .header("X-AUTH-APIKEY", apiKey)
      .body(requestBody)
      .response(asJson[UpdateResponse])

  /** Update the document Parts with the values from the Request
    *
    * Expected answers: code 200 : UpdateResponse () code 400 : String (Invalid value for: body) code 0 : ErrorDescription () Headers : x-error-code - Error
    * Code x-error-message - Message of the MongoCampException x-error-additional-info - Additional information for the MongoCampException
    *
    * Available security schemes: httpAuth1 (http) httpAuth (http) apiKeyAuth (apiKey)
    *
    * @param collectionName
    *   The name of your MongoDb Collection
    * @param documentId
    *   DocumentId to update
    * @param requestBody
    */
  def updateDocumentPartial(username: String, password: String, bearerToken: String, apiKey: String)(
      collectionName: String,
      documentId: String,
      requestBody: Map[String, Any]
  ) =
    basicRequest
      .method(Method.PATCH, uri"$baseUrl/mongodb/collections/${collectionName}/documents/${documentId}/partial")
      .contentType("application/json")
      .auth
      .basic(username, password)
      .auth
      .bearer(bearerToken)
      .header("X-AUTH-APIKEY", apiKey)
      .body(requestBody)
      .response(asJson[UpdateResponse])

  /** Update many Document in given Collection
    *
    * Expected answers: code 200 : UpdateResponse () code 400 : String (Invalid value for: body) code 0 : ErrorDescription () Headers : x-error-code - Error
    * Code x-error-message - Message of the MongoCampException x-error-additional-info - Additional information for the MongoCampException
    *
    * Available security schemes: httpAuth1 (http) httpAuth (http) apiKeyAuth (apiKey)
    *
    * @param collectionName
    *   The name of your MongoDb Collection
    * @param updateRequest
    */
  def updateMany(username: String, password: String, bearerToken: String, apiKey: String)(collectionName: String, updateRequest: UpdateRequest) =
    basicRequest
      .method(Method.PATCH, uri"$baseUrl/mongodb/collections/${collectionName}/documents/many/update")
      .contentType("application/json")
      .auth
      .basic(username, password)
      .auth
      .bearer(bearerToken)
      .header("X-AUTH-APIKEY", apiKey)
      .body(updateRequest)
      .response(asJson[UpdateResponse])
}
