package be.sloth.ooorder.api;

import be.sloth.ooorder.api.dto.RegisterCustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class CustomerControlTest {






    @LocalServerPort
    private int port;


    @Test
    void registerCustomer() {

        given()
                .baseUri("http://localhost")
                .port(port)
                .header("Content-type", "application/json")
                .and()
                .body(new RegisterCustomerDTO("a","b","1","x@x.x",
                        "abcStreet","11","1234","Brussels","ASSWORD"))
                .when()
                .post("/customer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract();
    }

    @Test
    void registerCustomerWithoutEmail() {


        given()
                .baseUri("http://localhost")
                .port(port)
                .header("Content-type", "application/json")
                .and()
                .body(new RegisterCustomerDTO("a","b","1","",
                        "abcStreet","11","1234","Brussels","ASSWORD"))
                .when()
                .post("/customer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract();
    }




}