/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datadigger.DataGathering;

import datadigger.DataProcessing.Data_Processor;
import datadigger.DataProcessing.Database_Handler;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nipuna
 */
public class Twitter_Task extends TimerTask {

    Twitter_Digger twd = new Twitter_Digger();
    private Timer timer = null;
    private volatile static int productId = 0;
    private Logger logger = Logger.getLogger("MyLogger");

    public Twitter_Task(Timer timer, int productId) {
        this.timer = timer;
        this.productId = productId;
    }

    @Override
    public void run() {
        executeRun();
    }

    public void executeRun(){
        //Get the  product names
        Database_Handler dbh_1 = new Database_Handler();
        ArrayList<String> productNames = dbh_1.loadProductDetails();
        
        //Get the product category
        Database_Handler dbh_2 = new Database_Handler();
        ArrayList<String> categoryNames = dbh_2.loadCategoryDetails();
               
        if(productId == 0){
            Database_Handler dbh_3 = new Database_Handler();            
            productId = dbh_3.loadProductId(productNames.get(0));
            
        }else{
            for(int index = productId; index < productNames.size(); index++){
                if((productId+1) != productNames.size() ){
                    productId +=1;
                }
            }
        }
        
        // Generate the Search query        
        String searchQuery = twd.generateSearchQuery("nexus 5", "", new ArrayList<String>());
        twd.searchTweets(searchQuery);
        
        ArrayList<Review> tweets = twd.getTweetData();
        
        
        if (tweets.size() > 0) {
            // Start the proccessing module.
            Data_Processor dp = new Data_Processor(tweets,productId);
            boolean isSuccess = dp.ExecuteProcessing();
        } else {
            System.out.println("No Tweets Retrieved");
            logger.log(Level.INFO, "No Tweets Retrieved");
        }
    }
}
