package com.hypothesis.cms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hypothesis.cms.dto.ArticleDto;
import com.hypothesis.cms.dto.ArticleStatus;
import com.hypothesis.cms.exception.CustomException;
import com.hypothesis.cms.model.Article;
import com.hypothesis.cms.model.Category;
import com.hypothesis.cms.repository.ArticleRepository;
import com.hypothesis.cms.repository.CategoryRepository;

@Service
public class ArticleService implements IArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Article createArticle(ArticleDto articleDto) {
		Optional<Category> category = categoryRepository.findById(articleDto.getCategoryid());
		Article article = new Article();
		article.setAuthor(articleDto.getAuthor());
		article.setTitle(articleDto.getTitle());
		article.setContent(articleDto.getContent());
		article.setCategory(category.get());
		article.setPublicationDate(new Date());
		article.setStatus(ArticleStatus.DRAFTED);
		return articleRepository.save(article);
	}

	@Override
	public Article updateArticle(ArticleDto articleDto, Long articleId) {
		if (articleRepository.existsById(articleId)) {
			throw new CustomException("There is no such article with id :" + articleId);
		}
		if (categoryRepository.existsById(articleDto.getCategoryid())) {
			throw new CustomException("There is no such category with id :" + articleDto.getCategoryid());
		}
		Optional<Category> category = categoryRepository.findById(articleDto.getCategoryid());
		Optional<Article> article = articleRepository.findById(articleId);
		article.get().setAuthor(articleDto.getAuthor());
		article.get().setTitle(articleDto.getTitle());
		article.get().setContent(articleDto.getContent());
		article.get().setCategory(category.get());
		article.get().setPublicationDate(new Date());
		article.get().setStatus(ArticleStatus.DRAFTED);

		return articleRepository.save(article.get());
	}

	@Override
	public Article deleteArticleByID(Long articleId) {
		if (articleRepository.existsById(articleId)) {
			throw new CustomException("There is no such article with id :" + articleId);
		}
		Optional<Article> article = articleRepository.findById(articleId);
		articleRepository.deleteById(articleId);
		return article.get();
	}

	@Override
	public Article getArticleByID(Long articleId) {
		if (articleRepository.existsById(articleId)) {
			throw new CustomException("There is no such article with id :" + articleId);
		}
		Optional<Article> article = articleRepository.findById(articleId);
		return article.get();
	}

	@Override
	public List<Article> getAllArticles() {
		return articleRepository.findAll();
	}

	@Override
	public void publishArticle(Long articleId) {
		if (articleRepository.existsById(articleId)) {
			throw new CustomException("There is no such article with id :" + articleId);
		}
		Optional<Article> article = articleRepository.findById(articleId);
		article.get().setStatus(ArticleStatus.PUBLISHED);
		articleRepository.save(article.get());

	}

	@Override
	public void unpublishArticle(Long articleId) {
		if (articleRepository.existsById(articleId)) {
			throw new CustomException("There is no such article with id :" + articleId);
		}
		Optional<Article> article = articleRepository.findById(articleId);
		article.get().setStatus(ArticleStatus.DRAFTED);
		articleRepository.save(article.get());

	}

	@Override
	public List<Article> getPublishedArticle() {
		return articleRepository.findByStatus(ArticleStatus.PUBLISHED);
	}

	@Override
	public List<Article> getdratedArticle() {
		return articleRepository.findByStatus(ArticleStatus.DRAFTED);
	}

	@Override
	public List<Article> getArticlesByCategory(Long categoryId) {
		return articleRepository.findByCategoryId(categoryId);
	}

	@Override
	public List<Article> searchArticlesByKeyword(String keyword) {
		return articleRepository.findByTitleContainingIgnoreCase(keyword);
	}

}
