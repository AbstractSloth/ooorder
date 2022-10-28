package be.sloth.ooorder.api.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.RoundingMode.*;

public class RegisterProductDTO {

    private final String name;
    private final String description;
    private final String priceInEuro;
    private final String stock;

    public RegisterProductDTO(String name, String description, String priceInEuro, String stock) {
        System.out.println("building dto...");
        this.name = name;
        System.out.println("name...");
        this.description = description;
        System.out.println("description...");
        this.priceInEuro = priceInEuro;
        System.out.println("price...");
        this.stock = stock;
        System.out.println("stock...");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPriceInEuro() {

        try {
            return new BigDecimal(priceInEuro).setScale(2, HALF_EVEN);
        } catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException("Could not parse price!");
        }
    }

    public Integer getStock() {
        return Integer.parseInt(stock);
    }
}
