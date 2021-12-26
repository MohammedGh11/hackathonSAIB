package com.SAIB.IdeationPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.SAIB.IdeationPlatform.config.ApiSuccessPayload;
import com.SAIB.IdeationPlatform.model.Comments;
import com.SAIB.IdeationPlatform.service.CommentsService;
import com.SAIB.IdeationPlatform.util.Results;

@RestController
public class CommentsController {
	
	@Autowired
	CommentsService commentsService;
	
	
	
	
	
	
	@GetMapping("/comments")
	public ResponseEntity<ApiSuccessPayload> getAllCommentss()
	{
		List<Comments> list=commentsService.getAllComments();
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Commentss Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		
		return response;
		
	}
	
	@PostMapping("/comments")
	public ResponseEntity<ApiSuccessPayload> addComments(@RequestBody Comments comments)
	{
		ResponseEntity<ApiSuccessPayload> response=null;
		System.out.println(comments);
		String result=commentsService.addComments(comments);
		if(result.equalsIgnoreCase(Results.SUCCESS))
		{
			ApiSuccessPayload payload=ApiSuccessPayload.build(result, "Comments created successfully", HttpStatus.CREATED);
			response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.CREATED);
		}
		
		return response;
	
	}
	
	@GetMapping("/comments/filter/postid/{postId}")
	public ResponseEntity<ApiSuccessPayload> getCommentsByPostId(@PathVariable long postId)
	{
		List<Comments> comments=commentsService.getCommentsByPostId(postId);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(comments, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/comments/filter/userid/{userId}")
	public ResponseEntity<ApiSuccessPayload> getCommentsByUserId(@PathVariable long userId)
	{
		List<Comments> comments=commentsService.getCommentsByUserId(userId);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(comments, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiSuccessPayload> deleteComment(@PathVariable long commentId)
	{
		String result=commentsService.deletePost(commentId);
		ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
}
