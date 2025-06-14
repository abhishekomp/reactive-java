package org.aom.movie_service.domain;

import java.util.List;

public class Movie {
    private MovieInfo movieInfo;
    private List<Review> reviewList;
    private Revenue revenue;

    // no-args constructor
    public Movie() {}
    // constructor with movie and reviewList
    public Movie(MovieInfo movieInfo, List<Review> reviewList) {
        this.movieInfo = movieInfo;
        this.reviewList = reviewList;
    }
    // constructor with movie, reviewList, and revenue
    public Movie(MovieInfo movieInfo, List<Review> reviewList, Revenue revenue) {
        this.movieInfo = movieInfo;
        this.reviewList = reviewList;
        this.revenue = revenue;
    }

    public MovieInfo getMovieInfo() {
        return movieInfo;
    }
    public void setMovieInfo(MovieInfo movieInfo) {
        this.movieInfo = movieInfo;
    }
    public List<Review> getReviewList() {
        return reviewList;
    }
    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
    public Revenue getRevenue() {
        return revenue;
    }
    public void setRevenue(Revenue revenue) {
        this.revenue = revenue;
    }
    @Override
    public String toString() {
        return "Movie{" +
                "movieInfo=" + movieInfo +
                ", reviewList=" + reviewList +
                ", revenue=" + revenue +
                '}';
    }

    // toString method using String.format for debugging
    public String toStringFormatted() {
        return String.format("Movie{movieInfo=%s, reviewList=%s, revenue=%s}",
                movieInfo,
                reviewList != null ? reviewList.toString() : "null",
                revenue != null ? revenue.toString() : "null");
    }
}