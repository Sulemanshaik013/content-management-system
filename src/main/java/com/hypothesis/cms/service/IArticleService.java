package com.hypothesis.cms.service;

import java.util.List;

import com.hypothesis.cms.dto.ArticleDto;

public interface IArticleService {

	ArticleDto createArticle(ArticleDto articleDto);

	ArticleDto updateArticle(ArticleDto articleDto, Long articleId);

	ArticleDto deleteArticleByID(Long articleId);

	ArticleDto getArticleByID(Long articleId);

	List<ArticleDto> getAllArticles();

	void publishArticle(Long articleId);

	void unpublishArticle(Long articleId);

	List<ArticleDto> getPublishedArticle();

	List<ArticleDto> getdratedArticle();

	List<ArticleDto> getArticlesByCategory(Long categoryId);

	List<ArticleDto> searchArticlesByKeyword(String keyword);

}
