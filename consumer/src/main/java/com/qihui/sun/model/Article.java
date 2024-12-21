package com.qihui.sun.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Data
@Document(indexName = "wish_tree_article")
public class Article {
    private String title;
    private String content;

    @Field(name = "create_time", type = FieldType.Date, format = {},pattern = "uuuu-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Field(name = "update_time", type = FieldType.Date, format ={},pattern = "uuuu-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Field(name = "category_key")
    private String categoryKey;
    @Id
    private String id;
    @Field(name = "delete_flag")
    private Boolean deleteFlag;
}