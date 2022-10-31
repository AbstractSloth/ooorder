package be.sloth.ooorder.service;

import be.sloth.ooorder.api.dto.OrderDTO;
import be.sloth.ooorder.api.dto.OrderReceiptDTO;
import be.sloth.ooorder.api.mapper.OrderMapper;
import be.sloth.ooorder.domain.customer.Customer;
import be.sloth.ooorder.domain.order.Order;
import be.sloth.ooorder.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final SecurityService security;

    private final ValidationService validation;

    private final OrderMapper mapper;


    public OrderService(OrderRepository orderRepo, SecurityService security, ValidationService validation, OrderMapper mapper) {
        this.orderRepo = orderRepo;
        this.security = security;
        this.validation = validation;
        this.mapper = mapper;
    }

    public OrderReceiptDTO placeOrder(List<OrderDTO> orders, String auths) {
        Customer customer = security.validateCustomer(auths);
        validation.validatePlacedOrders(orders);
        Order order = mapper.placeOrder(orders, customer.getId());
        orderRepo.addOrder(order);
        return mapper.makeReceipt(order);
    }
}
