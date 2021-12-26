package com.SAIB.IdeationPlatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.SAIB.IdeationPlatform.model.Post;
import com.SAIB.IdeationPlatform.repository.PostRepository;
import com.SAIB.IdeationPlatform.util.Results;

@Service
public class PostService {
	
	@Autowired
	PostRepository postRepository;


	public List<Post> getAllPost()
	{
		List<Post> list=postRepository.findAll();
		return list;
	
		
	}

	public String addPost(Post post)
	{
		String result="";
		Post storedPost=postRepository.save(post);
		if(storedPost!=null) {
			result=Results.SUCCESS;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Post not created");
		}
		
		return result;
	}
	
	
	public String updatePost(Post post, long postNumber)
	{
		String result="";
		
		post.setPostId(postNumber);
		Post updatedPost=postRepository.save(post);
		
		if(updatedPost!=null)
		{
			result=Results.SUCCESS;
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record was not updated");
		}
		return result;
		
	}
	
	public String deletePost(long postNumber)
	{
		String result="";
		try {
		postRepository.deleteById(postNumber);
		
		
			result=Results.SUCCESS;
			return result;
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
		public List<Post> getPostsByStatus(String status) {
			
			List<Post> list = postRepository.findByStatus(status);
			if(!list.isEmpty())
				return list;
			else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Posts With Status : " + status + " does not exits");
			}
			
			
		}
		
		public List<Post> getPostsByType(Long type)
		{
			List<Post> list=postRepository.findByuId(type);
			if(!list.isEmpty())
				return list;
			else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Post With type " + type + " does not exits");
			}
			
			
		}
		
		public Post getPostByPostNumber(long postId)
		{
			Optional<Post> optional=postRepository.findById(postId);
			
			if(optional.isPresent()) {
				return optional.get();
			}
			else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Post with Post Number:"+postId+"doesn't exist");
			}
			
		}
}
