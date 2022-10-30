package be.sloth.ooorder.domain.order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private final String id;
    private final String customer;
    private final List<OrderItem> orderItems = new ArrayList<OrderItem>();

    public Order(String customer) {
        this.id = UUID.randomUUID().toString();
        this.customer = customer;

    }

    public void addOrderItem(OrderItem item){
        orderItems.add(item);
    }

    public String getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
