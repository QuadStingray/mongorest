/** mongocamp No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
  *
  * The version of the OpenAPI document: 0.5.0
  *
  * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech). https://openapi-generator.tech Do not edit the class manually.
  */
package com.quadstingray.mongo.camp.client.api

import com.quadstingray.mongo.camp.client.core.JsonSupport._
import com.quadstingray.mongo.camp.client.model.{ CollectionStatus, DeleteResponse, JsonResultBoolean, MongoAggregateRequest }
import com.quadstingray.mongo.camp.converter.CirceSchema
import io.circe
import sttp.client3._
import sttp.client3.circe.asJson
import sttp.model.Method

object CollectionApi {
  def apply(baseUrl: String = com.quadstingray.mongo.camp.server.TestServer.serverBaseUrl) = new CollectionApi(baseUrl)
}

class CollectionApi(baseUrl: String) extends CirceSchema {

  /** Aggregate in your MongoDatabase Collection
    *
    * Expected answers: code 200 : Seq[Map[String, Any]] Headers : x-pagination-count-rows - count all elements x-pagination-rows-per-page - Count elements per
    * page x-pagination-current-page - Current page x-pagination-count-pages - Count pages code 400 : String (Invalid value for: body, Invalid value for: query
    * parameter rowsPerPage, Invalid value for: query parameter page) code 0 : ErrorDescription Headers : x-error-code - Error Code x-error-message - Message of
    * the MongoCampException x-error-additional-info - Additional information for the MongoCampException
    *
    * Available security schemes: apiKeyAuth (apiKey) httpAuth (http)
    *
    * @param collectionName
    *   The name of your MongoDb Collection
    * @param mongoAggregateRequest
    *   @param rowsPerPage Count elements per page
    * @param page
    *   Requested page of the ResultSets
    */
  def aggregate(
      apiKey: String,
      bearerToken: String
  )(collectionName: String, mongoAggregateRequest: MongoAggregateRequest, rowsPerPage: Option[Long] = None, page: Option[Long] = None) = {
    val requestBodyString = encodeAnyToJson(mongoAggregateRequest).toString() //todo: Validate on code generation
    basicRequest
      .method(Method.POST, uri"$baseUrl/mongodb/collections/$collectionName/aggregate?rowsPerPage=$rowsPerPage&page=$page")
      .contentType("application/json")
      .header("X-AUTH-APIKEY", apiKey)
      .auth
      .bearer(bearerToken)
      .body(requestBodyString)
      .response(asJson[Seq[Map[String, Any]]])
  }

  /** Delete all Document in Collection
    *
    * Expected answers: code 200 : DeleteResponse code 0 : ErrorDescription Headers : x-error-code - Error Code x-error-message - Message of the
    * MongoCampException x-error-additional-info - Additional information for the MongoCampException
    *
    * Available security schemes: apiKeyAuth (apiKey) httpAuth (http)
    *
    * @param collectionName
    *   The name of your MongoDb Collection
    */
  def clearCollection(apiKey: String, bearerToken: String)(collectionName: String) =
    basicRequest
      .method(Method.DELETE, uri"$baseUrl/mongodb/collections/$collectionName/clear")
      .contentType("application/json")
      .header("X-AUTH-APIKEY", apiKey)
      .auth
      .bearer(bearerToken)
      .response(asJson[DeleteResponse])

  /** List of all Collections
    *
    * Expected answers: code 200 : Seq[String] code 0 : ErrorDescription Headers : x-error-code - Error Code x-error-message - Message of the MongoCampException
    * x-error-additional-info - Additional information for the MongoCampException
    *
    * Available security schemes: apiKeyAuth (apiKey) httpAuth (http)
    */
  def collectionList(apiKey: String, bearerToken: String)(
  ): RequestT[Identity, Either[ResponseException[String, circe.Error], Seq[String]], Any] =
    basicRequest
      .method(Method.GET, uri"$baseUrl/mongodb/collections")
      .contentType("application/json")
      .header("X-AUTH-APIKEY", apiKey)
      .auth
      .bearer(bearerToken)
      .response(asJson[Seq[String]])

  /** Delete Collection
    *
    * Expected answers: code 200 : JsonResultBoolean code 0 : ErrorDescription Headers : x-error-code - Error Code x-error-message - Message of the
    * MongoCampException x-error-additional-info - Additional information for the MongoCampException
    *
    * Available security schemes: apiKeyAuth (apiKey) httpAuth (http)
    *
    * @param collectionName
    *   The name of your MongoDb Collection
    */
  def deleteCollection(apiKey: String, bearerToken: String)(collectionName: String) =
    basicRequest
      .method(Method.DELETE, uri"$baseUrl/mongodb/collections/$collectionName")
      .contentType("application/json")
      .header("X-AUTH-APIKEY", apiKey)
      .auth
      .bearer(bearerToken)
      .response(asJson[JsonResultBoolean])

  /** Distinct for Field in your MongoDatabase Collection
    *
    * Expected answers: code 200 : Seq[String] Headers : x-pagination-count-rows - count all elements x-pagination-rows-per-page - Count elements per page
    * x-pagination-current-page - Current page x-pagination-count-pages - Count pages code 400 : String (Invalid value for: query parameter rowsPerPage, Invalid
    * value for: query parameter page) code 0 : ErrorDescription Headers : x-error-code - Error Code x-error-message - Message of the MongoCampException
    * x-error-additional-info - Additional information for the MongoCampException
    *
    * Available security schemes: apiKeyAuth (apiKey) httpAuth (http)
    *
    * @param collectionName
    *   The name of your MongoDb Collection
    * @param field
    *   The field for your distinct Request.
    * @param rowsPerPage
    *   Count elements per page
    * @param page
    *   Requested page of the ResultSets
    */
  def distinct(apiKey: String, bearerToken: String)(collectionName: String, field: String, rowsPerPage: Option[Long] = None, page: Option[Long] = None) =
    basicRequest
      .method(Method.POST, uri"$baseUrl/mongodb/collections/$collectionName/distinct/$field?rowsPerPage=$rowsPerPage&page=$page")
      .contentType("application/json")
      .header("X-AUTH-APIKEY", apiKey)
      .auth
      .bearer(bearerToken)
      .response(asJson[Seq[Any]])

  /** All Informations about a single Collection
    *
    * Expected answers: code 200 : CollectionStatus code 400 : String (Invalid value for: query parameter includeDetails) code 0 : ErrorDescription Headers :
    * x-error-code - Error Code x-error-message - Message of the MongoCampException x-error-additional-info - Additional information for the MongoCampException
    *
    * Available security schemes: apiKeyAuth (apiKey) httpAuth (http)
    *
    * @param collectionName
    *   The name of your MongoDb Collection
    * @param includeDetails
    *   Include all details for the Collection
    */
  def getCollectionInformation(apiKey: String, bearerToken: String)(collectionName: String, includeDetails: Option[Boolean] = None) =
    basicRequest
      .method(Method.GET, uri"$baseUrl/mongodb/collections/$collectionName?includeDetails=$includeDetails")
      .contentType("application/json")
      .header("X-AUTH-APIKEY", apiKey)
      .auth
      .bearer(bearerToken)
      .response(asJson[CollectionStatus])

}
