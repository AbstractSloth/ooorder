package be.sloth.ooorder.api.dto;

import java.time.LocalDate;

public class ReceiptItem {

    private final String product;

    private final LocalDate delivery;

    private final String price;
    private final int amount;
    private final String subTotal;

    public ReceiptItem(String product, LocalDate delivery, String price, int amount, String subTotal) {
        this.product = product;
        this.delivery = delivery;
        this.price = price;
        this.amount = amount;
        this.subTotal = subTotal;
    }

    public String getProduct() {
        return product;
    }

    public LocalDate getDelivery() {
        return delivery;
    }

    public String getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public String getSubTotal() {
        return subTotal;
    }
}
