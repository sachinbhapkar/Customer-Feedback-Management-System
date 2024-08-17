package com.sachin.cfm.repository;

import com.sachin.cfm.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // Custom query methods can be added here if needed
}
