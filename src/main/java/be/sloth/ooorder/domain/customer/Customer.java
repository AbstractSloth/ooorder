package be.sloth.ooorder.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer {

    private final String id;
    private String firstName;
    private String lastName;
    private String eMail;
    private String phoneNumber;

    private final List<Privilege> privileges = new ArrayList<>();

    public Customer( String firstName, String lastName, String eMail, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
    }


}
