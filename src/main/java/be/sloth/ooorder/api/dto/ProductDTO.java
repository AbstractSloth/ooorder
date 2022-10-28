package be.sloth.ooorder.api.dto;

public class ProductDTO {

    private final String id;
    private final String name;
    private final String description;
    private final String price;
    private final String stock;

    public ProductDTO(String id, String name, String description, String price, String stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
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

    public String getPrice() {
        return price;
    }

    public String getStock() {
        return stock;
    }
}
