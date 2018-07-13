package uk.gov.hmcts.solccdservices.util

object Environment {
  val devBaseURL: String = scala.util.Properties.envOrElse("SOL_CCD_SERVICE_BASE_URL", "http://localhost:4104")
  val users: String = scala.util.Properties.envOrElse("numberOfUsers", "500")
  val maxResponseTime: String = scala.util.Properties.envOrElse("maxResponseTime", "20000") // in milliseconds
  val devAIDAMUserToken: String = scala.util.Properties.envOrElse("USER_AUTH_PROVIDER_OAUTH2_URL", "http://localhost:4501")
  val devAIDAMS2S: String = scala.util.Properties.envOrElse("SERVICE_AUTH_PROVIDER_BASE_URL", "http://localhost:4502")
  val atOnceUsers: String = scala.util.Properties.envOrElse("AT_ONCE_USERS", "1")
  val minPassPercent: String = scala.util.Properties.envOrElse("MIN_PASS_PERCENT", "99")
  val clientId: String = scala.util.Properties.envOrElse("IDAM_OAUTH2_CLIENT_ID", "ccd_gateway")
  val clientSecret: String = scala.util.Properties.envOrElse("IDAM_OAUTH2_CLIENT_SECRET", "OOOOOOOOOOOOOOOO")
  val redirectUri: String = scala.util.Properties.envOrElse("IDAM_OAUTH2_REDIRECT_URI", "http://localhost:3451/oauth2redirect")
  val userId: String = scala.util.Properties.envOrElse("IDAM_USER_ID", "null")
  val serviceName: String = scala.util.Properties.envOrElse("SERVICE_NAME", "PROBATE_BACKEND")
  val s2sAuthTotpSecret: String = scala.util.Properties.envOrElse("S2S_AUTH_TOTP_SECRET", "AAAAAAAAAAAAAAAA")
}
