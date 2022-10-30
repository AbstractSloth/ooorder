package be.sloth.ooorder.domain.product;

import java.util.UUID;

import static be.sloth.ooorder.domain.product.ItemStatus.*;

public class Item {

    private final String id;
    private final String product;
    private ItemStatus status;

    public Item(String product) {
        this.id = UUID.randomUUID().toString();
        this.product = product;
        this.status = AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }
}
