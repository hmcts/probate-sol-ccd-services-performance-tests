package uk.gov.hmcts.solccdservices.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._

object GrantIssuedNotificationPaData {

  def createCaseDataHttp(): ChainBuilder = {
    exec(
      http("GrantIssuedNotificationPa")
        .post("/notify/grant-issued")
        .body(ElFileBody("data/personalPayloadNotifications.json")).asJSON
        .check(status is 200)
    )
  }

  val createData: ScenarioBuilder = scenario("grantIssuedNotificationPa")
    .exec(createCaseDataHttp())


}
