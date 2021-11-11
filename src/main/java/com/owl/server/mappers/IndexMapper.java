package com.owl.server.mappers;

import com.owl.server.models.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IndexMapper {

    @Select("SELECT * FROM books")
    List<BookModel> findAllBooks();

    @Select("SELECT * FROM books WHERE nomination = 1")
    List<BookModel> findBooksByNomination();

    BookModel findBookById(@Param("id") String id);

    @Select("SELECT * FROM books WHERE type = #{type}")
    List<BookModel> findBooksByType(@Param("type") String type);

    @Insert("INSERT INTO comments(book_id, user_id, content, create_date) VALUES(#{book_id}, #{user_id}, #{content}, #{create_date})")
    void insertComment(CommentModel model);

    @Update("UPDATE comments SET agree = agree + 1 WHERE id = #{id}")
    void updateCommentAgree(CommentModel model);

    @Update("UPDATE comments SET oppose = oppose + 1 WHERE id = #{id}")
    void updateCommentOppose(CommentModel model);

    List<CartModel> findCartsByUserId(@Param("userId") String userId);

    @Insert("INSERT INTO carts(user_id, book_id, create_date) VALUES(#{user_id}, #{book_id}, #{create_date})")
    void insertProductIntoCarts(CartModel model);

    @Delete("DELETE FROM carts WHERE id = #{id}")
    void delProductFromCarts(CartModel model);

    @Insert("INSERT INTO orders(id, user_id, phone, address, receiver, create_date) VALUES(#{id}, #{user_id}, #{phone}, #{address}, #{receiver}, #{create_date})")
    void insertOrder(OrderModel model);

    List<OrderModel> findOrdersByUserId(@Param("userId") String userId);

    @Insert("INSERT INTO orders_products(order_id, book_id) VALUES(#{orderId}, #{bookId})")
    void insertOrdersProducts(@Param("orderId") String orderId, @Param("bookId") int bookId);

    @Delete("DELETE FROM carts WHERE user_id = #{user_id}")
    void delAllProductsFromCarts(CartModel model);

    @Update("UPDATE orders SET status = #{status} WHERE id = #{id}")
    void updateOrderStatus(OrderModel model);

    @Delete("DELETE FROM orders WHERE id = #{id}")
    void delOrderById(OrderModel model);


    List<OrderModel> findOrdersByStatusAndUserId(OrderModel model);

    @Select("SELECT * FROM users WHERE phone = #{phone}")
    UserModel findUserByPhone(UserModel model);

    @Select("SELECT avatar_url FROM users WHERE phone = #{phone}")
    String findUserAvatarByPhone(String phone);

    @Insert("INSERT INTO users(username, phone, password, create_date) VALUES(#{username}, #{phone}, #{password}, #{create_date})")
    void insertUser(UserModel model);

    @Update("UPDATE users SET username = #{username}, password = #{password}, background_url = #{background_url}, avatar_url = #{avatar_url}, phone = #{phone}, signature = #{signature} WHERE id = #{id}")
    void updateUser(UserModel model);
}
