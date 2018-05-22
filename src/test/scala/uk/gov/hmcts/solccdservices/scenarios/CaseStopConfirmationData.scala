package uk.gov.hmcts.solccdservices.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._

object CaseStopConfirmationData {

  def caseStopConfirmationDataHttp(): ChainBuilder = {
    exec(
      http("Case Stop Confirmation")
        .post("/case/stopConfirmation")
        .body(ElFileBody("data/caseStopConfirmation.json")).asJSON
        .check(status is 200)
    )
  }

  val createData: ScenarioBuilder = scenario("caseStopConfirmation")
    .exec(caseStopConfirmationDataHttp())
}