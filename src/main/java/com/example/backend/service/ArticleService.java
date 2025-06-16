package com.example.backend.service;

import com.example.backend.model.Article;
import com.example.backend.model.ArticleDTO;
import com.example.backend.repository.ArticleContentRepository;
import com.example.backend.repository.ArticleRepository;
import com.example.backend.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleContentRepository articleContentRepository;

    public List<ArticleDTO> getAllArticleDTOs() {
        List<Article> articles = articleRepository.findAll();

        return articles.stream().map(article -> {
            String authorName = authorRepository.findById((int) article.getAuthorId().longValue())
                    .map(author -> author.getName())
                    .orElse("Unknown");

            List<ArticleDTO.ContentDTO> contents = articleContentRepository.findByArticleId(article.getArticleId())
                    .stream()
                    .map(content -> new ArticleDTO.ContentDTO(
                            content.getContentType().name().toLowerCase(),
                            content.getContent()
                    ))
                    .toList();

            return new ArticleDTO(
                    article.getArticleId(),
                    article.getTitle(),
                    authorName,
                    article.getCreatedAt(),
                    article.getImageId(), // ✅ Thêm imageId
                    contents
            );
        }).toList();
    }

}
