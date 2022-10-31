package be.sloth.ooorder.api.dto;

public class RegisterCustomerDTO {


    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String eMail;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String password;

    public RegisterCustomerDTO(String firstName, String lastName, String phoneNumber, String eMail, String street, String houseNumber, String postalCode, String city, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getPassword() {
        return password;
    }
}
