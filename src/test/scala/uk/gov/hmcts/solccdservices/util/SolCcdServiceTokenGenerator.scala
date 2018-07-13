package uk.gov.hmcts.solccdservices.util


import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import io.jsonwebtoken.Jwts
import io.restassured.RestAssured.{given, post}
import io.restassured.http.ContentType
import io.restassured.response.Response
import uk.gov.hmcts.reform.authorisation.generators.ServiceAuthTokenGenerator

object SolCcdServiceTokenGenerator {

  val serviceAuthTokenGenerator: ServiceAuthTokenGenerator = ServiceAuthTokenGeneratorCreator.serviceAuthTokenGenerator

  val oauth2Url: String = Environment.devAIDAMUserToken
  val clientSecret: String = Environment.clientSecret
  val clientId: String = Environment.clientId
  val redirectUri: String = Environment.redirectUri

  val mapper = new ObjectMapper

  def generateServiceToken(): String = {
    serviceAuthTokenGenerator.generate()
  }

  def createUser(): Response = {
    given
      .contentType(ContentType.JSON)
      .body("""{ "email":"test@TEST.COM", "forename":"test@TEST.COM","surname":"test@TEST.COM","password":"123"}""")
      .post(oauth2Url + "/testing-support/accounts")
  }

  def getUserId: String = {
    if (!Environment.userId.isEmpty) Environment.userId
    val clientToken: String = generateClientToken
    val withoutSignature = clientToken.substring(0, clientToken.lastIndexOf('.') + 1)
    val claims = Jwts.parser.parseClaimsJwt(withoutSignature).getBody
    claims.get("id", classOf[String])
  }

  def generateClientToken: String = {
    val code = generateCode
    val jsonResponse = post(oauth2Url + "/oauth2/token?code=" + code + "&client_secret=" + clientSecret
      + "&client_id=" + clientId + "&redirect_uri=" + redirectUri + "&grant_type=authorization_code").body.asString
    println("JSON: " + jsonResponse)
    mapper.readValue(jsonResponse, classOf[JsonNode]).get("access_token").asText()
  }

  def generateCode: String = {
    val jsonResponse = given.relaxedHTTPSValidation.header("Authorization", "Basic dGVzdEBURVNULkNPTToxMjM=")
      .post(oauth2Url + "/oauth2/authorize?response_type=code" + "&client_id=" + clientId + "&redirect_uri="
        + redirectUri).asString
    mapper.readValue(jsonResponse, classOf[JsonNode]).get("code").asText()
  }
}
