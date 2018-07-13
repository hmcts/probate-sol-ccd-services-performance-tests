package uk.gov.hmcts.solccdservices.simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import uk.gov.hmcts.solccdservices.scenarios._
import uk.gov.hmcts.solccdservices.util.{Environment, Headers, SolCcdServiceTokenGenerator}

import scala.concurrent.duration._
import scala.language.postfixOps

class SolCcdServicesSimulation extends Simulation {

  val serviceToken: String = SolCcdServiceTokenGenerator.generateServiceToken

  val httpConf: HttpProtocolBuilder = http.baseURL(Environment.devBaseURL)
    .headers(Headers.commonHeader)
    .header("ServiceAuthorization", serviceToken)
    .header("user-id", "null")

  val CaseDataScenarios = List(

    CaseValidateData.createData.inject(
      atOnceUsers(Environment.atOnceUsers.toInt),
      rampUsersPerSec(1) to 7 during (2 seconds)
    ),

    CaseStopConfirmationData.createData.inject(
      atOnceUsers(Environment.atOnceUsers.toInt),
      rampUsersPerSec(1) to 7 during (2 seconds)
    ),

    NextStepsValidateData.createData.inject(
      atOnceUsers(Environment.atOnceUsers.toInt),
      rampUsersPerSec(1) to 7 during (2 seconds)
    ),

    NextStepsConfirmation.createData.inject(
      atOnceUsers(Environment.atOnceUsers.toInt),
      rampUsersPerSec(1) to 7 during (2 seconds)
    ),

    CaseDetailsPaData.createData.inject(
      atOnceUsers(Environment.atOnceUsers.toInt),
      rampUsersPerSec(1) to 7 during (2 seconds)
    ),

    CaseDetailsSolData.createData.inject(
      atOnceUsers(Environment.atOnceUsers.toInt),
      rampUsersPerSec(1) to 7 during (2 seconds)
    ),

    TemplateDocumentsData.createData.inject(
      atOnceUsers(Environment.atOnceUsers.toInt),
      rampUsersPerSec(1) to 7 during (2 seconds)
    )
  )

  setUp(CaseDataScenarios)
    .protocols(httpConf)
    .maxDuration(1 minutes)
    .assertions(
      global.responseTime.max.lt(Environment.maxResponseTime.toInt),
      global.successfulRequests.percent.gte(Environment.minPassPercent.toInt)
    )
}