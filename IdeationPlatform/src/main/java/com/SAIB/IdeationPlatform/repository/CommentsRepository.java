package com.SAIB.IdeationPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SAIB.IdeationPlatform.model.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

	List<Comments> findByuID(long userId);

	List<Comments> findBypID(long postId);

}
