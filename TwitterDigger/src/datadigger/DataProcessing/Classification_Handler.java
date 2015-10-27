/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datadigger.DataProcessing;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerEvaluator;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

/**
 *
 * @author Nipuna
 */
public class Classification_Handler {

    public Classification_Handler() {

    }

    // Train the neural network in order to classify reviews
    public void train() {
        String nlpModelPath = "C:/data/model";
        String trainingDataFilePath = "C:/data/data.txt";
        DoccatModel model = null;
        InputStream dataInputStream = null;
        OutputStream nlpModel = null;
        
        try {
            // Read training data file
            dataInputStream = new FileInputStream(trainingDataFilePath);
            // Read each training instance
            ObjectStream<String> lineStream = new PlainTextByLineStream(
                    dataInputStream, "UTF-8");
            ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(
                    lineStream);
            // Calculate the training model
            model = DocumentCategorizerME.train("en", sampleStream);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        //using trained classifier in production
        try {
            nlpModel = new BufferedOutputStream(new FileOutputStream(
                    nlpModelPath));
            model.serialize(nlpModel);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (nlpModel != null) {
                try {
                    nlpModel.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    // Classify the review
    public Review_Prediction_Result test(String cat, String text) throws InvalidFormatException,
            IOException {
        // Load model
        String classificationModelFilePath = "C:/data/model";        
        InputStream is = new FileInputStream(classificationModelFilePath);
        
        // Create classification model.
        DoccatModel classificationModel = new DoccatModel(is);        
        DocumentCategorizerME classificationME = new DocumentCategorizerME(classificationModel);
            
        // Get the classification class.
        String documentContent = text;    
        double[] classDistribution = classificationME.categorize(documentContent);
        String predictedCategory = classificationME.getBestCategory(classDistribution);
        
        // Ready the prediction result.
        Review_Prediction_Result result = new Review_Prediction_Result();
        if(predictedCategory.equals("Review")){
            result.setIsReview(true);
            result.setReview(text);
            result.setType(predictedCategory);
        }else{
            result.setIsReview(false);
            result.setReview(text);
            result.setType(predictedCategory);
        }
        
        // Return the prediction result.
        return result;
    }
}
