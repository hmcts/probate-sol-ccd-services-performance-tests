package uk.gov.hmcts.solccdservices.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._

object NextStepsConfirmation {

  def nextStepConfirmationDataHttp(): ChainBuilder = {
    exec(
      http("Next Steps Confirmation")
        .post("/nextsteps/confirmation")
        .body(ElFileBody("data/nextStepsConfirmation.json")).asJSON
        .check(status is 200)
    )
  }

  val createData: ScenarioBuilder = scenario("nextStepsConfirmation")
    .exec(nextStepConfirmationDataHttp())
}