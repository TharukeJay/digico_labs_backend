package com.example.digico_labs.service;

import com.example.digico_labs.repository.AuthenticationUserRepository;
import com.example.digico_labs.repository.model.OrderCreateRequest;
import com.example.digico_labs.repository.model.Orders;
import com.example.digico_labs.repository.model.user.AuthenticationUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class OrderService {

    private final AuthenticationUserRepository authenticationUserRepository;
    private EmailService emailService;

    public OrderService(AuthenticationUserRepository authenticationUserRepository, EmailService emailService) {
        this.authenticationUserRepository = authenticationUserRepository;
        this.emailService = emailService;
    }

    public List<Orders> getOrders(String userId) {
        AuthenticationUser authenticationUser = fetchUserById(userId);
        List<Orders> orderData = authenticationUser.getOrderList();
        return orderData;
    }

    private AuthenticationUser fetchUserById(String userId) {
        log.info("Fetching user");
        return authenticationUserRepository.findById(userId).orElseThrow(() -> {
            log.error("There was no user found with the given Id [{}]", userId);
            return null;
        });
    }

    public void createNewOrder(String userId, Orders orderCreateRequest) throws Exception {
        AuthenticationUser authenticationUser = fetchUserById(userId);
        log.info("Setting up the order");
        Orders orderData = new Orders();
        orderData.setPackageData(orderCreateRequest.getPackageData());
        orderData.setTotal(orderCreateRequest.getTotal());
        orderData.setCreatedOn(LocalDate.now());
        authenticationUser.getOrderList().add(orderData);

        authenticationUserRepository.save(authenticationUser);

        String body = "<p><h2>Order details</h2></p><p>Order place date :"  + orderData.getCreatedOn() +"</p><p>Order Total :" + orderData.getTotal() + "</p>";
        emailService.sendMail(authenticationUser.getEmail(), "Order summery", body);
    }
}
