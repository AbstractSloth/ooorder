package be.sloth.ooorder.domain.customer;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
    private long id;
    private String firstName;
    private String lastName;

    @Column(name = "email")
    private String mail;

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<CustomerPrivilege> privileges;

    private String password;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String mail, Address address, String phoneNumber, String password) {
        this.address = address;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public void grantPrivilege(Privilege privilege) {
        if(checkYourPrivilege(privilege))throw new IllegalArgumentException("this customer already has " + privilege);
        privileges.add(new CustomerPrivilege(this,privilege));
    }

    public String getMail() {
        return mail;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public boolean checkYourPrivilege(Privilege privilege) {
        return privileges.stream().anyMatch(customerPrivilege -> customerPrivilege.isThisPrivilege(privilege));
    }
}
