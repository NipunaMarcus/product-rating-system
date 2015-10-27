/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datadigger.DataGathering;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Nipuna
 */
public class Twitter_Digger {

    private final String TWITTER_CONSUMER_KEY = "aFIR3pAhv9WqKzK3M58PH3zxs";
    private final String TWITTER_SECRET_KEY = "xxShZmcINr193fp0XOc2e1yDkDntIMJsjsy5xoCC8ZgVbjwkmh";
    private final String TWITTER_ACCESS_TOKEN = "2349345594-QRYhuewVL2np51TzXSHohXLJ1wQ3bWkTxD9gsqA";
    private final String TWITTER_ACCESS_TOKEN_SECRET = "iYUgn6H8632pWRuxBJHGaN1Y5qijcEvYiCVeAGuG1PxRc";
    private ArrayList<Review> texts = new ArrayList<Review>();
    private String qury = "nexus 5  lang:en";
    private ConfigurationBuilder cb;
    private Twitter twitter;

    public Twitter_Digger() {
        this.cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
                .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public void searchTweets(String qury) {

        try {
            Query query = new Query(qury);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    String content = tweet.getText();
                    Date createdDate = tweet.getCreatedAt();
                    GeoLocation geoLocation = tweet.getGeoLocation();
                    System.out.println(content);

                    Review review = new Review();
                    review.setReview(content);
                    review.setCreatedDate(createdDate);
                    review.setGeoLocation(geoLocation);

                    texts.add(review);
                }

            } while ((query = result.nextQuery()) != null);

        } 
        catch (Exception te) {
            System.out.println("Failed to search tweets: " + te.getMessage());
        }
    }

    public String generateSearchQuery(String product, String untillDate, ArrayList<String> features) {
        String query = "" + product + " ";

        if (features.size() > 0) {
            for (String feature : features) {
                query += feature + " ";
            }
        }

        if (!untillDate.isEmpty()) {
            //Date format is YYYY-MM-DD
            query += "until:" + untillDate;
        }

        query += "lang:en";

        return query;
    }

    public ArrayList<Review> getTweetData() {
        return texts;
    }
}
