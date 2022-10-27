package be.sloth.ooorder.api;


import be.sloth.ooorder.api.dto.RegisterCustomerDTO;
import be.sloth.ooorder.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path="customer")
public class CustomerControl {

    private final CustomerService serviceChan;


    public CustomerControl(CustomerService serviceChan) {
        this.serviceChan = serviceChan;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void registerCustomer(@RequestBody RegisterCustomerDTO toBeAdded){
        serviceChan.createCustomer(toBeAdded);
    }

}
