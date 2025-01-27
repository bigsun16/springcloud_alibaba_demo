package com.qihui.sun.service;

import com.qihui.sun.model.Article;
import lombok.Setter;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Service
public class ArticleService {
    private ArticleRepository articleRepository;
    private ElasticsearchOperations elasticsearchOperations;

    public void findAll() {
        articleRepository.findAll().forEach(System.out::println);
    }

    public void save(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        article.setDeleteFlag(false);
        articleRepository.save(article);
    }

    public List<Article> searchByCategory(String categoryKey) {

        Criteria criteria = new Criteria("categoryKey").is(categoryKey);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        SearchHits<Article> search = elasticsearchOperations.search(criteriaQuery, Article.class);
        return search.get().map(SearchHit::getContent).toList();
    }
}

