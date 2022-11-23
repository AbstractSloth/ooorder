package be.sloth.ooorder.domain.product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    private long id;
    private String name;
    private String description;
    private BigDecimal priceInEuro;

    @OneToMany(mappedBy = "product")
    private List<Item> items;

    public Product() {
    }

    public Product(String name, String description, BigDecimal priceInEuro) {
        this.name = name;
        this.description = description;
        this.priceInEuro = priceInEuro;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPriceInEuro() {
        return priceInEuro;
    }


    public List<Item> getItems() {
        return items;
    }
}
