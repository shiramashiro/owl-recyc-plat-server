package com.owl.server.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookModel {

    private int id;
    private String type;
    private String name;
    private double price;
    private String author;
    private String description;
    private String cover_url;
    private int nomination;
    private double discount;
    private List<CoverModel> covers;
    private List<CommentModel> comments;

}
