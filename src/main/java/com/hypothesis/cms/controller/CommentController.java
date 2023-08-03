package com.hypothesis.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Comment> createComment(@RequestBody CommentDto commentDto) {
		Comment createdComment = commentService.createComment(commentDto);
		return new ResponseEntity<>(createdComment, HttpStatus.OK);
	}

	@PutMapping("/{commentId}")
	public ResponseEntity<Comment> updateComment(@RequestBody CommentDto commentDto, @PathVariable Long commentId) {
		Comment updatedComment = commentService.updateComment(commentDto, commentId);
		return new ResponseEntity<>(updatedComment, HttpStatus.OK);
	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<Comment> deleteComment(@PathVariable Long commentId) {
		Comment deletedComment = commentService.deleteCommentByID(commentId);
		return new ResponseEntity<>(deletedComment, HttpStatus.OK);
	}

	@GetMapping("/{commentId}")
	public ResponseEntity<Comment> getCommentById(@PathVariable Long commentId) {
		Comment comment = commentService.getCommentById(commentId);
		return new ResponseEntity<>(comment, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<Comment>> getAllComments() {
		List<Comment> allComments = commentService.getAllComments();
		return new ResponseEntity<>(allComments, HttpStatus.OK);
	}

	@GetMapping("/article/{articleId}")
	public ResponseEntity<List<Comment>> getCommentsByArticle(@PathVariable Long articleId) {
		List<Comment> commentsByArticle = commentService.getCommentsByArticle(articleId);
		return new ResponseEntity<>(commentsByArticle, HttpStatus.OK);
	}

}
