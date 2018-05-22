package uk.gov.hmcts.solccdservices.util


import java.util.Collections.singletonList

import io.circe._
import io.circe.parser._
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.response.Response
import org.springframework.http.{HttpEntity, HttpHeaders, MediaType}
import org.springframework.util.{LinkedMultiValueMap, MultiValueMap}
import org.springframework.web.client.RestTemplate

object SolCcdServiceTokenGenerator {

  def generateServiceToken(): String = {
    val S2S_URL: String = Environment.devAIDAMS2S
    val restTemplate = new RestTemplate

    val headers = new HttpHeaders
    headers.setContentType(MediaType.MULTIPART_FORM_DATA)
    headers.setAccept(singletonList(MediaType.ALL))

    val body = new LinkedMultiValueMap[String, String]
    body.add("microservice", "probate_backend")

    restTemplate.postForObject(S2S_URL + "/testing-support/lease", new HttpEntity[MultiValueMap[String, String]](body, headers), classOf[String])
  }

  def generateCode(): String = {
    createUser()

    val url: String = Environment.devAIDAMUserToken
    val clientId: String = Environment.clientId
    val redirectUri: String = Environment.redirectUri
    val restTemplate = new RestTemplate
    val body = new LinkedMultiValueMap[String, String]

    val headers = new HttpHeaders
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED)
    headers.setAccept(singletonList(MediaType.ALL))
    headers.add("Authorization", "Basic dGVzdEBURVNULkNPTToxMjM=")

    val json = restTemplate.postForObject(url + "/oauth2/authorize?response_type=code&client_id=" + clientId + "&redirect_uri=" + redirectUri + "&continue-url=some-url", new HttpEntity[MultiValueMap[String, String]](body, headers), classOf[String])
    val doc: Json = parse(json).getOrElse(Json.Null)
    val cursor: HCursor = doc.hcursor

    cursor.downField("code").as[String].fold(_ => null, r => r.toString)
  }

  def createUser(): Response = {
    val url: String = Environment.devAIDAMUserToken

    given
      .contentType(ContentType.JSON)
      .body("""{ "email":"test@TEST.COM", "forename":"test@TEST.COM","surname":"test@TEST.COM","password":"123"}""")
      .post(url + "/testing-support/accounts")
  }

  def generateClientToken(code: String): String = {
    val url: String = Environment.devAIDAMUserToken
    val clientId: String = Environment.clientId
    val clientSecret: String = Environment.clientSecret
    val redirectUri: String = Environment.redirectUri
    val restTemplate = new RestTemplate
    val body = new LinkedMultiValueMap[String, String]

    val headers = new HttpHeaders
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED)
    headers.setAccept(singletonList(MediaType.ALL))

    val json = restTemplate.postForObject(url + "/oauth2/token?code=" + code + "&client_secret=" + clientSecret + "&client_id=" + clientId + "&redirect_uri=" + redirectUri + "&grant_type=authorization_code",
      new HttpEntity[MultiValueMap[String, String]](body, headers), classOf[String])
    val doc: Json = parse(json).getOrElse(Json.Null)
    val cursor: HCursor = doc.hcursor

    cursor.downField("access_token").as[String].fold(_ => null, r => r.toString)
  }
}
