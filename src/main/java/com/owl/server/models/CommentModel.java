package com.owl.server.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentModel {

    private int id;
    private int book_id;
    private int user_id;
    private String content;
    private String create_date;
    private UserModel user;

}
