package com.fastrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastrack.model.Feedback;

public interface FeedbackDao extends JpaRepository<Feedback,Integer>{

}
