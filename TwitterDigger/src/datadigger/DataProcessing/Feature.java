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
public class Feature {
    private String featureName;
    private String value;
    private int score;
    private String sentiment;
    
    public Feature(){
        this.featureName = "";
        this.score = 0;
        this.sentiment="";
        this.value = "";
    }
    
    public void setFeatureName(String featureName){
        this.featureName = featureName;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public void setValue(String value){
        this.value = value;
    }
    
    public void setSentiment(String sentiment){
        this.sentiment = sentiment;
    }
    
    public String getFeatureName(){
        return this.featureName;
    }
    
    public String getValue(){
        return this.value;
    }
    
    public String getSentiment(){
        return this.sentiment;
    }
    
    public int getScore(){
        return this.score;
    }
}
