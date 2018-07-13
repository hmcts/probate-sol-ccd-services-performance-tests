package uk.gov.hmcts.solccdservices.util

import feign.Feign
import feign.jackson.JacksonEncoder
import org.springframework.cloud.netflix.feign.support.SpringMvcContract
import uk.gov.hmcts.reform.authorisation.ServiceAuthorisationApi
import uk.gov.hmcts.reform.authorisation.generators.ServiceAuthTokenGenerator

object ServiceAuthTokenGeneratorCreator {

  val serviceAuthTokenGenerator: ServiceAuthTokenGenerator = serviceAuthTokenGenerator(Environment.devAIDAMS2S,
    Environment.s2sAuthTotpSecret, Environment.serviceName)

  private def serviceAuthTokenGenerator(s2sUrl: String, secret: String, microService: String):
  ServiceAuthTokenGenerator = {
    val serviceAuthorisationApi = Feign.builder.encoder(new JacksonEncoder)
      .contract(new SpringMvcContract).target(classOf[ServiceAuthorisationApi], s2sUrl)
    new ServiceAuthTokenGenerator(secret, microService, serviceAuthorisationApi)
  }
}
