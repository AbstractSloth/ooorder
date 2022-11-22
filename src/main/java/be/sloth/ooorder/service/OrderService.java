package be.sloth.ooorder.service;

import be.sloth.ooorder.api.dto.OrderDTO;
import be.sloth.ooorder.api.dto.OrderReceiptDTO;
import be.sloth.ooorder.api.mapper.OrderDownMapper;
import be.sloth.ooorder.api.mapper.OrderUpMapper;
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

    private final OrderDownMapper downMapper;

    private final OrderUpMapper upMapper;


    public OrderService(OrderRepository orderRepo, SecurityService security, ValidationService validation, OrderDownMapper downMapper, OrderUpMapper upMapper) {
        this.orderRepo = orderRepo;
        this.security = security;
        this.validation = validation;
        this.downMapper = downMapper;
        this.upMapper = upMapper;
    }

    public OrderReceiptDTO placeOrder(List<OrderDTO> orders, String auths) {
        Customer customer = security.validateCustomer(auths);
        validation.validatePlacedOrders(orders);
        Order order = downMapper.placeOrder(orders, customer.getId());
        orderRepo.addOrder(order);
        return upMapper.makeReceipt(order);
    }
}
