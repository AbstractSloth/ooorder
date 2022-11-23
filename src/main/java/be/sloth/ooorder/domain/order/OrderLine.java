package be.sloth.ooorder.domain.order;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "line_seq")
    @SequenceGenerator(name = "line_seq", sequenceName = "line_seq", allocationSize = 1)
    private long id;

    @JoinColumn(name = "oorder")
    @ManyToOne
    private Order order;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @OneToMany(mappedBy = "line")
    private List<OrderItem> items;

    public OrderLine() {
    }

    public OrderLine(Order order, LocalDate deliveryDate) {
        this.order = order;
        this.deliveryDate = deliveryDate;
        this.items = new ArrayList<>();
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
