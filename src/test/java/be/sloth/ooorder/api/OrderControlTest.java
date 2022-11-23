package be.sloth.ooorder.api;

import be.sloth.ooorder.api.dto.OrderReceiptDTO;
import be.sloth.ooorder.domain.product.ItemStatus;
import be.sloth.ooorder.domain.product.Product;
import be.sloth.ooorder.domain.repository.ItemRepository;
import be.sloth.ooorder.domain.repository.ProductRepository;
import be.sloth.ooorder.service.ItemService;
import io.restassured.http.ContentType;
import io.swagger.v3.core.util.Json;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static be.sloth.ooorder.domain.product.ItemStatus.*;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderControlTest {


    @LocalServerPort
    private int port;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private ItemService service;



    @Test
    void placeOrder() {

        service.registerDummyProduct();


        String requestBody = "[{\n" +
                "  \"product\": "+ 1  +",\n" +
                "  \"amount\": 1 }, \n" +
                "{\n" +
                "  \"product\": \""+ 2  +"\",\n" +
                "  \"amount\": 10 } \n" +
                "]";





        OrderReceiptDTO receipt = given()
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
                .extract().body().as(OrderReceiptDTO.class);

        Json.prettyPrint(receipt);

        Product product = productRepo.getReferenceById(2L);
        Assertions.assertEquals(5,itemRepo.countItemsByProductAndStatus(product, RESERVED));
    }


    @Test
    void restockAfterReservation() {

        service.registerDummyProduct();


        String requestBody = "[{\n" +
                "  \"product\": \""+ 2  +"\",\n" +
                "  \"amount\": 10 } \n" +
                "]";





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
                .statusCode(HttpStatus.CREATED.value());


        Product product = productRepo.getReferenceById(2L);
        service.replenishStock(10,product);
        Assertions.assertEquals(5,itemRepo.countItemsByProductAndStatus(product, AVAILABLE));
    }

}