package com.hypothesis.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hypothesis.cms.dto.ArticleDto;
import com.hypothesis.cms.dto.ArticleStatus;
import com.hypothesis.cms.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	List<ArticleDto> findArticleByStatus(ArticleStatus status);
	List<ArticleDto> findArticleByCategory(Long categoryId);
	List<ArticleDto> findArticleBykeyword(String keyword);
}

