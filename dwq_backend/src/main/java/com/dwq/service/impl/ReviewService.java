package com.dwq.service.impl;

import com.dwq.entity.dto.Review;
import com.dwq.mapper.ReviewMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    public void addReview(Review review) {
        reviewMapper.insertReview(review);
    }


    public void deleteReview(Integer reviewId) {
        reviewMapper.deleteReview(reviewId);
    }


    public void updateReview(Review review) {
        reviewMapper.updateReview(review);
    }

    public Review getReview(Integer reviewId) {
        return reviewMapper.selectReviewById(reviewId);
    }

    public List<Review> getAllReviews() {
        return reviewMapper.selectAllReviews();
    }
}
