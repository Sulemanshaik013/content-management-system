package com.hypothesis.cms.service;

import java.util.List;

import com.hypothesis.cms.dto.CommentDto;

public interface ICommentService {

	CommentDto createComment(CommentDto commentDto);

	CommentDto updateComment(CommentDto commentDto, Long commentId);

	CommentDto deleteCommentByID(Long commentId);

	CommentDto getCommentById(Long commentId);

	List<CommentDto> getAllComments();

	List<CommentDto> getCommentsByArticle(Long articleId);

}
