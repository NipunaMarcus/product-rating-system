/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datadigger.DataProcessing;

import datadigger.DataGathering.Review;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nipuna
 */
public class Data_Processor {

    private ArrayList<Review> tweets;
    private int productId;
    private Logger logger = Logger.getLogger("MyLogger");

    public Data_Processor() {
        this.tweets = new ArrayList<Review>();
    }

    public Data_Processor(ArrayList<Review> tweets, int productId) {
        this.tweets = tweets;
        this.productId = productId;
    }

    // Execute the Data Processing Module
    public boolean ExecuteProcessing() {
        boolean isSuccess = true;
        try {
            System.out.println("step 3");
            Format_Review FR = new Format_Review();
            Feature_Extractor FE = new Feature_Extractor();
            Sentiment_Analyzer SA = new Sentiment_Analyzer();

            for (Review tweet : tweets) {
                //Clear the special characteres
                tweet.setReview(FR.removeSpecialCharacters(tweet.getReview()));
                System.out.println(tweet.getReview());

                //Classify the reviews
                Review_Prediction_Result result = FR.classifyReview(tweet.getReview());
                System.out.println("Review Result: " + result.getType());
                if (result.getIsReview()) {
                    // Exract features.
                    ArrayList<Feature> features = FE.extractFeatures(tweet.getReview(), productId);

                    ArrayList<Integer> featureSentiments = new ArrayList<Integer>();
                    for (Feature feature : features) {
                        int featureSentiment = SA.sentimentForFeature(feature.getValue());
                        featureSentiments.add(featureSentiment);
                        feature.setScore(featureSentiment);
                        feature.setSentiment(SA.parseIntSentimentToString(featureSentiment).getSentiment());
                    }

                    // Get the review sentiment
                    int reviewSentiment = SA.sentimentForReview(tweet.getReview());

                    // Get  the final sentiment
                    String finalSentiment = SA.finalSentiment(reviewSentiment, featureSentiments);
                    System.out.println("Final Sentiment: " + finalSentiment);
                    tweet.setFinalSentiment(finalSentiment);

                    Database_Handler DBH_3 = new Database_Handler();
                    int reviewLastId = 0;
                    reviewLastId = DBH_3.getLastInsertedIdForReview();
                    System.out.println("review Id: " + reviewLastId);

                    // Open a database Connection
                    Database_Handler DBH_5 = new Database_Handler();
                    System.out.println("Created date: " + tweet.getCreatedDateString());
                    int nextId = (reviewLastId + 1);
                    int productIdForPass = productId;
                    
                    tweet.setAPI("Twitter");
                    tweet.setReviewId(nextId);
                    tweet.setLikes(0);
                    tweet.setProductId(productIdForPass);
                    
                    DBH_5.insertReviewToDB(tweet);
                   
                    // Add featrues to the DB
                    for (Feature feature : features) {
                        Database_Handler DBH_4 = new Database_Handler();
                        int featureId = DBH_4.getLastInsertedIdForFeature();

                        Database_Handler DBH_2 = new Database_Handler();
                        DBH_2.insertFeatureReview((featureId + 1), feature.getFeatureName(), feature.getValue(), feature.getScore(), productId, reviewLastId);
                    }
                }
            }

        } catch (Exception ex) {
            isSuccess = false;
            logger.log(Level.SEVERE, ex.getMessage());
        }
        return isSuccess;
    }
}
