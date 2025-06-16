package com.example.backend.repository;

import com.example.backend.model.ArticleContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleContentRepository extends JpaRepository<ArticleContent, Integer> {
    List<ArticleContent> findByArticleId(Integer articleId);
}
