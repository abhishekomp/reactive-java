package org.aom.movie_service.service;

import org.aom.movie_service.domain.Movie;
import org.aom.movie_service.domain.MovieInfo;
import org.aom.movie_service.domain.Review;
import org.aom.movie_service.exception.MovieException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger log = LoggerFactory.getLogger(MovieReactiveService.class);

    private final MovieInfoService movieInfoService;
    private final ReviewService reviewService;

    public MovieReactiveService(MovieInfoService movieInfoService, ReviewService reviewService) {
        this.movieInfoService = movieInfoService;
        this.reviewService = reviewService;
    }

    // Method to fetch all movies reactively
    public Flux<Movie> getMovies() {
        log.info("Fetching all movies reactively...");
        // Use MovieInfoService to retrieve all movie information reactively
        // Fetch movie information reactively
        Flux<MovieInfo> movieInfoFlux = movieInfoService.retrieveMoviesFlux();

        // For each MovieInfo, fetch the reviews and create a Movie object
        return movieInfoFlux.flatMap(movieInfo -> {
                    // Fetch reviews for the current MovieInfo
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId()).collectList();
                    // Map the MovieInfo and reviews to a Movie object
                    Mono<Movie> movieMono = reviewsMono.map(reviews -> new Movie(movieInfo, reviews));
                    return movieMono;
                })
                .onErrorMap(ex -> {
                    // Handle any errors that occur during the fetching process
                    log.error("Error occurred while fetching movies: ", ex); // Log the error along with the stack trace
                    return new MovieException(ex.getMessage());
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
                })
                .onErrorMap(ex -> {
                    // Handle any errors that occur during the fetching process
                    log.error("Error occurred while fetching movie by ID: {}", id, ex); // Log the error with stack trace
                    return new MovieException("Movie not found with ID: " + id);
                });
    }
}

/*
Note about the usage of Slf4j Logger and exception handling:
If you do this:
log.error("Error occurred while fetching movies: {}", ex); // ex is an Exception
SLF4J will print the "ex" using its toString(), so the message will include the exception's type and message, but NOT the stack trace.
And your IDE will likely warn you that you should not use {} for Throwable parameters in SLF4J logging methods.
The message will look like this:
"Fewer arguments provided (0) than placeholders specified (1)"

Proper way to log the exception AND the stack trace:
log.error("Error occurred while fetching movies", ex);
No {} needed!
The second parameter, if it's a Throwable, is automatically logged with stack trace in SLF4J.
 */