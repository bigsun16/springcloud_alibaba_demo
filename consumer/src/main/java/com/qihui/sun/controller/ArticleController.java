package com.qihui.sun.controller;

import com.qihui.sun.model.Article;
import com.qihui.sun.service.ArticleService;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@Setter
public class ArticleController {
    private ArticleService articleService;

    @GetMapping("/list")
    public List<Article> selectAllCategoryList(@RequestParam String categoryKey) {
        return articleService.searchByCategory(categoryKey);
    }

    @PostMapping("/add")
    public void addArticle(@RequestBody Article article) {
        articleService.save(article);
    }

}