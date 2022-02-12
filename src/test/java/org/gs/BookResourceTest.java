package org.gs;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class BookResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/books")
          .then()
             .statusCode(200);
    }

}