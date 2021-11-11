package com.owl.server.controllers;

import com.owl.server.mappers.IndexMapper;
import com.owl.server.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
        List<CartModel> carts = mapper.findCartsByUserId(userId);
        if (!carts.isEmpty()) {
            book.setCarts(carts);
        }
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

    /**
     * 在书籍详细中插入一条评论。
     *
     * @param model 评论模型
     */
    @PostMapping("/insert/comment")
    public void insertComment(@RequestBody CommentModel model) {
        model.setCreate_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        mapper.insertComment(model);
    }

    /**
     * 书籍评论下点赞
     * @param model 评论模型
     */
    @PostMapping("/update/comment/agree")
    public void updateCommentAgree(@RequestBody CommentModel model) {
        mapper.updateCommentAgree(model);
    }

    /**
     * 书籍评论下反对
     * @param model 评论模型
     */
    @PostMapping("/update/comment/oppose")
    public void updateCommentOppose(@RequestBody CommentModel model) {
        mapper.updateCommentOppose(model);
    }

    @PostMapping("/insert/product/into/carts")
    public void insertProductIntoCarts(@RequestBody CartModel model) {
        model.setCreate_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        mapper.insertProductIntoCarts(model);
    }

    /**
     * 删除购物车中的一个商品。
     *
     * @param model 购物车模型
     */
    @PostMapping("/del/product/from/carts")
    public void delProductFromCarts(@RequestBody CartModel model) {
        mapper.delProductFromCarts(model);
    }

    /**
     * 支付订单。
     *
     * @param model 订单模型
     */
    @PostMapping("/insert/products/into/orders")
    public void insertProductsIntoOrders(@RequestBody OrderModel model) {
        model.setCreate_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        List<BookModel> products = model.getProducts();
        mapper.insertOrder(model);
        for (BookModel product : products) {
            mapper.insertOrdersProducts(model.getId(), product.getId());
        }
    }

    /**
     * 通过用户ID查询订单
     * @param userId 用户ID
     * @return 返回一个 List 集合，元素为订单模型。
     */
    @GetMapping("/find/orders/by/userId")
    public List<OrderModel> findOrdersByUserId(@RequestParam(name = "userId") String userId) {
        return mapper.findOrdersByUserId(userId);
    }

    /**
     * 删除购物车中所有的商品，在用户支付完成之后。
     *
     * @param model 购物车模型。
     */
    @PostMapping("/del/all/products/from/carts")
    public void delAllProductsFromCarts(@RequestBody CartModel model) {
        mapper.delAllProductsFromCarts(model);
    }

    @GetMapping("/find/carts/by/userId")
    public List<CartModel> findCartsByUserId(@RequestParam(name = "userId") String userId) {
        return mapper.findCartsByUserId(userId);
    }

    @PostMapping("/update/order/status")
    public void updateOrderStatus(@RequestBody OrderModel model) {
        mapper.updateOrderStatus(model);
    }

    @PostMapping("/del/order/by/id")
    public void delOrderById(@RequestBody OrderModel model) {
        mapper.delOrderById(model);
    }

    @PostMapping("/find/orders/by/status/and/userId")
    public List<OrderModel> findOrdersByStatusAndUserId(@RequestBody OrderModel model) {
        return mapper.findOrdersByStatusAndUserId(model);
    }

    @PostMapping("/signin")
    public UserModel signin(@RequestBody UserModel model) {
        UserModel user = mapper.findUserByPhone(model);
        if (user == null) {
            user.setCorrect(false);
        } else {
            if (Objects.equals(user.getPassword(), model.getPassword())) {
                user.setCorrect(true);
            }
        }
        return user;
    }

    @GetMapping("/find/user/avatar/by/phone")
    public String findUserAvatarByPhone(@RequestParam(name = "phone") String phone) {
        return mapper.findUserAvatarByPhone(phone);
    }

    @PostMapping("/signup")
    public UserModel signup(@RequestBody UserModel model) {
        UserModel flag = new UserModel();
        if (mapper.findUserByPhone(model) != null) {
            flag.setRepeat(true);
        } else {
            model.setCreate_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            mapper.insertUser(model);
            flag.setRepeat(false);
        }
        return flag;
    }

    @PostMapping("/update/user")
    public void updateUser(@RequestBody UserModel model) {
        mapper.updateUser(model);
    }
}