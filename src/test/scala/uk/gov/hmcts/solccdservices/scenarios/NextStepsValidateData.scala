package uk.gov.hmcts.solccdservices.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._

object NextStepsValidateData {

  def nextStepsValidateDataHttp(): ChainBuilder = {
    exec(
      http("Next Steps Validate")
        .post("/nextsteps/validate")
        .body(ElFileBody("data/nextStepsValidate.json")).asJSON
        .check(status is 200)
    )
  }

  val createData: ScenarioBuilder = scenario("nextStepsValidate")
    .exec(nextStepsValidateDataHttp())
}