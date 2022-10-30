package be.sloth.ooorder.api.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderReceiptDTO {

    private final String transactionId;

    private final List<ReceiptItem> items;

    private String totalPrice;

    public OrderReceiptDTO(String transactionId) {
        this.transactionId = transactionId;
        this.items = new ArrayList<>();
    }

    public void addReceiptItem(ReceiptItem item){
        items.add(item);
    }

    public String getTransactionId() {
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
