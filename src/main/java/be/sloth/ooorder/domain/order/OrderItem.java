package be.sloth.ooorder.domain.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderItem {

    private final String item;
    private final BigDecimal price;


    private OrderLine line;

    public OrderItem(BigDecimal price, String item) {
        this.item = item;
        this.price = price;
    }


    public String getItem() {
        return item;
    }

    public BigDecimal getPrice() {
        return price;
    }



}
