package com.owl.server.controllers;

import com.owl.server.mappers.IndexMapper;
import com.owl.server.models.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/find/all")
    public List<BookModel> findAll() {
        return mapper.findAll();
    }

    /**
     * 查询所有的推荐书籍。
     *
     * @return 返回一个 List 集合，每一个元素为 BookModel 对象。
     */
    @GetMapping("/find/nomination")
    public List<BookModel> findNomination() {
        return mapper.findNomination();
    }

    /**
     * 通过书籍ID查询书籍。
     *
     * @param id 书籍ID
     * @return 返回一个 BookModel 对象。
     */
    @GetMapping("/find/by/id")
    public BookModel findById(@RequestParam(name = "id") String id) {
        return mapper.findById(id);
    }

}
