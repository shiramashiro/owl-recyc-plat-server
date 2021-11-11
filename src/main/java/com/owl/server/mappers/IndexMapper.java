package com.owl.server.mappers;

import com.owl.server.models.BookModel;
import com.owl.server.models.CartModel;
import com.owl.server.models.CommentModel;
import com.owl.server.models.OrderModel;
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
}
