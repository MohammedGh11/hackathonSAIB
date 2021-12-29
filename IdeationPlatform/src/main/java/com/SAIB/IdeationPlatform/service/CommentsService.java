package com.SAIB.IdeationPlatform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.SAIB.IdeationPlatform.config.JwtTokenUtil;
import com.SAIB.IdeationPlatform.model.Comments;
import com.SAIB.IdeationPlatform.repository.CommentsRepository;
import com.SAIB.IdeationPlatform.util.Results;

@Service
public class CommentsService {

	@Autowired
	CommentsRepository commentsRepository;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	public String addComments(Comments comments, String token) {
		token = jwtTokenUtil.removeBearer(token);
		long userId = jwtTokenUtil.getIdFromToken(token);
		comments.setuID(userId);
		String result = "";
		Comments storedComments = commentsRepository.save(comments);
		if (storedComments != null) {
			result = Results.SUCCESS;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comments not created");
		}

		return result;
	}

	public List<Comments> getCommentsByPostId(long postId) {
		List<Comments> list = commentsRepository.findBypID(postId);

		if (!list.isEmpty()) {
			return list;
		} else {
			return new ArrayList<Comments>();
		}

	}

	public List<Comments> getCommentsByUserId(long userId) {
		List<Comments> list = commentsRepository.findByuID(userId);

		if (!list.isEmpty()) {
			return list;
		} else {
			return new ArrayList<Comments>();
		}

	}

	public String deleteComment(long commentId, String token) {
		long userId = jwtTokenUtil.getIdFromToken(token);
		String userType = jwtTokenUtil.getUserTypeFromToken(token);
		Optional<Comments> comment = commentsRepository.findById(commentId);

		if (!comment.isPresent()) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "");

		}

		Boolean hasAuthority = userType.equalsIgnoreCase("Admin") || userType.equalsIgnoreCase("Application Reviewer")
				|| userType.equalsIgnoreCase("Cost Reviewer") || userType.equalsIgnoreCase("Feasibility Reviewer")
				|| userId == comment.get().getuID();

		if (!hasAuthority) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "");
		}
		String result = "";
		try {
			commentsRepository.deleteById(commentId);

			result = Results.SUCCESS;
			return result;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	public List<Comments> getAllComments() {
		List<Comments> list = commentsRepository.findAll();
		return list;
	}

}
