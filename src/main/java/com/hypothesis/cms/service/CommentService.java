package com.hypothesis.cms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hypothesis.cms.dto.CommentDto;
import com.hypothesis.cms.exception.CustomException;
import com.hypothesis.cms.exception.ResourceNotFoundException;
import com.hypothesis.cms.model.Article;
import com.hypothesis.cms.model.Comment;
import com.hypothesis.cms.model.User;
import com.hypothesis.cms.repository.ArticleRepository;
import com.hypothesis.cms.repository.CommentRepository;
import com.hypothesis.cms.repository.UserRepository;

@Service
public class CommentService implements ICommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Comment createComment(CommentDto commentDto) {
		Comment comment = new Comment();
		Article article = articleRepository.findById(commentDto.getArticleId()).orElseThrow(
				() -> new ResourceNotFoundException("There is no such article with id :" + commentDto.getArticleId()));
		User user = userRepository.findById(commentDto.getUserId()).orElseThrow(
				() -> new ResourceNotFoundException("There is no such comment with id :" + commentDto.getUserId()));

		comment.setContent(commentDto.getContent());
		comment.setArticle(article);
		comment.setUser(user);
		comment.setTimestamp(new Date());

		return commentRepository.save(comment);
	}

	@Override
	public Comment updateComment(CommentDto commentDto, Long commentId) {
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("There is no such comment with id :" + commentId));
		articleRepository.findById(commentDto.getArticleId()).orElseThrow(
				() -> new ResourceNotFoundException("There is no such article with id :" + commentDto.getArticleId()));
		userRepository.findById(commentDto.getUserId()).orElseThrow(
				() -> new ResourceNotFoundException("There is no such comment with id :" + commentDto.getUserId()));

		comment.setContent(commentDto.getContent());
		comment.setTimestamp(new Date());

		return commentRepository.save(comment);

	}

	@Override
	public Comment deleteCommentByID(Long commentId) {
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new CustomException("There is no such comment with id :" + commentId));

		commentRepository.deleteById(commentId);
		return comment;
	}

	@Override
	public Comment getCommentById(Long commentId) {
		return commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("There is no such comment with id :" + commentId));
	}

	@Override
	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}

	@Override
	public List<Comment> getCommentsByArticle(Long articleId) {
		if (!commentRepository.existsById(articleId)) {
			throw new ResourceNotFoundException("there is noo such article with id : " + articleId);
		}

		return commentRepository.findByArticleId(articleId);
	}

}
