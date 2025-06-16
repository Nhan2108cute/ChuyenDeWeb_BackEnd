// controller/ArticleController.java
package com.example.backend.controller;

import com.example.backend.model.Article;
import com.example.backend.model.ArticleDTO;
import com.example.backend.repository.ArticleRepository;
import com.example.backend.repository.AuthorRepository;
import com.example.backend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "http://localhost:3000")
public class ArticleController {

    @Autowired
   private ArticleService articleService;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public List<ArticleDTO> getAllArticles() {
        return articleService.getAllArticleDTOs();
    }

//        return articles.stream().map(article -> {
//            AtomicReference<String> authorName = new AtomicReference<>("Unknown");
//            if (article.getAuthorId() != null) {
//                authorRepository.findById(article.getAuthorId().intValue()).ifPresent(author ->
//                        authorName.set(author.getName()));
//            }
//
//            // Có thể lấy `description` từ `link` hoặc sau này bạn thêm field riêng
//            String description = "Xem thêm tại: " + article.getLink();
//
//            return new ArticleDTO(
//                    article.getArticleId(),
//                    article.getTitle(),
//                    description,
//                    authorName.get(),
//                    article.getCreatedAt()
//            );
//        }).toList();

}
