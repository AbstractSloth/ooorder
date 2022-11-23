package be.sloth.ooorder.domain.customer;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity
@IdClass(CustomerPrivilege.class)
public class CustomerPrivilege implements Serializable {

    @Id
    @JoinColumn
    @ManyToOne
    private Customer customer;
    @Id
    @Enumerated(EnumType.STRING)
    private Privilege privilege;

    public CustomerPrivilege() {
    }

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

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public boolean isThisPrivilege(Privilege privilege){
        return this.privilege.equals(privilege);
    }
}
