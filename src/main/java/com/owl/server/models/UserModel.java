package com.owl.server.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private int id;
    private String username;
    private String password;
    private String avatar_url;
    private String phone;
    private String signature;
    private String background_url;
    private String create_date;
    private boolean correct;
    private boolean repeat;

}
