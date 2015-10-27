/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datadigger.DataGathering;

import java.util.Date;
import twitter4j.GeoLocation;

/**
 *
 * @author Nipuna
 */
public class Review {

    private String review;
    private int reviewId;
    private int productId;
    private int likes;
    private String api;
    private Date createdDate;
    private GeoLocation geoLocation;
    private String finalSentiment;

    public Review() {
        this.review = "";
        this.reviewId = 0;
        this.likes = 0;
        this.productId = 0;
        this.api = "";
        this.finalSentiment = "";
        this.createdDate = new Date();
    }

    public void setReview(String review) {
        this.review = review;
    }
    
    public void setLikes(int likes){
        this.likes = likes;
    }

    public void setAPI(String api){
        this.api = api;
    }
    
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setCreatedDate(Date date) {
        this.createdDate = date;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public void setFinalSentiment(String finalSentiment) {
        this.finalSentiment = finalSentiment;
    }

    public int getLikes(){
        return this.likes;
    }
    
    public String getAPI(){
        return this.api;
    }
    
    public int getReviewId() {
        return this.reviewId;
    }

    public int getProductId() {
        return this.productId;
    }

    public String getFinalSentiment() {
        return this.finalSentiment;
    }

    public String getReview() {
        return this.review;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public String getCreatedDateString() {
        return this.createdDate.toString();
    }

    public GeoLocation getGeoLocation() {
        return this.geoLocation;
    }
}
