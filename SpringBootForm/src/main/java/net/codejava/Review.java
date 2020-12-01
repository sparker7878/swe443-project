package net.codejava;

public class Review {
    private String restName; //restaurant name
    private String review; //the user's review
    private boolean rating; //upvote or downvote implemented instead of 5-star system
    //for rating: true = thumbs up, false = thumbs down

    public Review(String restName, String review, boolean rating) {
        this.restName = restName;
        this.review = review;
        this.rating = rating;
    }

    public String getRestName() {
        return this.restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getReview() {
        return this.review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public boolean isRated() {
        return this.rating;
    }

    public void setRating(boolean rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return this.review;
    }
}
