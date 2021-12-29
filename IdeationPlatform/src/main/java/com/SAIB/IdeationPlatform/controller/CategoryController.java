package com.SAIB.IdeationPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.SAIB.IdeationPlatform.config.ApiSuccessPayload;
import com.SAIB.IdeationPlatform.model.Category;
import com.SAIB.IdeationPlatform.service.CategoryService;
import com.SAIB.IdeationPlatform.util.Results;

@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/categories")
	public ResponseEntity<ApiSuccessPayload> getAllcategories() {
		List<Category> list = categoryService.getAllcategories();

		ApiSuccessPayload payload = ApiSuccessPayload.build(list, "Categories Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response = new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);

		return response;

	}

	@PostMapping("/category")
	public ResponseEntity<ApiSuccessPayload> addCategory(@RequestBody Category category,
			@RequestHeader(name = "Authorization") String token) {
		ResponseEntity<ApiSuccessPayload> response = null;
		System.out.println(category);
		String result = categoryService.addCategory(category, token);
		if (result.equalsIgnoreCase(Results.SUCCESS)) {
			ApiSuccessPayload payload = ApiSuccessPayload.build(result, "Category created successfully",
					HttpStatus.CREATED);
			response = new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.CREATED);
		}

		return response;

	}

	@GetMapping("/category/{categoryNumber}")
	public ResponseEntity<ApiSuccessPayload> getCategorybyCategoryNumber(@PathVariable long categoryNumber) {
		Category category = categoryService.getCategoryById(categoryNumber);

		ApiSuccessPayload payload = ApiSuccessPayload.build(category, "Success", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response = new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}

}
