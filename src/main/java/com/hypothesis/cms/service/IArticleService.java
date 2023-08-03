package com.hypothesis.cms.service;

import java.util.List;

import com.hypothesis.cms.dto.ArticleDto;
import com.hypothesis.cms.model.Article;

public interface IArticleService {

	Article createArticle(ArticleDto articleDto);

	Article updateArticle(ArticleDto articleDto, Long articleId);

	Article deleteArticleByID(Long articleId);

	Article getArticleByID(Long articleId);

	List<Article> getAllArticles();

	void publishArticle(Long articleId);

	void unpublishArticle(Long articleId);

	List<Article> getPublishedArticles();

	List<Article> getdratedArticles();

	List<Article> getArticlesByCategory(Long categoryId);

	List<Article> searchArticlesByKeyword(String keyword);

}
