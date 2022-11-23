package be.sloth.ooorder.api.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderReceiptDTO {

    private long transactionId;

    private List<ReceiptItem> items;

    private String totalPrice;

    public OrderReceiptDTO(long transactionId) {
        this.transactionId = transactionId;
        this.items = new ArrayList<>();
    }

    public OrderReceiptDTO() {
    }

    public void addReceiptItem(ReceiptItem item) {
        items.add(item);
    }

    public long getTransactionId() {
        return transactionId;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
