package uk.gov.hmcts.solccdservices.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._

object CaseDetailsPaData {

  def createCaseDataHttp(): ChainBuilder = {
    exec(
      http("Template PA")
        .get("/template/case-details/pa")
        .check(status is 200)
    )
  }

  val createData: ScenarioBuilder = scenario("Template PA")
    .exec(createCaseDataHttp())
}