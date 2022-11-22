package be.sloth.ooorder.api.mapper;


import be.sloth.ooorder.api.dto.OrderReceiptDTO;
import be.sloth.ooorder.api.dto.ReceiptItem;
import be.sloth.ooorder.domain.order.Order;
import be.sloth.ooorder.domain.order.OrderItem;
import be.sloth.ooorder.domain.order.OrderLine;
import be.sloth.ooorder.domain.repository.ItemRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderUpMapper {

    private final ItemRepository itemRepo;
    private BigDecimal totalPrice;

    public OrderUpMapper(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    public OrderReceiptDTO makeReceipt(Order order) {
        OrderReceiptDTO receipt = new OrderReceiptDTO(order.getId());
        totalPrice = new BigDecimal("0.00");
        order.getOrderLines().forEach(orderLine -> receipt.addReceiptItem(makeReceiptItem(orderLine)));
        receipt.setTotalPrice(makePriceString(totalPrice));
        return receipt;
    }

    private ReceiptItem makeReceiptItem(OrderLine line) {
        OrderItem orderItem = line.getItems().get(0);
        return new ReceiptItem(findProductName(orderItem.getItem()),
                line.getDeliveryDate(),
                makePriceString(orderItem.getPrice()),
                line.getItems().size(),
                makePriceString(calculateSubtotal(line,orderItem.getPrice())));
    }

    private String findProductName(String itemId) {
        return itemRepo.getItemProduct(itemId).getName();
    }

    private String makePriceString(BigDecimal price) {
        return price.toString() + " €";
    }

    private BigDecimal calculateSubtotal(OrderLine line, BigDecimal price) {
        BigDecimal multiplicand = new BigDecimal(line.getItems().size());
        BigDecimal subTotal = price.multiply(multiplicand);
        totalPrice = totalPrice.add(subTotal);
        return subTotal;
    }
}
