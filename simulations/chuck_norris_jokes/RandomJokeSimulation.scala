package chuck_norris_jokes

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RandomJokeSimulation extends Simulation {

  private val httpProtocol = http
    .baseUrl("https://api.chucknorris.io")
    .inferHtmlResources(AllowList(), DenyList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("ru")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/15.1 Safari/605.1.15")
  


  private val scn = scenario("RandomJokeSimulation")
    .exec(
      http("request_0")
        .get("/jokes/random")
    )

setUp(scn.inject(rampUsers(750).during(15.seconds))).protocols(httpProtocol)
}
