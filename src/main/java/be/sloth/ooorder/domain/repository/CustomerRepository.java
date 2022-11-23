package be.sloth.ooorder.domain.repository;

import be.sloth.ooorder.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {


    public Customer findByMail(String eMail);

    public boolean existsByMail(String eMail);

}
