package com.SAIB.IdeationPlatform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.SAIB.IdeationPlatform.config.JwtTokenUtil;
import com.SAIB.IdeationPlatform.model.Post;
import com.SAIB.IdeationPlatform.repository.PostRepository;
import com.SAIB.IdeationPlatform.util.Results;

@Service
public class PostService {
	
	@Autowired
	PostRepository postRepository;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	UserService userService;

	public List<Post> getAllPost()
	{
		List<Post> list=postRepository.findAll();
		return list;
	
		
	}

	public String addPost(Post post, String token)
	{
		String result="";
		token = jwtTokenUtil.removeBearer(token);
		long userId = userService.getUserIdByEmail(jwtTokenUtil.getUsernameFromToken(token));
		if(userId!= post.getuId()) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Error code 00001");
		}
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

		public List<Post> getAllPosts(int pageNo, int pageSize, String sortBy) {
			
			Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
			Page<Post> pageResult=postRepository.findAll(paging);
			int total = pageResult.getTotalPages();
			System.out.println(total);
			
			if(pageResult.hasContent()) {
				return pageResult.getContent();
			}
			else
				return new ArrayList<Post>();
		}

		public String upVoteByPostId(long postId) {
			
				Optional<Post> optional =postRepository.findById(postId);
				
				String result;
				if(optional.isPresent()) {
					Post post =optional.get();
					post.setVoteUp(post.getVoteUp()+1);
					postRepository.save(post);
					result=Results.SUCCESS;
					return result;
				}
				else {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Post with Post Number:"+postId+"doesn't exist");
				}
				
		}

		public String downVoteByPostId(long postId) {


			Optional<Post> optional =postRepository.findById(postId);
			
			String result;
			if(optional.isPresent()) {
				Post post =optional.get();
				post.setVoteDown(post.getVoteDown()+1);
				postRepository.save(post);
				result=Results.SUCCESS;
				return result;
			}
			else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Post with Post Number:"+postId+"doesn't exist");
			}
		}

		public String addApplicationScoreByPostId(long postId , int score) {

			Optional<Post> optional = postRepository.findById(postId);
			
			String result;
			if(optional.isPresent()) {
				
				
				Post post =optional.get();
				post.setAppReviewedScore(score);
				post.setStatus("public");
				Long cumulativeScore = (post.getAppReviewedScore()+post.getCost_score()+post.getFeasibilityScore())/3;
				post.setScore(cumulativeScore);
				postRepository.save(post);
				result=Results.SUCCESS;
				return result;
			}
			else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Post with Post Number:"+postId+"doesn't exist");
			}
		}
		
		public String addCostScoreByPostId(long postId , int score) {

			Optional<Post> optional =postRepository.findById(postId);
			
			String result;
			if(optional.isPresent()) {
				
				
				Post post =optional.get();
				post.setCost_score(score);
				Long cumulativeScore = (post.getAppReviewedScore()+post.getCost_score()+post.getFeasibilityScore())/3;
				post.setScore(cumulativeScore);
				postRepository.save(post);
				result=Results.SUCCESS;
				return result;
			}
			else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Post with Post Number:"+postId+"doesn't exist");
			}
		}
		
		
		public String addFeasibilityScoreByPostId(long postId , int score) {

			Optional<Post> optional =postRepository.findById(postId);
			
			String result;
			if(optional.isPresent()) {
				
				
				Post post =optional.get();
				post.setFeasibilityScore(score);
				Long cumulativeScore = (post.getAppReviewedScore()+post.getCost_score()+post.getFeasibilityScore())/3;
				post.setScore(cumulativeScore);
				postRepository.save(post);
				result=Results.SUCCESS;
				return result;
			}
			else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Post with Post Number:"+postId+"doesn't exist");
			}
		}
}
