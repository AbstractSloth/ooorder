package be.sloth.ooorder.service;

import be.sloth.ooorder.api.dto.CustomerListDTO;
import be.sloth.ooorder.api.dto.RegisterCustomerDTO;
import be.sloth.ooorder.api.mapper.CustomerMapper;
import be.sloth.ooorder.domain.customer.Customer;
import be.sloth.ooorder.domain.customer.Privilege;
import be.sloth.ooorder.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static be.sloth.ooorder.domain.customer.Privilege.*;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepo;
    private final ValidationService validation;

    private final SecurityService security;

    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository customerRepo, ValidationService validation, SecurityService security, CustomerMapper mapper) {
        this.customerRepo = customerRepo;
        this.validation = validation;
        this.security = security;
        this.mapper = mapper;
    }

    public void createCustomer(RegisterCustomerDTO toBeAdded) {
        validation.validateNewCustomer(toBeAdded);
        customerRepo.save(mapper.createNewCustomer(toBeAdded));
    }

    public List<CustomerListDTO> getCustomerList(String auths){
        security.validateAuthorization(auths, ADMIN);
        return customerRepo.findAll().stream()
                .map(mapper::mapListCustomer).toList();
    }

    public CustomerListDTO getThatCustomer(String auths, long id){
        security.validateAuthorization(auths, ADMIN);
        Customer customer = customerRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("no such customer!"));
        return mapper.mapListCustomer(customer);
    }


}
