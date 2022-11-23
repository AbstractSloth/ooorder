package be.sloth.ooorder.api;


import be.sloth.ooorder.api.dto.CustomerListDTO;
import be.sloth.ooorder.api.dto.ProductDTO;
import be.sloth.ooorder.api.dto.RegisterCustomerDTO;
import be.sloth.ooorder.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "customer")
@CrossOrigin
public class CustomerControl {

    private final CustomerService serviceChan;


    public CustomerControl(CustomerService serviceChan) {
        this.serviceChan = serviceChan;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void registerCustomer(@RequestBody RegisterCustomerDTO toBeAdded) {
        serviceChan.createCustomer(toBeAdded);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public List<CustomerListDTO> showCustomers(@RequestHeader String authorization) {
        return serviceChan.getCustomerList(authorization);
    }

    @GetMapping(path = "/{id}",produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public CustomerListDTO showThatCustomers(@RequestHeader String authorization,@PathVariable long id) {
        return serviceChan.getThatCustomer(authorization,id);
    }

}
