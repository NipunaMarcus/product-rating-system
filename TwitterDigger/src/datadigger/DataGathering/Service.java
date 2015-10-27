package datadigger.DataGathering;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nipuna
 */
import datadigger.DataProcessing.Classification_Handler;
import datadigger.DataProcessing.Database_Handler;
import datadigger.DataProcessing.Feature_Value;
import datadigger.DataProcessing.Format_Review;
import datadigger.DataProcessing.Review_Prediction_Result;
import datadigger.DataProcessing.Sentiment_Analyzer;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

public class Service {

    private static final String TWITTER_CONSUMER_KEY = "aFIR3pAhv9WqKzK3M58PH3zxs";
    private static final String TWITTER_SECRET_KEY = "xxShZmcINr193fp0XOc2e1yDkDntIMJsjsy5xoCC8ZgVbjwkmh";
    private static final String TWITTER_ACCESS_TOKEN = "2349345594-QRYhuewVL2np51TzXSHohXLJ1wQ3bWkTxD9gsqA";
    private static final String TWITTER_ACCESS_TOKEN_SECRET = "iYUgn6H8632pWRuxBJHGaN1Y5qijcEvYiCVeAGuG1PxRc";

    private static ArrayList<String> texts = new ArrayList<String>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Format_Review fr = new Format_Review();
//        Sentiment_Analyzer sa = new Sentiment_Analyzer();
        try {
           Scheduling_Scheme SSH = new Scheduling_Scheme();
           SSH.startSchedule();
        } catch (Exception e) {
            
        }        
    }
}
