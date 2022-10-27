package be.sloth.ooorder.service;

import be.sloth.ooorder.api.dto.RegisterCustomerDTO;
import be.sloth.ooorder.domain.customer.Customer;
import be.sloth.ooorder.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    private final CustomerRepository customerRepo;

    public ValidationService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public void validateNewCustomer(RegisterCustomerDTO toBeAdded) {
        validateEmail(toBeAdded.geteMail());
        assertNotNullOrBlank(toBeAdded.getLastName(), "Last name");
        assertNotNullOrBlank(toBeAdded.getCity(), "City");
        assertNotNullOrBlank(toBeAdded.getPassword(), "Password");
        customerRepo.getAll().forEach(existing -> validateThatCustomer(toBeAdded, existing));
    }

    void validateThatCustomer(RegisterCustomerDTO added, Customer existing) {
        if (added.geteMail().equals(existing.geteMail()))
            throw new IllegalArgumentException("E mail is not unique!");
    }


    public void assertNotNullOrBlank(String value, String field) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " can not be empty!");
    }


    public void validateEmail(String eMail) {
        if (eMail == null || !eMail.matches("^[A-z0-9]+@[A-z0-9]+\\.[A-z0-9]+$"))
            throw new IllegalArgumentException("E mail does not conform to format!");
    }
}
