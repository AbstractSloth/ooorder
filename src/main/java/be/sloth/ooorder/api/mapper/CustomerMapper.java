package be.sloth.ooorder.api.mapper;


import be.sloth.ooorder.api.dto.CustomerListDTO;
import be.sloth.ooorder.api.dto.RegisterCustomerDTO;
import be.sloth.ooorder.domain.customer.Address;
import be.sloth.ooorder.domain.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer createNewCustomer(RegisterCustomerDTO toBeAdded) {

        return new Customer(toBeAdded.getFirstName(),
                toBeAdded.getLastName(),
                toBeAdded.geteMail(),
                createAddress(toBeAdded),
                toBeAdded.getPhoneNumber(),
                toBeAdded.getPassword());
    }


    private Address createAddress(RegisterCustomerDTO toBeAdded) {
        return new Address(toBeAdded.getStreet(),
                toBeAdded.getHouseNumber(),
                toBeAdded.getPostalCode(),
                toBeAdded.getCity());
    }

    public CustomerListDTO mapListCustomer(Customer customer){
        return new CustomerListDTO(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPrivileges());
    }
}
