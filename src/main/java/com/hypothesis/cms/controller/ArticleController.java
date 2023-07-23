package com.hypothesis.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hypothesis.cms.dto.ArticleDto;
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
	public ArticleDto createArticle(@RequestBody ArticleDto article) {
		return articleService.createArticle(article);
	}

	@PutMapping("/{articleId}")
	public ArticleDto updateArticle(@RequestBody ArticleDto article, Long articleId) {
		return articleService.updateArticle(article, articleId);
	}

	@DeleteMapping("/{articleId}")
	public ArticleDto deleteArticle(Long articleId) {
		return articleService.deleteArticleByID(articleId);
	}

	@GetMapping("/{articleId}")
	public ArticleDto getArticle(Long articleId) {
		return articleService.getArticleByID(articleId);
	}

	@GetMapping()
	public List<ArticleDto> getAllArticles() {
		return articleService.getAllArticles();
	}

	@PostMapping("/{articleId}/publish")
	public void publishArticle(Long articleId) {
		articleService.publishArticle(articleId);
	}

	@PostMapping("/{articleId}/unpublish")
	public void unpublishArticle(Long articleId) {
		articleService.unpublishArticle(articleId);
	}

	@GetMapping("/{articleId}/published")
	public List<ArticleDto> getPublishedArticles() {
		return articleService.getPublishedArticle();
	}

	@GetMapping("/{articleId}/drafts")
	public List<ArticleDto> getdratedArticle() {
		return articleService.getdratedArticle();
	}

	@GetMapping("/category/{categoryId}")
	public List<ArticleDto> getArticlesByCategory(Long categoryId) {
		return articleService.getArticlesByCategory(categoryId);
	}

	@GetMapping("/search")
	public List<ArticleDto> searchArticleByKeyword(@RequestParam String keyword) {
		return articleService.searchArticlesByKeyword(keyword);
	}

}
