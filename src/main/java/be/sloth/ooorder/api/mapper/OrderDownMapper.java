package be.sloth.ooorder.api.mapper;

import be.sloth.ooorder.api.dto.OrderDTO;
import be.sloth.ooorder.domain.customer.Customer;
import be.sloth.ooorder.domain.order.Order;
import be.sloth.ooorder.domain.order.OrderItem;
import be.sloth.ooorder.domain.order.OrderLine;
import be.sloth.ooorder.domain.product.Item;
import be.sloth.ooorder.domain.product.Product;
import be.sloth.ooorder.domain.repository.ItemRepository;
import be.sloth.ooorder.domain.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static be.sloth.ooorder.domain.product.ItemStatus.*;

@Component
public class OrderDownMapper {

    private final ProductRepository productRepo;
    private final ItemRepository itemRepo;


    public OrderDownMapper(ProductRepository productRepo, ItemRepository itemRepo) {
        this.productRepo = productRepo;
        this.itemRepo = itemRepo;
    }

    public Order placeOrder(List<OrderDTO> orders, Customer customer) {
        Order order = new Order(customer);
        orders.forEach(dto -> order.addOrderLine(createOrderLine(dto,order)));
        return order;
    }

    private OrderLine createOrderLine(OrderDTO dto,Order order) {
        Product product = productRepo.getReferenceById(dto.getProduct());
        int amountInStock = itemRepo.countItemsByProductAndStatus(product,AVAILABLE);

        OrderLine line = new OrderLine(order,setDeliveryDate(dto.getAmount(),amountInStock));
        for (int i = 0; i < dto.getAmount(); i++) {
            Item item;
            if (i < amountInStock) {
                item = itemRepo.findFirstByProductAndStatus(product,AVAILABLE);
                item.setStatus(SOLD);
                itemRepo.save(item);
            } else {
                item = reserveItem(product);
            }
            line.addOrderItem(new OrderItem(product.getPriceInEuro(),item));
        }

        return line;
    }

    private LocalDate setDeliveryDate(int ordered, int inStock) {
        if (inStock > ordered) return LocalDate.now().plusDays(1);

        return LocalDate.now().plusDays(7);
    }

    private Item reserveItem(Product product) {
        Item item = new Item(product);
        item.setStatus(RESERVED);
        itemRepo.save(item);
        return item;
    }


}
