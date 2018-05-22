package uk.gov.hmcts.solccdservices.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._

object CaseDetailsSolData {

  def createCaseDataHttp(): ChainBuilder = {
    exec(
      http("Template SOL")
        .get("/template/case-details/sol")
        .check(status is 200)
    )
  }

  val createData: ScenarioBuilder = scenario("Template SOL")
    .exec(createCaseDataHttp())
}