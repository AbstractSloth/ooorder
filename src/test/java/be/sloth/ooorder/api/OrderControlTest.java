package be.sloth.ooorder.api;

import be.sloth.ooorder.domain.product.Product;
import be.sloth.ooorder.domain.repository.ItemRepository;
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
class OrderControlTest {


    @LocalServerPort
    private int port;

    @Autowired
    private  ItemRepository itemRepo;



    @Test
    void placeOrder() {

        Product product1 = itemRepo.getCatalogue().stream().toList().get(0);

        Product product2 = itemRepo.getCatalogue().stream().toList().get(1);

        String requestBody = "[{\n" +
                "  \"product\": \""+ product1.getId()  +"\",\n" +
                "  \"amount\": 1 }, \n" +
                "{\n" +
                "  \"product\": \""+ product2.getId()  +"\",\n" +
                "  \"amount\": 10 } \n" +
                "]";




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
                .post("/order")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract();
    }

}