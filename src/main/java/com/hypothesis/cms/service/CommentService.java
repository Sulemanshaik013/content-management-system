package com.hypothesis.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hypothesis.cms.dto.CommentDto;
import com.hypothesis.cms.exception.CustomException;
import com.hypothesis.cms.model.Comment;
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

		if (!articleRepository.existsById(commentDto.getArticleId())) {
			throw new CustomException("there is noo such article with id :" + commentDto.getArticleId());
		}
		if (!userRepository.existsById(commentDto.getUserId())) {
			throw new CustomException("there is noo such user with id :" + commentDto.getUserId());
		}
		comment.setContent(commentDto.getContent());
		comment.setArticle(articleRepository.findById(commentDto.getArticleId()).get());
		comment.setUser(userRepository.findById(commentDto.getUserId()).get());

		return commentRepository.save(comment);
	}

	@Override
	public Comment updateComment(CommentDto commentDto, Long commentId) {
		if (commentRepository.existsById(commentId)) {
			throw new CustomException("there is noo such comment with id : " + commentId);
		}
		if (articleRepository.existsById(commentDto.getArticleId())) {
			throw new CustomException("there is noo such article with id : " + commentDto.getArticleId());
		}
		if (userRepository.existsById(commentDto.getUserId())) {
			throw new CustomException("there is noo such user with id : " + commentDto.getUserId());
		}

		Comment comment = commentRepository.findById(commentId).get();
		comment.setContent(commentDto.getContent());

		return commentRepository.save(comment);

	}

	@Override
	public Comment deleteCommentByID(Long commentId) {
		if (commentRepository.existsById(commentId)) {
			throw new CustomException("there is noo such comment with id : " + commentId);
		}

		Comment comment = commentRepository.findById(commentId).get();
		commentRepository.deleteById(commentId);
		return comment;
	}

	@Override
	public Comment getCommentById(Long commentId) {
		if (commentRepository.existsById(commentId)) {
			throw new CustomException("there is noo such comment with id : " + commentId);
		}
		return commentRepository.findById(commentId).get();
	}

	@Override
	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}

	@Override
	public List<Comment> getCommentsByArticle(Long articleId) {
		if (!commentRepository.existsById(articleId)) {
			throw new CustomException("there is noo such article with id : " + articleId);
		}

		return commentRepository.findByArticleId(articleId);
	}

}
