package be.sloth.ooorder.service;

import be.sloth.ooorder.api.dto.RegisterCustomerDTO;
import be.sloth.ooorder.api.mapper.CustomerMapper;
import be.sloth.ooorder.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepo;
    private final ValidationService validation;

    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository customerRepo, ValidationService validation, CustomerMapper mapper) {
        this.customerRepo = customerRepo;
        this.validation = validation;
        this.mapper = mapper;
    }

    public void createCustomer(RegisterCustomerDTO toBeAdded) {
        validation.validateNewCustomer(toBeAdded);
        customerRepo.save(mapper.createNewCustomer(toBeAdded));
    }


}
