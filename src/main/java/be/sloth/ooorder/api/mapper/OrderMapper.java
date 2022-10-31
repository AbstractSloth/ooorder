package be.sloth.ooorder.api.mapper;

import be.sloth.ooorder.api.dto.OrderDTO;
import be.sloth.ooorder.api.dto.OrderReceiptDTO;
import be.sloth.ooorder.api.dto.ReceiptItem;
import be.sloth.ooorder.domain.order.Order;
import be.sloth.ooorder.domain.order.OrderItem;
import be.sloth.ooorder.domain.product.Item;
import be.sloth.ooorder.domain.product.ItemStatus;
import be.sloth.ooorder.domain.product.Product;
import be.sloth.ooorder.domain.repository.ItemRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static be.sloth.ooorder.domain.product.ItemStatus.*;

@Component
public class OrderMapper {

    private final ItemRepository itemRepo;

    private BigDecimal totalPrice;

    public OrderMapper(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    public Order placeOrder(List<OrderDTO> orders, String customerId) {
        Order order = new Order(customerId);
        orders.forEach(dto -> order.addOrderItem(createOrderItem(dto)));
        return order;
    }

    private OrderItem createOrderItem(OrderDTO dto) {
        int amountInStock = itemRepo.getAmountInStock(dto.getProduct());
        Product product = itemRepo.getProductById(dto.getProduct());
        OrderItem orderItem = new OrderItem(product.getPriceInEuro(), setDeliveryDate(dto.getAmount(), amountInStock));
        for (int i = 0; i < dto.getAmount(); i++) {

            if (i < amountInStock) {
                Item item = itemRepo.getFirstInStock(dto.getProduct());
                orderItem.addItem(item.getId());
                item.setStatus(SOLD);
            } else {
                orderItem.addItem(reserveItem(dto.getProduct()));
            }
        }

        return orderItem;
    }

    private LocalDate setDeliveryDate(int ordered, int inStock) {
        if (inStock > ordered) return LocalDate.now().plusDays(1);

        return LocalDate.now().plusDays(7);
    }

    private String reserveItem(String productId) {
        Item item = new Item(productId);
        itemRepo.addStock(item);
        item.setStatus(RESERVED);

        return item.getId();
    }

    public OrderReceiptDTO makeReceipt(Order order) {
        OrderReceiptDTO receipt = new OrderReceiptDTO(order.getId());
        totalPrice = new BigDecimal("0.00");
        order.getOrderItems().forEach(orderItem -> receipt.addReceiptItem(makeReceiptItem(orderItem)));
        receipt.setTotalPrice(makePriceString(totalPrice));
        return receipt;
    }

    private ReceiptItem makeReceiptItem(OrderItem orderItem) {

        return new ReceiptItem(findProductName(orderItem.getItems().get(0)),
                orderItem.getDeliveryDate(),
                makePriceString(orderItem.getPricePerUnit()),
                orderItem.getItems().size(),
                makePriceString(calculateSubtotal(orderItem)));
    }

    private String findProductName(String itemId) {
        return itemRepo.getItemProduct(itemId).getName();
    }

    private String makePriceString(BigDecimal price) {
        return price.toString() + " €";
    }

    private BigDecimal calculateSubtotal(OrderItem item) {
        BigDecimal multiplicand = new BigDecimal(item.getItems().size());
        BigDecimal subTotal = item.getPricePerUnit().multiply(multiplicand);
        totalPrice = totalPrice.add(subTotal);
        return subTotal;
    }
}
