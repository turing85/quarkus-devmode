package de.consol.dus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestHTTPEndpoint(GreetingResource.class)
class GreetingResourceTest {

  @Test
  void greetingEndpointWithEmptyBodyTest() {
    // @formatter:off
    given()
        .when()
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .post()
        .then()
            .statusCode(Response.Status.OK.getStatusCode())
            .body(is("Hi IBM!"));
    // @formatter:on
  }

  @Test
  void greetingEndpointWithNonemptyBodyTest() {
    // @formatter:off
    given()
        .when()
            .header("Content-Type", MediaType.APPLICATION_JSON)
            .body(GreetingRequest.builder().salutation("Hi").name("John Doe").build())
            .post()
        .then()
            .statusCode(Response.Status.OK.getStatusCode())
            .body(is("Hi John Doe!"));
    // @formatter:on
  }
}