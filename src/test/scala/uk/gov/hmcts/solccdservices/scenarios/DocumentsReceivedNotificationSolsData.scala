package uk.gov.hmcts.solccdservices.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._

object DocumentsReceivedNotificationSolsData {

  def createCaseDataHttp(): ChainBuilder = {
    exec(
      http("DocumentReceivedNotificationSols")
        .post("/notify/documents-received")
        .body(ElFileBody("data/solicitorPayloadNotifications.json")).asJSON
        .check(status is 200)
    )
  }

  val createData: ScenarioBuilder = scenario("documentReceivedNotificationSols")
    .exec(createCaseDataHttp())
}
