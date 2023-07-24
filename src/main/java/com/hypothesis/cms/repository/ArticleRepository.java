package com.hypothesis.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hypothesis.cms.dto.ArticleStatus;
import com.hypothesis.cms.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	List<Article> findByStatus(ArticleStatus status);

	List<Article> findByCategoryId(Long categoryId);

	List<Article> findByTitleContainingIgnoreCase(String keyword);
}
