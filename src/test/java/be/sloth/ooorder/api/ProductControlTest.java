package be.sloth.ooorder.api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductControlTest {

    @LocalServerPort
    private int port;



    private static String requestBody = "{\n" +
            "  \"name\": \"thing\",\n" +
            "  \"description\": \"It does stuff\", \n" +
            "  \"priceInEuro\": \"11\", \n" +
            "  \"stock\": \"1\"}";

    @Test
    void registerProduct() {

        System.out.println(requestBody);


        given()
                .baseUri("http://localhost")
                .port(port)
                .auth()
                .preemptive()
                .basic("gigachad@based.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/catalogue")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract();
    }

    @Test
    void viewCatalogue() {



        given()
                .baseUri("http://localhost")
                .port(port)
                .header("Content-type", "application/json")
                .when()
                .get("/catalogue")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }

}