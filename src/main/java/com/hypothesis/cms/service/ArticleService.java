package com.hypothesis.cms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hypothesis.cms.dto.ArticleDto;
import com.hypothesis.cms.dto.ArticleStatus;
import com.hypothesis.cms.exception.ResourceNotFoundException;
import com.hypothesis.cms.model.Article;
import com.hypothesis.cms.model.Category;
import com.hypothesis.cms.model.User;
import com.hypothesis.cms.repository.ArticleRepository;
import com.hypothesis.cms.repository.CategoryRepository;
import com.hypothesis.cms.repository.UserRepository;

@Service
public class ArticleService implements IArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Article createArticle(ArticleDto articleDto) {
		Optional<Category> category = Optional
				.ofNullable(categoryRepository.findById(articleDto.getCategoryid()).orElseThrow(
						() -> new ResourceNotFoundException("No such user found with id" + articleDto.getUserId())));
		Optional<User> user = Optional.ofNullable(userRepository.findById(articleDto.getUserId()).orElseThrow(
				() -> new ResourceNotFoundException("No such user found with id" + articleDto.getUserId())));
		Article article = new Article();
		article.setUser(user.get());
		article.setTitle(articleDto.getTitle());
		article.setContent(articleDto.getContent());
		article.setCategory(category.get());
		article.setPublicationDate(new Date());
		article.setStatus(ArticleStatus.DRAFTED);
		return articleRepository.save(article);
	}

	@Override
	public Article updateArticle(ArticleDto articleDto, Long articleId) {
		Optional<Article> article = Optional.ofNullable(articleRepository.findById(articleId)
				.orElseThrow(() -> new ResourceNotFoundException("No such article with id :" + articleId)));
		Optional<Category> category = Optional.ofNullable(
				categoryRepository.findById(articleDto.getCategoryid()).orElseThrow(() -> new ResourceNotFoundException(
						"No such Category with id :" + articleDto.getCategoryid())));
		Optional<User> user = Optional.ofNullable(userRepository.findById(articleDto.getUserId()).orElseThrow(
				() -> new ResourceNotFoundException("No such user found with id" + articleDto.getUserId())));

		article.get().setUser(user.get());
		article.get().setTitle(articleDto.getTitle());
		article.get().setContent(articleDto.getContent());
		article.get().setCategory(category.get());
		article.get().setPublicationDate(new Date());
		article.get().setStatus(ArticleStatus.DRAFTED);

		return articleRepository.save(article.get());
	}

	@Override
	public Article deleteArticleByID(Long articleId) {

		Optional<Article> article = Optional.ofNullable(articleRepository.findById(articleId)
				.orElseThrow(() -> new ResourceNotFoundException("There is no such article with id :" + articleId)));
		articleRepository.deleteById(articleId);
		return article.get();
	}

	@Override
	public Article getArticleByID(Long articleId) {
		return articleRepository.findById(articleId)
				.orElseThrow(() -> new ResourceNotFoundException("There is no such article with id :" + articleId));
	}

	@Override
	public List<Article> getAllArticles() {
		return articleRepository.findAll();
	}

	@Override
	public void publishArticle(Long articleId) {
		Optional<Article> article = Optional.ofNullable(articleRepository.findById(articleId)
				.orElseThrow(() -> new ResourceNotFoundException("There is no such article with id :" + articleId)));

		article.get().setStatus(ArticleStatus.PUBLISHED);
		articleRepository.save(article.get());
	}

	@Override
	public void unpublishArticle(Long articleId) {
		Optional<Article> article = Optional.ofNullable(articleRepository.findById(articleId)
				.orElseThrow(() -> new ResourceNotFoundException("There is no such article with id :" + articleId)));

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
