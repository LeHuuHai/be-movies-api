package dev.hari.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private IReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    public ReviewService(IReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(String reviewBody, String imdbId){
        Review review = new Review(reviewBody);

        reviewRepository.insert(review);

        Update update = new Update();
        update.push("reviewIds").value(review);
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(update)
                .first();

        return review;
    }
}
