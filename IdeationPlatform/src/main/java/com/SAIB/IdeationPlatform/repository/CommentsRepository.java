package com.SAIB.IdeationPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SAIB.IdeationPlatform.model.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

}
