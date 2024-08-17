package com.sachin.cfm.service;

import com.sachin.cfm.entity.Feedback;
import com.sachin.cfm.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Cache the result of submitting feedback with the cache key being the feedback ID
    @CacheEvict(value = "feedbacks", allEntries = true)
    public Feedback submitFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    // Cache the result of retrieving all feedbacks
    @Cacheable(value = "feedbacks", unless = "#result.isEmpty()")
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    // Cache the result of retrieving feedback by ID with the cache key being the feedback ID
    @Cacheable(value = "feedbacks", key = "#id")
    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }

    // Update the cache when feedback status is updated
    @CachePut(value = "feedbacks", key = "#id")
    public Feedback updateFeedbackStatus(Long id, String status) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        feedback.setStatus(status);
        return feedbackRepository.save(feedback);
    }

    // Evict cache when feedback is deleted (if you have a delete method)
    @CacheEvict(value = "feedbacks", key = "#id")
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
