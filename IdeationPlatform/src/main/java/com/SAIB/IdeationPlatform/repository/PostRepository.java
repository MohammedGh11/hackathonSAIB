package com.SAIB.IdeationPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SAIB.IdeationPlatform.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByStatus(String status);

	List<Post> findByuId(Long categoryType);

}
