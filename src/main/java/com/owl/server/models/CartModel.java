package com.owl.server.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartModel {

    private int id;
    private int user_id;
    private int book_id;
    private String create_date;
    private int num;
    private BookModel book;

}
