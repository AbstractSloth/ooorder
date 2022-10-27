package be.sloth.ooorder.domain.repository;

import be.sloth.ooorder.domain.customer.Address;
import be.sloth.ooorder.domain.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static be.sloth.ooorder.domain.customer.Privilege.*;

@Component
public class CustomerRepository {

    private final Map<String, Customer> customers;


    public CustomerRepository(){
        customers = new HashMap<>();
        addInitialAdmin();
    }

    private void addInitialAdmin(){
        Customer customer = new Customer("Giga",
                "Chad",
                "gigachad@based.com",
                new Address("Based Boulevard","69","420","SneedVille"),
                "1111111111111"
                ,"password");

        customer.grantPrivilege(ADMIN);
        addCustomer(customer);
    }

   public void addCustomer(Customer customer){
        customers.put(customer.getId(), customer);
   }

   public Collection<Customer> getAll(){
        return  customers.values();
   }

   public Customer findByEmail(String eMail){
        return customers.values().stream()
                .filter(customer -> customer.geteMail().equals(eMail))
                .findFirst().orElseThrow();
   }
}
