package com.hypothesis.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hypothesis.cms.dto.ArticleDto;
import com.hypothesis.cms.model.Article;
import com.hypothesis.cms.service.IArticleService;

@RestController
@RequestMapping(path = "/api/v1/articles")
public class ArticleController {

	@Autowired
	private IArticleService articleService;

	public ArticleController(IArticleService articleService) {
		this.articleService = articleService;
	}

	@PostMapping()
	public ResponseEntity<Article> createArticle(@RequestBody ArticleDto article) {
		Article createdArticle = articleService.createArticle(article);
		return new ResponseEntity<>(createdArticle, HttpStatus.OK);
	}

	@PutMapping("/{articleId}")
	public ResponseEntity<Article> updateArticle(@RequestBody ArticleDto article, @PathVariable Long articleId) {
		Article updatedArticle = articleService.updateArticle(article, articleId);
		return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
	}

	@DeleteMapping("/{articleId}")
	public ResponseEntity<Article> deleteArticle(@PathVariable Long articleId) {
		Article deleteArticleByID = articleService.deleteArticleByID(articleId);
		return new ResponseEntity<>(deleteArticleByID, HttpStatus.OK);
	}

	@GetMapping("/{articleId}")
	public ResponseEntity<Article> getArticle(@PathVariable Long articleId) {
		Article articleByID = articleService.getArticleByID(articleId);
		return new ResponseEntity<>(articleByID, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<Article>> getAllArticles() {
		List<Article> allArticles = articleService.getAllArticles();
		return new ResponseEntity<>(allArticles, HttpStatus.OK);
	}

	@PostMapping("/{articleId}/publish")
	public ResponseEntity<Void> publishArticle(@PathVariable Long articleId) {
		articleService.publishArticle(articleId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/{articleId}/unpublish")
	public ResponseEntity<Void> unpublishArticle(@PathVariable Long articleId) {
		articleService.unpublishArticle(articleId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/published")
	public ResponseEntity<List<Article>> getPublishedArticles() {
		List<Article> publishedArticles = articleService.getPublishedArticles();
		return new ResponseEntity<>(publishedArticles, HttpStatus.OK);
	}

	@GetMapping("/drafts")
	public ResponseEntity<List<Article>> getdratedArticles() {
		List<Article> getdratedArticles = articleService.getdratedArticles();
		return new ResponseEntity<>(getdratedArticles, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<Article>> getArticlesByCategory(@PathVariable Long categoryId) {
		List<Article> articlesByCategory = articleService.getArticlesByCategory(categoryId);
		return new ResponseEntity<>(articlesByCategory, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Article>> searchArticleByKeyword(@RequestParam String keyword) {
		List<Article> searchArticlesByKeyword = articleService.searchArticlesByKeyword(keyword);
		return new ResponseEntity<>(searchArticlesByKeyword, HttpStatus.OK);
	}

}
