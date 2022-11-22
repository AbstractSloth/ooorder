package be.sloth.ooorder.domain.order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private final String id;
    private final String customer;
    private final List<OrderLine> orderLines = new ArrayList<>();

    public Order(String customer) {
        this.id = UUID.randomUUID().toString();
        this.customer = customer;

    }

    public void addOrderLine(OrderLine line) {
        orderLines.add(line);
    }

    public String getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }
}
