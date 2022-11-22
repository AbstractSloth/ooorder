package be.sloth.ooorder.domain.customer;

public class CustomerPrivilege {

    private Customer customer;
    private Privilege privilege;


    public CustomerPrivilege(Customer customer, Privilege privilege) {
        this.customer = customer;
        this.privilege = privilege;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Privilege getPrivilege() {
        return privilege;
    }
}
