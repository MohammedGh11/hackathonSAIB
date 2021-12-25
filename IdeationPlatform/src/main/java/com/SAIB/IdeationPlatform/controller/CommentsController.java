package com.SAIB.IdeationPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.SAIB.IdeationPlatform.service.CommentsService;

@RestController
public class CommentsController {
	
	@Autowired
	CommentsService commentsService;
	
}
