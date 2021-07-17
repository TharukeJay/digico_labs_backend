package com.example.digico_labs.controller;

import com.example.digico_labs.repository.model.OrderCreateRequest;
import com.example.digico_labs.repository.model.Orders;
import com.example.digico_labs.repository.model.user.AuthenticationUser;
import com.example.digico_labs.service.EmailService;
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
    private EmailService emailService;

    public OrderController(OrderService orderService, EmailService emailService) {
        this.orderService = orderService;
        this.emailService = emailService;
    }

    @GetMapping("/{userId}/getAllOrders")
    public ResponseEntity<Map<String,Object>> getAllOrderDetails(@PathVariable("userId") String userId) {
        List<Orders> userData = orderService.getOrders(userId);
        Map<String, Object> pagedResponse = new HashMap<>();
        pagedResponse.put("orderData", userData);
        return ResponseEntity.ok(pagedResponse);
    }

    @PostMapping("/{userId}/saveOrder")
    public ResponseEntity<String> saveNewOrders(@PathVariable("userId") String userId,
                                                @RequestBody Orders orderCreateRequest) {
        try {
            orderService.createNewOrder(userId,orderCreateRequest);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.ok("Error occurred");
        }
    }

    @GetMapping("/sendMail")
    public ResponseEntity<String> sendMail() throws Exception {
        emailService.sendMail("tharuke15jaya@gmail.com", "Test", "some body");
        return ResponseEntity.ok("Success");
    }
}
