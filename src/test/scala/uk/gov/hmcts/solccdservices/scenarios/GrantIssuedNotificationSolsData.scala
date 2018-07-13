package uk.gov.hmcts.solccdservices.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._

object GrantIssuedNotificationSolsData {

  def createCaseDataHttp(): ChainBuilder = {
    exec(
      http("GrantIssuedNotificationSols")
        .post("/notify/grant-issued")
        .body(ElFileBody("data/solicitorPayloadNotifications.json")).asJSON
        .check(status is 200)
    )
  }

  val createData: ScenarioBuilder = scenario("grantIssuedNotificationSols")
    .exec(createCaseDataHttp())


}
