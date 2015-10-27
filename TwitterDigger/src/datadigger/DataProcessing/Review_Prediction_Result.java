/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datadigger.DataProcessing;

/**
 *
 * @author Nipuna
 */

// Data class for prediction result of Reviews
public class Review_Prediction_Result {

    // Classification of the given string variable to predict the class

    private String type;

    // Review, which is classified by the AI
    private String review;

    // Flag to check whether this is a reeview or not
    // Flag will be true if it is a review otherwise false.
    private boolean isReview;

    // Default constructor for class
    public Review_Prediction_Result() {
        this.type = "";
        this.review = "";
    }

    // Constructor for class
    public Review_Prediction_Result(String type, String review, boolean isReview) {
        this.type = type;
        this.review = review;
        this.isReview = isReview;
    }
    
    // Set the value of type.
    public void setType(String type){
        this.type = type;
    }
    
    // Set the value of review
    public void setReview(String review){
        this.review = review;
    }
    
    // Set the flag is review
    public void setIsReview(boolean isReview){
        this.isReview = isReview;
    }
    
    // Get the type of content
    public String getType(){
        return this.type;
    }
    
    // Get the review
    public String getReview(){
        return this.review;
    }
    
    // Get the flag of is review
    public boolean getIsReview(){
        return this.isReview;
    }
}
