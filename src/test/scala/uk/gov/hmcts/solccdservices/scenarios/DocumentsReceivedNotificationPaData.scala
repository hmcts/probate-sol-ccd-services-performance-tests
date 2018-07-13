package uk.gov.hmcts.solccdservices.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._


object DocumentsReceivedNotificationPaData {

  def createCaseDataHttp(): ChainBuilder = {
    exec(
      http("DocumentReceivedNotificationPa")
        .post("/notify/documents-received")
        .body(ElFileBody("data/personalPayloadNotifications.json")).asJSON
        .check(status is 200)
    )
  }

  val createData: ScenarioBuilder = scenario("documentReceivedNotificationPa")
    .exec(createCaseDataHttp())


}
