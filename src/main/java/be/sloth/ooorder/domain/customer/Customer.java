package be.sloth.ooorder.domain.customer;

import java.util.*;

public class Customer {

    private final String id;
    private String firstName;
    private String lastName;
    private String eMail;

    private Address address;
    private String phoneNumber;

    private final Set<Privilege> privileges = new HashSet<>();

    private String password;

    public Customer(String firstName, String lastName, String eMail, Address address, String phoneNumber, String password) {
        this.address = address;
        this.password = password;
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public void grantPrivilege(Privilege privilege){
        privileges.add(privilege);
    }

    public String geteMail() {
        return eMail;
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public boolean checkYourPrivilege(Privilege privilege){
        return privileges.contains(privilege);
    }
}
