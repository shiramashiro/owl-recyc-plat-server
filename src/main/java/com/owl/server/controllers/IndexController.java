package com.owl.server.controllers;

import com.owl.server.mappers.IndexMapper;
import com.owl.server.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/index")
public class IndexController {

    private IndexMapper mapper;

    @Autowired
    public void setMapper(IndexMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 查询所有的书籍。
     *
     * @return 返回一个 List 集合，每一个元素为 BookModel 对象。
     */
    @GetMapping("/find/all/books")
    public List<BookModel> findAllBooks() {
        return mapper.findAllBooks();
    }

    /**
     * 查询所有的推荐书籍。
     *
     * @return 返回一个 List 集合，每一个元素为 BookModel 对象。
     */
    @GetMapping("/find/books/by/nomination")
    public List<BookModel> findBooksByNomination() {
        return mapper.findBooksByNomination();
    }

    /**
     * 通过ID查询书籍。
     *
     * @param id 书籍ID
     * @return 返回一个 BookModel 对象。
     */
    @GetMapping("/find/book/by/id")
    public BookModel findBookById(@RequestParam(name = "id") String id, @RequestParam(name = "userId") String userId) {
        BookModel book = mapper.findBookById(id);
        book.setCarts(mapper.findCartsByUserId(userId));
        return book;
    }

    /**
     * 通过type查询书籍。
     *
     * @param type 书籍类型
     * @return 返回一个 List 集合，每一个元素为 BookModel 对象。
     */
    @GetMapping("/find/books/by/type")
    public List<BookModel> findBooksByType(@RequestParam(name = "type") String type) {
        return mapper.findBooksByType(type);
    }

    @PostMapping("/insert/comment")
    public void insertComment(@RequestBody CommentModel model) {
        model.setCreate_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        mapper.insertComment(model);
    }

    @PostMapping("/update/comment/agree")
    public void updateCommentAgree(@RequestBody CommentModel model) {
        mapper.updateCommentAgree(model);
    }

    @PostMapping("/update/comment/oppose")
    public void updateCommentOppose(@RequestBody CommentModel model) {
        mapper.updateCommentOppose(model);
    }

    @PostMapping("/insert/order")
    public void insertOrder(@RequestBody OrderModel model) {
        mapper.insertOrder(model);
    }

    @GetMapping("/find/orders/by/id")
    public List<OrderModel> findOrdersById(@RequestParam(name = "id") String id) {
        return mapper.findOrdersById(id);
    }

    @PostMapping("/insert/product/into/carts")
    public void insertProductIntoCarts(@RequestBody CartModel model) {
        model.setCreate_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println(model);
        mapper.insertProductIntoCarts(model);
    }

}