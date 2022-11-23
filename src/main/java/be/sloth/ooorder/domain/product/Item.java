package be.sloth.ooorder.domain.product;

import javax.persistence.*;
import java.util.UUID;

import static be.sloth.ooorder.domain.product.ItemStatus.*;

@Entity
@Table
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @SequenceGenerator(name = "item_seq", sequenceName = "item_seq", allocationSize = 1)
    private long id;

    @JoinColumn
    @ManyToOne
    private Product product;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    public Item() {
    }

    public Item(Product product) {

        this.product = product;
        this.status = AVAILABLE;
    }

    public long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }
}
