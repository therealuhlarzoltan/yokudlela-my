package hu.yokudlela.yokudlela.controller;


import hu.yokudlela.yokudlela.domain.dto.order.*;
import hu.yokudlela.yokudlela.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("orders")
@RequestMapping("/orders")
@RequiredArgsConstructor
@Validated
@CrossOrigin
public class OrdersController {
    private final OrderService orderService;

    @Operation(summary = "Save Order", description = "Save order to database")
    @PostMapping
    public void save(@Valid @RequestBody OrderRequest order){
        orderService.save(order);
    }

    @Operation(summary = "Delete Order", description = "Delete order from database")
    @DeleteMapping
    public void delete(@Valid @RequestBody OrderIdRequest pdata) {
        orderService.delete(pdata.getId());
    }

    @Operation(summary = "Get All Orders", description = "Get all orders from database")
    @GetMapping
    public List<OrderResponse> getAll(){
        return orderService.getAll();
    }

    @Operation(summary = "Delete OrderGroup", description = "Delete orders by order group from database")
    @DeleteMapping("/group")
    public void delete(@Valid @RequestBody OrderGroupRequest pdata) {
        orderService.deleteOrderGroup(pdata.getOrderGroup());
    }

    @Operation(summary = "Get OrderGroup", description = "Get orders in order group by order group id")
    @PostMapping("/group")
    public OrderGroupResponse getOrderGroup(@Valid @RequestBody OrderGroupRequest orderGroup) {
        return orderService.getOrderGroup(orderGroup.getOrderGroup());
    }


    @Operation(summary = "Update Order State", description = "Update order state of an order entity")
    @PatchMapping
    public void updateState(@Valid @RequestBody OrderStateChangeRequest pdata) {
        orderService.updateState(pdata);
    }

    @Operation(summary = "Pay for whole order group", description = "Pay all orders in the group")
    @PatchMapping("/group/pay")
    public void payGroup(@Valid @RequestBody OrderGroupRequest pData){
        orderService.payGroup(pData.getOrderGroup());
    }

    @Operation(summary = "Pay for order", description = "Pay for a single order")
    @PatchMapping("/pay")
    public void pay(@Valid @RequestBody OrderIdRequest data){
        orderService.pay(data.getId());
    }

}
