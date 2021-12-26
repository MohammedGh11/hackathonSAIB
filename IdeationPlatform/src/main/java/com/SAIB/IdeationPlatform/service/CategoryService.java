package com.SAIB.IdeationPlatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.SAIB.IdeationPlatform.model.Category;
import com.SAIB.IdeationPlatform.repository.CategoryRepository;
import com.SAIB.IdeationPlatform.util.Results;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public List<Category> getAllcategories() {
		
		List<Category> list=categoryRepository.findAll();
		return list;
	}

	public String addCategory(Category category) {
		String result="";
		Category storedCategory=categoryRepository.save(category);
		if(storedCategory!=null) {
			result=Results.SUCCESS;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not created");
		}
		
		return result;
	}
	
	
}
