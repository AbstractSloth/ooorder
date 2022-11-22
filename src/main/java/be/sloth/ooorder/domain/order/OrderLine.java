package be.sloth.ooorder.domain.order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderLine {

    private Order order;

    private LocalDate deliveryDate;

    private List<OrderItem> items = new ArrayList<>();

    public OrderLine(Order order, LocalDate deliveryDate) {
        this.order = order;
        this.deliveryDate = deliveryDate;
    }

    public void addOrderItem(OrderItem item){
        items.add(item);
    }

    public Order getOrder() {
        return order;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}
