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
public class Feature_Value {
    private int featureValue;
    private String sentiment;
    
    public Feature_Value(){
        this.featureValue = -1;
        this.sentiment = "";
    }
    
    public void setFeatureValue(int featureValue){
        this.featureValue = featureValue;
    }
    
    public void setSentiment(String sentiment){
        this.sentiment = sentiment;
    }
    
    public int getFeatureValue(){
        return this.featureValue;
    }
    
    public String getSentiment(){
        return this.sentiment;
    }
}
