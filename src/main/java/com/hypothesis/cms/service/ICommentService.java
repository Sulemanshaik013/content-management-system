package com.hypothesis.cms.service;

import java.util.List;

import com.hypothesis.cms.dto.CommentDto;
import com.hypothesis.cms.model.Comment;

public interface ICommentService {

	Comment createComment(CommentDto commentDto);

	Comment updateComment(CommentDto commentDto, Long commentId);

	Comment deleteCommentByID(Long commentId);

	Comment getCommentById(Long commentId);

	List<Comment> getAllComments();

	List<Comment> getCommentsByArticle(Long articleId);

}
