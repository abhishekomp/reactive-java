package org.aom.movie_service.service;

import org.aom.movie_service.domain.Movie;
import org.aom.movie_service.domain.MovieInfo;
import org.aom.movie_service.domain.Review;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class MovieReactiveService {

    // This class will contain methods to handle movie-related operations
    // in a reactive manner, such as fetching movies, reviews, and revenues.
    // It will use reactive programming principles to handle asynchronous data streams.

    // Example method signatures:
    // public Flux<Movie> getAllMovies();
    // public Mono<Movie> getMovieById(Long id);
    // public Mono<Void> addMovie(Movie movie);
    // public Mono<Void> updateMovie(Long id, Movie movie);
    // public Mono<Void> deleteMovie(Long id);

    private final MovieInfoService movieInfoService;
    private final ReviewService reviewService;

    public MovieReactiveService(MovieInfoService movieInfoService, ReviewService reviewService) {
        this.movieInfoService = movieInfoService;
        this.reviewService = reviewService;
    }

    // Method to fetch all movies reactively
    public Flux<Movie> getMovies() {
        // Fetch movie information reactively
        Flux<MovieInfo> movieInfoFlux = movieInfoService.retrieveMoviesFlux();

        // For each MovieInfo, fetch the reviews and create a Movie object
        return movieInfoFlux.flatMap(movieInfo -> {
            // Fetch reviews for the current MovieInfo
            Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId()).collectList();
            // Map the MovieInfo and reviews to a Movie object
            Mono<Movie> movieMono = reviewsMono.map(reviews -> new Movie(movieInfo, reviews));
            return movieMono;
        });
    }

    // Method to fetch a movie by its ID reactively
    public Mono<Movie> getMovieById(Long id) {
        // Fetch movie information by ID
        Mono<MovieInfo> movieInfoMono = movieInfoService.retrieveMovieInfoMonoUsingId(id);

        // Fetch reviews for the movie and create a Movie object
        return movieInfoMono.flatMap(movieInfo -> {
            Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId()).collectList();
            return reviewsMono.map(reviews -> new Movie(movieInfo, reviews));
        });
    }
}
