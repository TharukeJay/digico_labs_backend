package com.example.digico_labs.repository.model.user;

import com.example.digico_labs.repository.model.Orders;
import com.example.digico_labs.repository.model.PersistObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@Document
public class AuthenticationUser extends PersistObject {

    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String nic;
    private String password;
    private int telephone;
    private UserRole userRole;
    protected UserStatus status;
    private List<Orders> orderList;

//    public class Orders {
//        private String packageType;
//        private String qty;
//        private String subTotal;
//        private String total;
//    }

    public enum UserRole {
        SUPER_ADMIN,
        ROLE_ADMIN,
        ROLE_USER
    }
}
