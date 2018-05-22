package uk.gov.hmcts.solccdservices.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._

object CaseValidateData {

  def caseValidateDataHttp(): ChainBuilder = {
    exec(
      http("Case Validate")
        .post("/case/validate")
        .body(ElFileBody("data/caseValidate.json")).asJSON
        .check(status is 200)
    )
  }

  val createData: ScenarioBuilder = scenario("caseValidate")
    .exec(caseValidateDataHttp())
}