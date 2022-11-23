package be.sloth.ooorder.api.dto;

public class OrderDTO {

    private final long product;
    private final int amount;

    public OrderDTO(long product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public long getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }
}
