package com.hypothesis.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Article createArticle(@RequestBody ArticleDto article) {
		return articleService.createArticle(article);
	}

	@PutMapping("/{articleId}")
	public Article updateArticle(@RequestBody ArticleDto article, @PathVariable Long articleId) {
		return articleService.updateArticle(article, articleId);
	}

	@DeleteMapping("/{articleId}")
	public Article deleteArticle(@PathVariable Long articleId) {
		return articleService.deleteArticleByID(articleId);
	}

	@GetMapping("/{articleId}")
	public Article getArticle(@PathVariable Long articleId) {
		return articleService.getArticleByID(articleId);
	}

	@GetMapping()
	public List<Article> getAllArticles() {
		return articleService.getAllArticles();
	}

	@PostMapping("/{articleId}/publish")
	public void publishArticle(@PathVariable Long articleId) {
		articleService.publishArticle(articleId);
	}

	@PostMapping("/{articleId}/unpublish")
	public void unpublishArticle(@PathVariable Long articleId) {
		articleService.unpublishArticle(articleId);
	}

	@GetMapping("/published")
	public List<Article> getPublishedArticles() {
		return articleService.getPublishedArticle();
	}

	@GetMapping("/drafts")
	public List<Article> getdratedArticle() {
		return articleService.getdratedArticle();
	}

	@GetMapping("/category/{categoryId}")
	public List<Article> getArticlesByCategory(@PathVariable Long categoryId) {
		return articleService.getArticlesByCategory(categoryId);
	}

	@GetMapping("/search")
	public List<Article> searchArticleByKeyword(@RequestParam String keyword) {
		return articleService.searchArticlesByKeyword(keyword);
	}

}
