package org.aom.movie_service;

import org.aom.movie_service.service.MovieInfoService;
import org.aom.movie_service.service.MovieReactiveService;
import org.aom.movie_service.service.ReviewService;
import org.aom.movie_service.utils.JsonUtil;

import java.io.IOException;

public class MovieClientApp {
    public static void main(String[] args) throws IOException {
        System.out.println("Movie Client Application is running...");
        // Here you can add code to interact with the MovieInfoService, ReviewService, etc.
        // For example, you could create instances of these services and call their methods.
        MovieInfoService movieInfoService = new MovieInfoService();
        ReviewService reviewService = new ReviewService();
        MovieReactiveService movieReactiveService = new MovieReactiveService(movieInfoService, reviewService);

        // Example usage of the MovieReactiveService to fetch movies
        //getMovies(movieReactiveService);

        // Example usage of the MovieReactiveService to fetch a movie by ID
        //getMovieById(movieReactiveService, 100L);

        // Example usage of the MovieReactiveService to fetch a movie by ID and print in JSON format
        getMovieByIdAndPrintInJson(movieReactiveService, 100L);

        // Keep the application running to see the output
        System.out.println("Press a key to end");
        System.in.read();
    }

    private static void getMovies(MovieReactiveService movieReactiveService) {
        movieReactiveService.getMovies()
                .subscribe(movie -> {
                    System.out.println("Received movie: " + movie.getMovieInfo().getName());
                    System.out.println("Reviews: " + movie.getReviewList());
                }, error -> {
                    System.err.println("Error occurred: " + error.getMessage());
                }, () -> {
                    System.out.println("Completed fetching movies.");
                });
    }

    private static void getMovieById(MovieReactiveService movieReactiveService, Long id) {
        movieReactiveService.getMovieById(id)
                .subscribe(movie -> {
                    System.out.println("Received movie: " + movie.getMovieInfo().getName());
                    System.out.println("Reviews: " + movie.getReviewList());
                }, error -> {
                    System.err.println("Error occurred: " + error.getMessage());
                }, () -> {
                    System.out.println("Completed fetching movie by ID.");
                });
    }

    private static void getMovieByIdAndPrintInJson(MovieReactiveService movieReactiveService, Long id) {
        movieReactiveService.getMovieById(id)
                .map(movie -> {
                    try {
                        return JsonUtil
                                .getObjectMapper()
                                .writerWithDefaultPrettyPrinter()   // Use pretty printer for better readability
                                .writeValueAsString(movie);
                    } catch (Exception e) {
                        e.printStackTrace();    // Useful for debugging
                        throw new RuntimeException("Failed to serialize movie", e);
                    }
                })
                .subscribe(json -> {
                    System.out.println("Received movie in JSON format: " + json);
                }, error -> {
                    System.err.println("Error occurred: " + error.getMessage());
                }, () -> {
                    System.out.println("Completed fetching movie by ID in JSON format.");
                });
    }
}
