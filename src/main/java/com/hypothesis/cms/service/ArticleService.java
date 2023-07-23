package com.hypothesis.cms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hypothesis.cms.dto.ArticleDto;
import com.hypothesis.cms.dto.ArticleStatus;
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

	@Autowired
	private ModelMapper mapper;

	@Override
	public ArticleDto createArticle(ArticleDto articleDto) {
		Optional<Category> category = categoryRepository.findById(articleDto.getCategoryid());
		Article article = new Article();
		article.setAuthor(articleDto.getAuthor());
		article.setTitle(articleDto.getTitle());
		article.setContent(articleDto.getContent());
		article.setCategory(category.get());
		article.setPublicationDate(new Date());
		article.setStatus(ArticleStatus.DRAFTED);
		return mapper.map(articleRepository.save(article), ArticleDto.class);
	}

	@Override
	public ArticleDto updateArticle(ArticleDto articleDto, Long articleId) {
		Optional<Category> category = categoryRepository.findById(articleDto.getCategoryid());
		Optional<Article> article = articleRepository.findById(articleId);
		if (article.isPresent()) {
			article.get().setAuthor(articleDto.getAuthor());
			article.get().setTitle(articleDto.getTitle());
			article.get().setContent(articleDto.getContent());
			article.get().setCategory(category.get());
			article.get().setPublicationDate(new Date());
			article.get().setStatus(ArticleStatus.DRAFTED);
		}
		return mapper.map(articleRepository.save(article.get()), ArticleDto.class);
	}

	@Override
	public ArticleDto deleteArticleByID(Long articleId) {
		Optional<Article> article = articleRepository.findById(articleId);
		if (article.isPresent()) {
			articleRepository.deleteById(articleId);
			return mapper.map(article.get(), ArticleDto.class);
		}
		return null;

	}

	@Override
	public ArticleDto getArticleByID(Long articleId) {
		Optional<Article> article = articleRepository.findById(articleId);
		if (article.isPresent()) {
			return mapper.map(article.get(), ArticleDto.class);
		}
		return null;
	}

	@Override
	public List<ArticleDto> getAllArticles() {
		return mapper.map(articleRepository.findAll(), new TypeToken<List<ArticleDto>>() {
		}.getType());
	}

	@Override
	public void publishArticle(Long articleId) {
		Optional<Article> article = articleRepository.findById(articleId);
		if (article.isPresent()) {
			article.get().setStatus(ArticleStatus.PUBLISHED);
		}
	}

	@Override
	public void unpublishArticle(Long articleId) {
		Optional<Article> article = articleRepository.findById(articleId);
		if (article.isPresent()) {
			article.get().setStatus(ArticleStatus.DRAFTED);
		}

	}

	@Override
	public List<ArticleDto> getPublishedArticle() {
		return articleRepository.findArticleByStatus(ArticleStatus.PUBLISHED);
	}

	@Override
	public List<ArticleDto> getdratedArticle() {
		return articleRepository.findArticleByStatus(ArticleStatus.DRAFTED);
	}

	@Override
	public List<ArticleDto> getArticlesByCategory(Long categoryId) {
		return articleRepository.findArticleByCategory(categoryId);
	}

	@Override
	public List<ArticleDto> searchArticlesByKeyword(String keyword) {
		return articleRepository.findArticleBykeyword(keyword);
	}

}
