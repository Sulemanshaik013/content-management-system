package com.hypothesis.cms.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hypothesis.cms.dto.CommentDto;
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
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto) {
		Optional<Article> article = articleRepository.findById(commentDto.getArticleId());
		Optional<User> user = userRepository.findById(commentDto.getUserId());
		
		Comment comment = new Comment();
		comment.setContent(commentDto.getContent());
		comment.setArticle(article.get());
		comment.setUser(user.get());
		
		return modelMapper.map(commentRepository.save(comment), CommentDto.class) ;
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, Long commentId) {
		Optional<Article> article = articleRepository.findById(commentDto.getArticleId());
		Optional<User> user = userRepository.findById(commentDto.getUserId());
		Optional<Comment> comment = commentRepository.findById(commentId);
		if(comment.isPresent()){	
			comment.get().setContent(commentDto.getContent());
			comment.get().setArticle(article.get());
			comment.get().setUser(user.get());
			
		}
		return modelMapper.map(commentRepository.save(comment.get()), CommentDto.class) ;
		
	}

	@Override
	public CommentDto deleteCommentByID(Long commentId) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if(comment.isPresent()){
			commentRepository.deleteById(commentId);
		}
		return modelMapper.map(comment.get(), CommentDto.class) ;
	}

	@Override
	public CommentDto getCommentById(Long commentId) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if(comment.isPresent()){
			return modelMapper.map(comment.get(), CommentDto.class) ;
		}
		return null;
	}

	@Override
	public List<CommentDto> getAllComments() {
		return modelMapper.map(commentRepository.findAll(), new TypeToken<List<CommentDto>>() {			
		}.getType());
	}

	@Override
	public List<CommentDto> getCommentsByArticle(Long articleId) {
		return modelMapper.map(commentRepository.findAllCommentSbyArticleId(articleId), new TypeToken<List<CommentDto>>() {			
		}.getType());
	}



	
}
