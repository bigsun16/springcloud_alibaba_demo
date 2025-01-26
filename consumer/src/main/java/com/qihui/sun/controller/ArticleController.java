package com.qihui.sun.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qihui.sun.model.Article;
import com.qihui.sun.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/list")
    public List<Article> selectAllCategoryList(@RequestParam String categoryKey) {
        return articleService.searchByCategory(categoryKey);
    }

    @PostMapping("/add")
    public void addArticle(@RequestBody Article article) {
        articleService.save(article);
    }

}