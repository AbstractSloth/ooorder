package be.sloth.ooorder.service;

import be.sloth.ooorder.domain.customer.Credentials;
import be.sloth.ooorder.domain.customer.Customer;
import be.sloth.ooorder.domain.customer.Privilege;
import be.sloth.ooorder.domain.repository.CustomerRepository;
import be.sloth.ooorder.service.exception.BadCredentialsException;
import be.sloth.ooorder.service.exception.NoPrivilegeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.NoSuchElementException;

@Service
public class SecurityService {

    private final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    private final CustomerRepository customerRepository;

    public SecurityService(CustomerRepository personRepository) {
        this.customerRepository = personRepository;
    }

    public void validateAuthorization(String authorization, Privilege privilege) {

        Customer customer = validateCustomer(authorization);


        if (!customer.checkYourPrivilege(privilege)) {
            logger.error("User " + customer.geteMail() + " does not have privilege " + privilege);
            throw new NoPrivilegeException(privilege);
        }

    }

    public Customer validateCustomer(String auths){
        Credentials credentials = decodeCredentials(auths);

        Customer customer;
        try {
            customer = customerRepository.findByEmail(credentials.geteMail());
        } catch (NoSuchElementException e) {
            logger.error("Unknown user with the username " + credentials.geteMail());
            throw new BadCredentialsException();
        }


        if(!customer.checkPassword(credentials.getPassword())) {
            logger.error("Password does not match for user " + credentials.geteMail());
            throw new BadCredentialsException();
        }

        return customer;

    }


    private Credentials decodeCredentials(String authorization) {
        String decodedAuth = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String eMail = decodedAuth.substring(0, decodedAuth.indexOf(":"));
        String password = decodedAuth.substring(decodedAuth.indexOf(":") + 1);
        return new Credentials(eMail, password);
    }
}
