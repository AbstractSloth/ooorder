package be.sloth.ooorder.domain.order;

import be.sloth.ooorder.domain.product.Item;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
    @SequenceGenerator(name = "order_item_seq", sequenceName = "order_item_seq", allocationSize = 1)
    private long id;

    @OneToOne
    @JoinColumn
    private Item item;
    private BigDecimal price;

    @JoinColumn
    @ManyToOne
    private OrderLine line;

    public OrderItem() {
    }

    public OrderItem(BigDecimal price, Item item) {
        this.item = item;
        this.price = price;
    }


    public Item getItem() {
        return item;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public long getId() {
        return id;
    }

    public OrderLine getLine() {
        return line;
    }
}
