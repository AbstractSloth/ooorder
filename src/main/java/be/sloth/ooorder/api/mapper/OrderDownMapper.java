package be.sloth.ooorder.api.mapper;

import be.sloth.ooorder.api.dto.OrderDTO;
import be.sloth.ooorder.api.dto.OrderReceiptDTO;
import be.sloth.ooorder.api.dto.ReceiptItem;
import be.sloth.ooorder.domain.order.Order;
import be.sloth.ooorder.domain.order.OrderItem;
import be.sloth.ooorder.domain.order.OrderLine;
import be.sloth.ooorder.domain.product.Item;
import be.sloth.ooorder.domain.product.Product;
import be.sloth.ooorder.domain.repository.ItemRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static be.sloth.ooorder.domain.product.ItemStatus.*;

@Component
public class OrderDownMapper {

    private final ItemRepository itemRepo;

    private BigDecimal totalPrice;

    public OrderDownMapper(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    public Order placeOrder(List<OrderDTO> orders, String customerId) {
        Order order = new Order(customerId);
        orders.forEach(dto -> order.addOrderLine(createOrderLine(dto,order)));
        return order;
    }

    private OrderLine createOrderLine(OrderDTO dto,Order order) {
        int amountInStock = itemRepo.getAmountInStock(dto.getProduct());
        Product product = itemRepo.getProductById(dto.getProduct());
        OrderLine line = new OrderLine(order,setDeliveryDate(dto.getAmount(),amountInStock));
        for (int i = 0; i < dto.getAmount(); i++) {
            Item item;
            if (i < amountInStock) {
                item = itemRepo.getFirstInStock(dto.getProduct());
                item.setStatus(SOLD);
            } else {
                item = reserveItem(dto.getProduct());
            }
            line.addOrderItem(new OrderItem(product.getPriceInEuro(),item.getId()));
        }

        return line;
    }

    private LocalDate setDeliveryDate(int ordered, int inStock) {
        if (inStock > ordered) return LocalDate.now().plusDays(1);

        return LocalDate.now().plusDays(7);
    }

    private Item reserveItem(String productId) {
        Item item = new Item(productId);
        itemRepo.addStock(item);
        item.setStatus(RESERVED);

        return item;
    }


}
