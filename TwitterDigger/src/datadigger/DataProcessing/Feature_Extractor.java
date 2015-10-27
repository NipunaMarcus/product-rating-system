/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datadigger.DataProcessing;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nipuna
 */
public class Feature_Extractor {

    private ArrayList<String> featuresForProduct = null;
    private Format_Review fr = null;
    private Logger logger = Logger.getLogger("MyLogger");
    
    public Feature_Extractor() {
        this.fr = new Format_Review();
        this.featuresForProduct = new ArrayList<String>();
    }

    // Extract the features
    public ArrayList<Feature> extractFeatures(String review,int productId) {
        Database_Handler dbh_1 = new Database_Handler();
        int categoryId = dbh_1.getCategoryIdByProductId(productId);
        
        Database_Handler dbh_2 = new Database_Handler();
        this.featuresForProduct = dbh_2.loadFeaturesByProductType(categoryId);
        
        System.out.println("Category Id: "+categoryId + " Product ID: "+ productId);
        
        ArrayList<Feature> features = new ArrayList<Feature>();
        try {
            // Tokenize the review
            String[] sentence = fr.tockenizer(review);
            
            // Tag the part of speech in the sentence
            String[] tags = fr.posTagger(sentence);
            
            // Extract the nouns
            ArrayList<String> nouns = fr.extractNouns(sentence, tags);

            for (String noun : nouns) {
                // Check whether identified feature is a feature.
                boolean isAFeature = this.isFeature(noun);                
                if (isAFeature) {
                    // Get the literal value to the feature
                    String value = fr.extractFeatureValue(noun, sentence, tags);
                    
                    Feature feature = new Feature();
                    feature.setFeatureName(noun);
                    feature.setValue(value);      
                    
                    features.add(feature);
                }                
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return features;
    }

    // Check for product features.
    public boolean isFeature(String feature) {
        boolean isTrue = false;
        for (String pf : featuresForProduct) {
            if (pf.equals(feature)) {
                isTrue = true;
                break;
            }
        }
        return isTrue;
    }
}
