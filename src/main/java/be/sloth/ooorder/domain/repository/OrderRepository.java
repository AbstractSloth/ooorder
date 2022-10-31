package be.sloth.ooorder.domain.repository;

import be.sloth.ooorder.domain.order.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderRepository {

    private final Map<String, Order> orders;

    public OrderRepository() {
        orders = new HashMap<>();
    }

    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }
}
