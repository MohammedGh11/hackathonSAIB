package com.SAIB.IdeationPlatform.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.SAIB.IdeationPlatform.model.Comments;
import com.SAIB.IdeationPlatform.repository.CommentsRepository;
import com.SAIB.IdeationPlatform.util.Results;

@Service
public class CommentsService {

	@Autowired
	CommentsRepository commentsRepository;

		public String addComments(Comments comments)
	{
		String result="";
		Comments storedComments=commentsRepository.save(comments);
		if(storedComments!=null) {
			result=Results.SUCCESS;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Comments not created");
		}
		
		return result;
	}
		
		
		public List<Comments> getCommentsByPostId(long postId)
		{
			List<Comments> list=commentsRepository.findBypID(postId);
			
			if(!list.isEmpty()) {
				return list;
			}
			else {
				return new ArrayList<Comments>();
			}
			
		}
		public List<Comments> getCommentsByUserId(long userId)
		{
			List<Comments> list=commentsRepository.findByuID(userId);
			
			if(!list.isEmpty()) {
				return list;
			}
			else {
					return new ArrayList<Comments>();
			}
			
		}


		public String deletePost(long commentId) {
			String result="";
			try {
			commentsRepository.deleteById(commentId);
			
			
				result=Results.SUCCESS;
				return result;
			}
			catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			}
			
		}


		public List<Comments> getAllComments() {
			List<Comments> list=commentsRepository.findAll();
			return list;
		}
	
		
		
		
}
