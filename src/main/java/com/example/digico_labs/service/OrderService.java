package com.example.digico_labs.service;

import com.example.digico_labs.repository.AuthenticationUserRepository;
import com.example.digico_labs.repository.model.OrderCreateRequest;
import com.example.digico_labs.repository.model.Orders;
import com.example.digico_labs.repository.model.user.AuthenticationUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderService {

    private final AuthenticationUserRepository authenticationUserRepository;

    public OrderService(AuthenticationUserRepository authenticationUserRepository) {
        this.authenticationUserRepository = authenticationUserRepository;
    }

    public List<Orders> getOders(String userId) {
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

    public void createNewOrder(String userId, OrderCreateRequest orderCreateRequest) {
        AuthenticationUser authenticationUser = fetchUserById(userId);
        Orders orderData = new Orders();
        for (Orders orders: orderCreateRequest.getOrderList()) {
            orderData.setPackageType(orders.getPackageType());
            orderData.setQty(orders.getQty());
            orderData.setSubTotal(orders.getSubTotal());
            orderData.setTotal(orders.getTotal());
            authenticationUser.getOrderList().add(orderData);
        }
        authenticationUserRepository.save(authenticationUser);
    }
}
