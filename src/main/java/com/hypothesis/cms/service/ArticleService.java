package com.hypothesis.cms.service;

import java.util.Date;
import java.util.List;

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

	private static final String THERE_IS_NO_SUCH_ARTICLE_WITH_ID = "There is no such article with id :";
	private static final String NO_SUCH_USER_FOUND_WITH_ID = "No such user found with id";
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Article createArticle(ArticleDto articleDto) {
		Category category = categoryRepository.findById(articleDto.getCategoryid()).orElseThrow(
						() -> new ResourceNotFoundException(NO_SUCH_USER_FOUND_WITH_ID + articleDto.getUserId()));
		User user = userRepository.findById(articleDto.getUserId()).orElseThrow(
				() -> new ResourceNotFoundException(NO_SUCH_USER_FOUND_WITH_ID + articleDto.getUserId()));
		Article article = new Article();
		article.setUser(user);
		article.setTitle(articleDto.getTitle());
		article.setContent(articleDto.getContent());
		article.setCategory(category);
		article.setPublicationDate(new Date());
		article.setStatus(ArticleStatus.DRAFTED);
		return articleRepository.save(article);
	}

	@Override
	public Article updateArticle(ArticleDto articleDto, Long articleId) {
		Article article = articleRepository.findById(articleId)
				.orElseThrow(() -> new ResourceNotFoundException("No such article with id :" + articleId));
		Category category = 
				categoryRepository.findById(articleDto.getCategoryid()).orElseThrow(() -> new ResourceNotFoundException(
						"No such Category with id :" + articleDto.getCategoryid()));
		User user = userRepository.findById(articleDto.getUserId()).orElseThrow(
				() -> new ResourceNotFoundException(NO_SUCH_USER_FOUND_WITH_ID + articleDto.getUserId()));

		article.setUser(user);
		article.setTitle(articleDto.getTitle());
		article.setContent(articleDto.getContent());
		article.setCategory(category);
		article.setPublicationDate(new Date());
		article.setStatus(ArticleStatus.DRAFTED);

		return articleRepository.save(article);
	}

	@Override
	public Article deleteArticleByID(Long articleId) {

		Article article = articleRepository.findById(articleId)
				.orElseThrow(() -> new ResourceNotFoundException(THERE_IS_NO_SUCH_ARTICLE_WITH_ID + articleId));
		articleRepository.deleteById(articleId);
		return article;
	}

	@Override
	public Article getArticleByID(Long articleId) {
		return articleRepository.findById(articleId)
				.orElseThrow(() -> new ResourceNotFoundException(THERE_IS_NO_SUCH_ARTICLE_WITH_ID + articleId));
	}

	@Override
	public List<Article> getAllArticles() {
		return articleRepository.findAll();
	}

	@Override
	public void publishArticle(Long articleId) {
		Article article = articleRepository.findById(articleId)
				.orElseThrow(() -> new ResourceNotFoundException(THERE_IS_NO_SUCH_ARTICLE_WITH_ID + articleId));

		article.setStatus(ArticleStatus.PUBLISHED);
		articleRepository.save(article);
	}

	@Override
	public void unpublishArticle(Long articleId) {
		Article article = articleRepository.findById(articleId)
				.orElseThrow(() -> new ResourceNotFoundException(THERE_IS_NO_SUCH_ARTICLE_WITH_ID + articleId));

		article.setStatus(ArticleStatus.DRAFTED);
		articleRepository.save(article);

	}

	@Override
	public List<Article> getPublishedArticles() {
		return articleRepository.findByStatus(ArticleStatus.PUBLISHED);
	}

	@Override
	public List<Article> getdratedArticles() {
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
