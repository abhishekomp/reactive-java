package org.aom.movie_service.service;

import org.aom.movie_service.domain.Review;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

public class ReviewService {

    public List<Review> retrieveReviews(long movieInfoId){

        return List.of(
                new Review(1L, movieInfoId, "Awesome Movie", 8.9),
                new Review(2L, movieInfoId, "Excellent Movie", 9.0));
    }

    public Flux<Review> retrieveReviewsFlux(long movieInfoId){

        var reviewsList = List.of(
                new Review(1L,movieInfoId, "Awesome Movie", 8.9),
                new Review(2L, movieInfoId, "Excellent Movie", 9.0));
        return Flux.fromIterable(reviewsList)
                .delayElements(Duration.ofSeconds(1));
    }
}