package com.dwq.mapper;
import com.dwq.entity.dto.Review;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {
    @Insert("INSERT INTO dwq_review (user_id, pet_id, rating) VALUES (#{userId}, #{petId}, #{rating})")
    @Options(useGeneratedKeys = true, keyProperty = "reviewId")
    void insertReview(Review review);

    @Delete("DELETE FROM dwq_review WHERE review_id = #{reviewId}")
    void deleteReview(Integer reviewId);

    @Update("UPDATE dwq_review SET user_id = #{userId}, pet_id = #{petId}, rating = #{rating}, review_date = #{reviewDate} WHERE review_id = #{reviewId}")
    void updateReview(Review review);

    @Select("SELECT * FROM dwq_review WHERE review_id = #{reviewId}")
    Review selectReviewById(Integer reviewId);

    @Select("SELECT * FROM dwq_review")
    List<Review> selectAllReviews();
}
