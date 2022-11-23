package be.sloth.ooorder.api;

import be.sloth.ooorder.domain.repository.CustomerRepository;
import be.sloth.ooorder.service.ItemService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductControlTest {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemService service;

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

        System.out.println(customerRepository.count());

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
    void registerProduct_unauthorized() {


        given()
                .baseUri("http://localhost")
                .port(port)
                .auth()
                .preemptive()
                .basic("gigachad@based.com", "assword")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/catalogue")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .extract();
    }

    @Test
    void registerProduct_forbidden() {


        given()
                .baseUri("http://localhost")
                .port(port)
                .auth()
                .preemptive()
                .basic("soijack@cringe.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/catalogue")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .extract();
    }

    @Test
    void viewCatalogue() {

        service.registerDummyProduct();

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