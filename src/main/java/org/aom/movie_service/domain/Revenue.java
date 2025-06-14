package org.aom.movie_service.domain;

public class Revenue {
    private Long movieInfoId;
    private double budget;
    private double boxOffice;

    public Revenue() {}
    public Revenue(Long movieInfoId, double budget, double boxOffice) {
        this.movieInfoId = movieInfoId;
        this.budget = budget;
        this.boxOffice = boxOffice;
    }
    public Long getMovieInfoId() {
        return movieInfoId;
    }
    public void setMovieInfoId(Long movieInfoId) {
        this.movieInfoId = movieInfoId;
    }
    public double getBudget() {
        return budget;
    }
    public void setBudget(double budget) {
        this.budget = budget;
    }
    public double getBoxOffice() {
        return boxOffice;
    }
    public void setBoxOffice(double boxOffice) {
        this.boxOffice = boxOffice;
    }
    @Override
    public String toString() {
        return "Revenue{" +
                "movieInfoId=" + movieInfoId +
                ", budget=" + budget +
                ", boxOffice=" + boxOffice +
                '}';
    }
}