package com.sachin.cfm.controller;

import com.sachin.cfm.entity.Feedback;
import com.sachin.cfm.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/feedback")
@RestController
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Feedback> submitFeedback(@RequestBody Feedback feedback) {
        Feedback createdFeedback = feedbackService.submitFeedback(feedback);
        return ResponseEntity.ok(createdFeedback);
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        Optional<Feedback> feedback = feedbackService.getFeedbackById(id);
        return feedback.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Feedback> updateFeedbackStatus(@PathVariable Long id, @RequestParam String status) {
        Feedback updatedFeedback = feedbackService.updateFeedbackStatus(id, status);
        return ResponseEntity.ok(updatedFeedback);
    }
}
