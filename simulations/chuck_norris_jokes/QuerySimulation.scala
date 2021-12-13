package chuck_norris_jokes

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class QuerySimulation extends Simulation {

  private val httpProtocol = http
    .baseUrl("https://api.chucknorris.io")
    .inferHtmlResources(AllowList(), DenyList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("ru")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/15.1 Safari/605.1.15")
  


  private val scn = scenario("QuerySimulation")
    .exec(
      http("request_0")
        .get("/jokes/search?query=money")
    )

setUp(scn.inject(rampUsers(1000).during(20.seconds))).protocols(httpProtocol)
}
