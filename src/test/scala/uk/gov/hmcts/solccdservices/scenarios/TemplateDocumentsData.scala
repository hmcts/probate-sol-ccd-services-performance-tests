package uk.gov.hmcts.solccdservices.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._

object TemplateDocumentsData {

  def createCaseDataHttp(): ChainBuilder = {

    exec(
      http("Template Documents Data")
        .post("/template/documents")
        .body(ElFileBody("data/templateDocuments.json")).asJSON
        .check(status is 200)
    )
  }

  val createData: ScenarioBuilder = scenario("Template Documents")
    .exec(createCaseDataHttp())
}