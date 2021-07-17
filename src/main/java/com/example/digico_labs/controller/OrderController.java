package com.example.digico_labs.controller;

import com.example.digico_labs.repository.model.OrderCreateRequest;
import com.example.digico_labs.repository.model.Orders;
import com.example.digico_labs.repository.model.user.AuthenticationUser;
import com.example.digico_labs.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/api")
@RestController
@CrossOrigin("*")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{userId}/getAllOrders")
    public ResponseEntity<Map<String,Object>> getAllOrderDetails(@PathVariable("userId") String userId) {
        List<Orders> userData = orderService.getOders(userId);
        Map<String, Object> pagedResponse = new HashMap<>();
        pagedResponse.put("orderData", userData);
        return ResponseEntity.ok(pagedResponse);
    }

    @PostMapping("/{userId}/saveOrder")
    public ResponseEntity<String> saveNewOrders(@PathVariable("userId") String userId,
                                                @RequestBody OrderCreateRequest orderCreateRequest) {
        try {
            orderService.createNewOrder(userId,orderCreateRequest);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.ok("Error occurred");
        }
    }
}
