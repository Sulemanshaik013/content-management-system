package com.hypothesis.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hypothesis.cms.dto.CommentDto;
import com.hypothesis.cms.model.Comment;
import com.hypothesis.cms.service.ICommentService;

@RestController
@RequestMapping(path = "/api/v1/comments")
public class CommentController {

	@Autowired
	private ICommentService commentService;

	public CommentController(ICommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping()
	public Comment createComment(@RequestBody CommentDto commentDto) {
		return commentService.createComment(commentDto);
	}

	@PutMapping("/{commentId}")
	public Comment updateComment(@RequestBody CommentDto commentDto, @PathVariable Long commentId) {
		return commentService.updateComment(commentDto, commentId);
	}

	@DeleteMapping("/{commentId}")
	public Comment deleteComment(@PathVariable Long commentId) {
		return commentService.deleteCommentByID(commentId);
	}

	@GetMapping("/{commentId}")
	public Comment getCommentById(@PathVariable Long commentId) {
		return commentService.getCommentById(commentId);
	}

	@GetMapping()
	public List<Comment> getAllComments() {
		return commentService.getAllComments();
	}

	@GetMapping("/article/{articleId}")
	public List<Comment> getCommentsByArticle(@PathVariable Long articleId) {
		return commentService.getCommentsByArticle(articleId);
	}

}
