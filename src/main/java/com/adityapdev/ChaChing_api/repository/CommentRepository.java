package com.adityapdev.ChaChing_api.repository;

import com.adityapdev.ChaChing_api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
