package be.sloth.ooorder.domain.order;

import be.sloth.ooorder.domain.customer.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "oorder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oorder_seq")
    @SequenceGenerator(name = "oorder_seq", sequenceName = "oorder_seq", allocationSize = 1)
    private long id;

    @JoinColumn
    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    public Order() {
    }

    public Order(Customer customer) {
        this.customer = customer;
        this.orderLines = new ArrayList<>();
    }

    public void addOrderLine(OrderLine line) {
        orderLines.add(line);
    }

    public long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }
}
