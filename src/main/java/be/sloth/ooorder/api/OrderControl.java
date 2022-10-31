package be.sloth.ooorder.api;

import be.sloth.ooorder.api.dto.OrderDTO;
import be.sloth.ooorder.api.dto.OrderReceiptDTO;
import be.sloth.ooorder.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "order")
@CrossOrigin
public class OrderControl {

    private final OrderService service;

    public OrderControl(OrderService service) {
        this.service = service;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public OrderReceiptDTO placeOrder(@RequestHeader String authorization, @RequestBody List<OrderDTO> order) {
        return service.placeOrder(order, authorization);
    }
}
