package com.hypothesis.cms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hypothesis.cms.constants.ExceptionConstants;
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
				() -> new ResourceNotFoundException(ExceptionConstants.NO_SUCH_ARTICLE_WITH_ID + commentDto.getArticleId()));
		User user = userRepository.findById(commentDto.getUserId()).orElseThrow(
				() -> new ResourceNotFoundException(ExceptionConstants.NO_SUCH_COMMENT_WITH_ID + commentDto.getUserId()));

		comment.setContent(commentDto.getContent());
		comment.setArticle(article);
		comment.setUser(user);
		comment.setTimestamp(new Date());

		return commentRepository.save(comment);
	}

	@Override
	public Comment updateComment(CommentDto commentDto, Long commentId) {
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException(ExceptionConstants.NO_SUCH_COMMENT_WITH_ID + commentId));
		
		if (!articleRepository.existsById(commentDto.getArticleId())) {
			throw new ResourceNotFoundException(ExceptionConstants.NO_SUCH_ARTICLE_WITH_ID + commentDto.getArticleId());
		}
		if (!userRepository.existsById(commentDto.getUserId())) {
			throw new ResourceNotFoundException(ExceptionConstants.NO_SUCH_COMMENT_WITH_ID + commentDto.getUserId());
		}
		
		comment.setContent(commentDto.getContent());
		comment.setTimestamp(new Date());

		return commentRepository.save(comment);

	}

	@Override
	public Comment deleteCommentByID(Long commentId) {
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new CustomException(ExceptionConstants.NO_SUCH_COMMENT_WITH_ID + commentId));

		commentRepository.deleteById(commentId);
		return comment;
	}

	@Override
	public Comment getCommentById(Long commentId) {
		return commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException(ExceptionConstants.NO_SUCH_COMMENT_WITH_ID + commentId));
	}

	@Override
	public List<Comment> getAllComments() {
		return commentRepository.findAll();
	}

	@Override
	public List<Comment> getCommentsByArticle(Long articleId) {
		if (!commentRepository.existsById(articleId)) {
			throw new ResourceNotFoundException(ExceptionConstants.NO_SUCH_ARTICLE_WITH_ID + articleId);
		}

		return commentRepository.findByArticleId(articleId);
	}

}
