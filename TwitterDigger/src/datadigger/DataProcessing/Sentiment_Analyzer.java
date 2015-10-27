package datadigger.DataProcessing;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nipuna
 */
// Sentiment Analysis module
public class Sentiment_Analyzer {

    private ArrayList<Feature> featureValues = new ArrayList<Feature>();
    private Logger logger = Logger.getLogger("MyLogger");

    public Sentiment_Analyzer() {

    }

    // Analyze the sentiment for the input.
    public int sentimentAnalyzer(String input) {
        // Reading the ANN
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        int mainSentiment = 0;

        // Validate input
        if (input != null && input.length() > 0) {
            int longest = 0;
            Annotation annotation = pipeline.process(input);
            
            // Get sentiment for the input.
            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                Tree tree = sentence.get(SentimentCoreAnnotations.AnnotatedTree.class);
                
                // Predicted sentiment value.
                int psentiment = RNNCoreAnnotations.getPredictedClass(tree);
                String textPart = sentence.toString();
                if (textPart.length() > longest) {
                    mainSentiment = psentiment;
                    longest = textPart.length();
                }
            }
        }else{
            logger.log(Level.SEVERE, "SentimentAnalyzer: Input value is empty.");
        }

        // Return the main sentiment for the input.
        return mainSentiment;
    }

    // Get the sentiment value for the Review
    public int sentimentForReview(String review) {
        int sentiment = -1;
        if (review != null && review.length() > 0) {
            sentiment = sentimentAnalyzer(review);
        }
        return sentiment;
    }

    // Get the sentiment value for the feature
    public int sentimentForFeature(String literalValue) {
        int finalsentiment = -1;
        if (literalValue != null && literalValue.length() > 0) {
            finalsentiment = sentimentAnalyzer(literalValue);
        }
        return finalsentiment;
    }

    // get final sentiment value for review.
    public String finalSentiment(int reviewSentiment, ArrayList<Integer> featureSentiments) {
        String sentiment = "";
        int negatives = 0;
        int neutrals = 0;
        int positives = 0;
        
        // Parse the sentiment value to string
        Feature_Value finalReviewSentiment = parseIntSentimentToString(reviewSentiment);

        for (int featuresentiment : featureSentiments) {
            if (featuresentiment >= 0 && featuresentiment <= 1) {
                negatives += 1;
            } else if (featuresentiment == 2) {
                neutrals += 1;
            } else if (featuresentiment > 2 && featuresentiment <= 4) {
                positives += 1;
            }
        }
        
        // Get the max value from given values.
        Feature_Value maxValue = getMaxValue(negatives, positives, neutrals);
        if(maxValue.getSentiment().equals(Constant.NEGATIVE) && finalReviewSentiment.getSentiment().equals(Constant.NEGATIVE)){
            sentiment = Constant.NEGATIVE;
        }else if(maxValue.getSentiment().equals(Constant.NEUTRAL) && finalReviewSentiment.getSentiment().equals(Constant.NEUTRAL)){
            sentiment = Constant.NEUTRAL;
        }else if(maxValue.getSentiment().equals(Constant.POSITIVE) && finalReviewSentiment.getSentiment().equals(Constant.POSITIVE)){
            sentiment = Constant.POSITIVE;
        }else{
            sentiment = finalReviewSentiment.getSentiment();
        }

        return sentiment;
    }
    
    // Parse the integer value to string sentiment
    public Feature_Value parseIntSentimentToString(int sentiment){
        Feature_Value parsedSentiment = new Feature_Value();
        if(sentiment >= 0 && sentiment <= 1){
            parsedSentiment.setFeatureValue(sentiment);
            parsedSentiment.setSentiment(Constant.NEGATIVE);
        }else if(sentiment == 2){
            parsedSentiment.setFeatureValue(sentiment);
            parsedSentiment.setSentiment(Constant.NEUTRAL);
        }else if(sentiment > 2 && sentiment <= 4){
            parsedSentiment.setFeatureValue(sentiment);
            parsedSentiment.setSentiment(Constant.POSITIVE);
        }
        return parsedSentiment;
    }

    // Get the maximum value from the given integer values.
    public Feature_Value getMaxValue(int negatives, int positives, int neutrals) {
        Feature_Value negativeValues = new Feature_Value();
        Feature_Value positiveValues = new Feature_Value();
        Feature_Value neutralValues = new Feature_Value();
        Feature_Value finalValue = new Feature_Value();
        ArrayList<Feature_Value> temp = new ArrayList<Feature_Value>();

        // Set each values to custom size.
        negativeValues.setFeatureValue(negatives);
        negativeValues.setSentiment(Constant.NEGATIVE);
        positiveValues.setFeatureValue(positives);
        positiveValues.setSentiment(Constant.POSITIVE);
        neutralValues.setFeatureValue(neutrals);
        neutralValues.setSentiment(Constant.NEUTRAL);

        // Add values to a list.
        temp.add(neutralValues);
        temp.add(negativeValues);
        temp.add(positiveValues);

        // Add the neutral value as the max value.
        int maxValue = neutrals;
        finalValue = neutralValues;
        for (int index = 0; index < (temp.size() - 1); index++) {
            if (maxValue < temp.get(index + 1).getFeatureValue()) {
                maxValue = temp.get(index + 1).getFeatureValue();
                finalValue = temp.get(index + 1);
            }
        }
        return finalValue;
    }
}
