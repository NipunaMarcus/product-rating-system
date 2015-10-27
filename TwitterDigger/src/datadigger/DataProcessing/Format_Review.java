/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datadigger.DataProcessing;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;

/**
 *
 * @author Nipuna
 */
// Format and clean up the review
public class Format_Review {

    private ArrayList<String> reviews;

    public Format_Review() {

    }

    // Remove special characters
    public String removeSpecialCharacters(String input) {
        String review = "";
        review = input.replaceAll("[^a-zA-Z0-9.]+"," ");        
        return review;
    }

    // Detect the sentences
    public String[] sentenceDetector(String input) throws InvalidFormatException,
            IOException {
        String paragraph = input;

        // always start with a model, a model is learned from training data
        InputStream is = new FileInputStream("en-sent.bin");
        SentenceModel model = new SentenceModel(is);
        SentenceDetectorME sdetector = new SentenceDetectorME(model);

        String extractedSentences[] = sdetector.sentDetect(paragraph);
        is.close();
        
        return extractedSentences;
    }

    // Classify the review
    public Review_Prediction_Result classifyReview(String text) throws InvalidFormatException, IOException {       
            Classification_Handler classificationHandler = new Classification_Handler();
            Review_Prediction_Result result = classificationHandler.test("Other", text);
            if (result.getType().equals("Review")) {
                System.out.println("Type: " + result.getType());
                System.out.println("Review: " + result.getReview());
                System.out.println("is Review: " + result.getIsReview());
            }  
            return result;
    }

    // Tockenize the given text
    public String[] tockenizer(String text) throws InvalidFormatException, IOException {
        InputStream modelIn = new FileInputStream("en-token.bin");
        TokenizerModel model = new TokenizerModel(modelIn);
        Tokenizer tokenizer = new TokenizerME(model);

        String tokens[] = tokenizer.tokenize(text);
        return tokens;
    }

    // Tag the POS in a sentence
    public String[] posTagger(String[] text) throws InvalidFormatException, IOException {
        InputStream modelIn = new FileInputStream("en-pos-maxent.bin");
        POSModel posmodel = new POSModel(modelIn);
        modelIn.close();
        POSTaggerME posTagger = new POSTaggerME(posmodel);

        String tags[] = posTagger.tag(text);
        return tags;
    }

    // Extract noun from a sentence
    public ArrayList<String> extractNouns(String[] sentence, String[] tags) {
        ArrayList<String> nouns = new ArrayList<String>();
        
        // Get the POS tags and find the nouns
        for (int index = 0; index < tags.length; index++) {
            String noun = "";
            if (tags[index].equals(Constant.NOUN)) {
                if (tags[index + 1].equals(Constant.NOUN)) {
                    noun = sentence[index] + " " + sentence[index + 1];
                    ++index;
                } else {
                    noun = sentence[index];
                }
                nouns.add(noun);
            }
        }
        return nouns;
    }

    // Extract values for features.
    public String extractFeatureValue(String feature, String[] sentence, String[] tags) throws InvalidFormatException, IOException {
        String[] words = this.tockenizer(feature);
        String value = "";
        int indexForWord = 0;
        String lastWord = words[(words.length) - 1];
        
        // get the position of the feature in the review.
        for (int index = 0; index < sentence.length; index++) {
            if (sentence[index].equals(lastWord)) {
                indexForWord = index;
                break;
            }
        }

        // Find the related adverbs to the feature.
        if (indexForWord != 0) {
            for (int index = indexForWord; index < tags.length; index++) {
                if (tags[index].equals(Constant.ADJECTIVE) || tags[index].equals(Constant.COMPARETIVEADVERB)) {
                    if (tags[index - 1].equals(Constant.ADVERB)) {
                        value = sentence[index - 1] + " " + sentence[index];
                    } else {
                        value = sentence[index];
                    }
                    break;
                }
            }
        } else {
            value = null;
        }
        return value;
    }
}
