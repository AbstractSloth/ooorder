package be.sloth.ooorder.api.dto;

import be.sloth.ooorder.domain.customer.Privilege;

import java.util.List;

public class CustomerListDTO {

    private long id;

    private String firstName;

    private String lastName;

    private List<Privilege> privileges;

    public CustomerListDTO(long id, String firstName, String lastName, List<Privilege> privileges) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.privileges = privileges;
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

    public List<Privilege> getPrivileges() {
        return privileges;
    }
}
