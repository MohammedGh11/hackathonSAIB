package com.SAIB.IdeationPlatform.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.SAIB.IdeationPlatform.config.ApiSuccessPayload;
import com.SAIB.IdeationPlatform.model.Post;
import com.SAIB.IdeationPlatform.service.PostService;
import com.SAIB.IdeationPlatform.util.Results;

@RestController
public class PostController {

	@Autowired
	PostService postService;
	
	
	@GetMapping("/posts")
	public ResponseEntity<ApiSuccessPayload> getAllPosts()
	{
		List<Post> list=postService.getAllPost();
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Posts Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		
		return response;
		
	}
	@GetMapping("/posts/all/sorted")
	public ResponseEntity<ApiSuccessPayload> getAllPosts(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String sortBy ){
		
		
		List<Post> list=postService.getAllPosts(pageNo, pageSize ,sortBy);
		HttpStatus status=HttpStatus.OK;
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Posts Found",status);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
		return response;
		
		
	}
	@GetMapping("/post/{postId}")
	public ResponseEntity<ApiSuccessPayload> getPostbyPostNumber(@PathVariable long postId)
	{
		Post post=postService.getPostByPostNumber(postId);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(post, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/post/upvote/{postId}")
	public ResponseEntity<ApiSuccessPayload> upVoteByPostId(@PathVariable long postId)
	{
	
		
		ResponseEntity<ApiSuccessPayload> response=null;
		
		String result=postService.upVoteByPostId(postId);
		if(result.equalsIgnoreCase(Results.SUCCESS))
		{
			ApiSuccessPayload payload=ApiSuccessPayload.build(result, "Post created successfully", HttpStatus.CREATED);
			response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.CREATED);
		}
		
		return response;
	}
	
	
	@GetMapping("/post/downvote/{postId}")
	public ResponseEntity<ApiSuccessPayload> downVoteByPostId(@PathVariable long postId)
	{
	
		
		ResponseEntity<ApiSuccessPayload> response=null;
		
		String result=postService.downVoteByPostId(postId);
		if(result.equalsIgnoreCase(Results.SUCCESS))
		{
			ApiSuccessPayload payload=ApiSuccessPayload.build(result, "Post created successfully", HttpStatus.CREATED);
			response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.CREATED);
		}
		
		return response;
	}
	
	@PostMapping("/post")
	public ResponseEntity<ApiSuccessPayload> addPost(@RequestBody Post post)
	{
		ResponseEntity<ApiSuccessPayload> response=null;
		System.out.println(post);
		String result=postService.addPost(post);
		if(result.equalsIgnoreCase(Results.SUCCESS))
		{
			ApiSuccessPayload payload=ApiSuccessPayload.build(result, "Post created successfully", HttpStatus.CREATED);
			response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.CREATED);
		}
		
		return response;
	
	}
	
	@PutMapping("/post/{postNumber}")
	public ResponseEntity<ApiSuccessPayload> updatePost(@RequestBody Post post, @PathVariable long postNumber)
	{
		String result=postService.updatePost(post, postNumber);
		ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/post/{postNumber}")
	public ResponseEntity<ApiSuccessPayload> deletePost(@PathVariable long postNumber)
	{
		String result=postService.deletePost(postNumber);
		ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}
}
