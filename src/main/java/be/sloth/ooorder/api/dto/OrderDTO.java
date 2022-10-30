package be.sloth.ooorder.api.dto;

public class OrderDTO {

    private final String product;
    private final int amount;

    public OrderDTO(String product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public String getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }
}
