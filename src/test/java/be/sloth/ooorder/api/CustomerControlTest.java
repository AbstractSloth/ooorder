package be.sloth.ooorder.api;

import be.sloth.ooorder.api.dto.RegisterCustomerDTO;
import io.restassured.http.ContentType;
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
                .statusCode(HttpStatus.CREATED.value());
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
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void viewCustomerList() {


        given()
                .baseUri("http://localhost")
                .port(port)
                .auth()
                .preemptive()
                .basic("gigachad@based.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Content-type", "application/json")
                .when()
                .get("/customer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void viewOneCustomer() {


        given()
                .baseUri("http://localhost")
                .port(port)
                .auth()
                .preemptive()
                .basic("gigachad@based.com", "password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .header("Content-type", "application/json")
                .when()
                .get("/customer/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }




}