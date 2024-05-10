package hu.yokudlela.yokudlela.service;

import hu.yokudlela.yokudlela.domain.dto.order.OrderGroupResponse;
import hu.yokudlela.yokudlela.domain.dto.order.OrderRequest;
import hu.yokudlela.yokudlela.domain.dto.order.OrderResponse;
import hu.yokudlela.yokudlela.domain.dto.order.OrderStateChangeRequest;
import hu.yokudlela.yokudlela.domain.entity.Order;
import hu.yokudlela.yokudlela.domain.entity.Table;
import hu.yokudlela.yokudlela.domain.enumeration.OrderState;
import hu.yokudlela.yokudlela.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;


    public void save(OrderRequest order) {
        var orderEntiy = mapper.map(order, Order.class);
        orderEntiy.setOrderStatus(OrderState.IN_PROGRESS);
        orderRepository.save(orderEntiy);
    }

    public void delete(String id) {
        orderRepository.deleteById(id);
    }

    public OrderGroupResponse getOrderGroup(String id) {
       var orders = orderRepository.findByOrderGroup(id);
       var mapped = orders.stream().map(o -> mapper.map(o, OrderResponse.class)).collect(Collectors.toList());
       return  OrderGroupResponse.builder()
               .orderGroup(id)
               .orders(mapped)
               .totalPrice(orders.stream().mapToDouble(o -> o.getQuantity() * o.getMenuItem().getPrice()).sum())
               .build();
    }

    public void deleteOrderGroup(String orderGroup) {
        var orders = orderRepository.findByOrderGroup(orderGroup);
        for (var e : orders)
            orderRepository.deleteById(e.getId());
    }


    public void updateState(OrderStateChangeRequest pdata) {
        var opt = orderRepository.findById(pdata.getId());
        var strEnum = pdata.getOrderState();
        var entity = opt.get();
        entity.setOrderStatus(Arrays.stream(OrderState.values()).filter(e -> e.getName().equals(strEnum)).findFirst().get());
        orderRepository.save(entity);
    }

    public void payGroup(String orderGroup) {
        var allOrders = orderRepository.findByOrderGroup(orderGroup);
        for (var e : allOrders)
            e.setOrderStatus(OrderState.PAID);
        orderRepository.saveAll(allOrders);
    }

    public void pay(String id) {
        var opt = orderRepository.findById(id);
        var e = opt.get();
        e.setOrderStatus(OrderState.PAID);
        orderRepository.save(e);
    }

    public List<OrderResponse> getAll() {
        var entities = new ArrayList<Order>();
        orderRepository.findAll().forEach(entities::add);
        return entities.stream().map(e -> mapper.map(e, OrderResponse.class)).collect(Collectors.toList());
    }
}
