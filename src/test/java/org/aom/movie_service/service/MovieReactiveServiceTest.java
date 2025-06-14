package org.aom.movie_service.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieReactiveServiceTest {

    private MovieInfoService movieInfoService = new MovieInfoService();
    private ReviewService reviewService = new ReviewService();
    private MovieReactiveService movieReactiveService = new MovieReactiveService(movieInfoService, reviewService);

    @Test
    void getAllMovies() {
        movieReactiveService.getAllMovies()
                .doOnNext(movie -> {
                    System.out.println("Movie: " + movie);
                    assertNotNull(movie.getMovie());
                    assertNotNull(movie.getReviewList());
                })
                .blockLast(); // Wait for the stream to complete


    }
}