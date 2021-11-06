package com.owl.server.mappers;

import com.owl.server.models.BookModel;
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

}
