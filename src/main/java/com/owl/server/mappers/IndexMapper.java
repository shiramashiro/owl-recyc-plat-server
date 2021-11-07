package com.owl.server.mappers;

import com.owl.server.models.BookModel;
import com.owl.server.models.CommentModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IndexMapper {

    @Select("SELECT * FROM books")
    List<BookModel> findAll();

    @Select("SELECT * FROM books WHERE nomination = 1")
    List<BookModel> findNomination();

    BookModel findById(@Param("id") String id);

    @Select("SELECT * FROM books WHERE type = #{type}")
    List<BookModel> findByType(@Param("type") String type);

    @Insert("INSERT INTO comments(book_id, user_id, content, create_date) VALUES(#{book_id}, #{user_id}, #{content}, #{create_date})")
    void publishComment(CommentModel model);
}
