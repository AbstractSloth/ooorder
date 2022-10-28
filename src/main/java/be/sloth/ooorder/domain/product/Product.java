package be.sloth.ooorder.domain.product;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {

    private final String id;
    private String name;
    private String description;
    private BigDecimal priceInEuro;

    public Product(String name, String description, BigDecimal priceInEuro) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.priceInEuro = priceInEuro;
    }

    public String getId() {
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
}
