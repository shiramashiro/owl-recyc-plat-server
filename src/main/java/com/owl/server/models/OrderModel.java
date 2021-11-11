package com.owl.server.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {

    private String id;
    private int user_id;
    private String create_date;
    private String phone;
    private String address;
    private String receiver;
    private int status;
    private List<BookModel> products;

}
