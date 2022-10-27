package be.sloth.ooorder.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CustomerControlTest {


    @Autowired
    private CustomerControl customerControl;


    private static String requestBody = "{\n" +
            "  \"eMail\": \"x@x.x\",\n" +
            "  \"firstName\": \"a\",\n" +
            "  \"lastName\": \"b\",\n" +
            "  \"street\": \"abcstreet\", \n" +
            "  \"houseNumber\": \"11\", \n" +
            "  \"postalCode\": \"1234\", \n" +
            "  \"city\": \"Brussels\", \n" +
            "  \"phoneNumber\": \"11\", \n" +
            "  \"password\": \"ASSWORD\"}";


    @LocalServerPort
    private int port;


    @Test
    void registerCustomer() {

        given()
                .baseUri("http://localhost")
                .port(port)
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/customer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract();
    }

    @Test
    void registerCustomerWithoutEmail() {

        String badRequestBody = "{\n" +
                "  \"firstName\": \"a\",\n" +
                "  \"lastName\": \"b\",\n" +
                "  \"street\": \"abcstreet\", \n" +
                "  \"houseNumber\": \"11\", \n" +
                "  \"postalCode\": \"1234\", \n" +
                "  \"city\": \"Brussels\", \n" +
                "  \"phoneNumber\": \"11\", \n" +
                "  \"password\": \"ASSWORD\"}";

        given()
                .baseUri("http://localhost")
                .port(port)
                .header("Content-type", "application/json")
                .and()
                .body(badRequestBody)
                .when()
                .post("/customer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract();
    }




}