package be.sloth.ooorder.domain.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderItem {

    private final List<String> items;
    private final BigDecimal pricePerUnit;
    private LocalDate deliveryDate;

    public OrderItem(BigDecimal pricePerUnit, LocalDate deliveryDate) {
        this.items = new ArrayList<>();
        this.pricePerUnit = pricePerUnit;
        this.deliveryDate = deliveryDate;
    }

    public void addItem(String itemId) {
        items.add(itemId);
    }

    public List<String> getItems() {
        return items;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

}
