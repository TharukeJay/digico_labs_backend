package com.example.digico_labs.repository.model;

import com.example.digico_labs.repository.model.user.AuthenticationUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class OrderCreateRequest {

    List<Orders> orderList;
}
