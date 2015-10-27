<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of SentimentAnalysis
 *
 * @author Nipuna
 */
include_once 'datumboxAPI.php';

class SentimentAnalysis extends CI_Model {

    protected $datumbox_api_key;

    function __construct() {
        parent::__construct();
    }

    // Claculate the sentiment
    function analyzeSentimentValue($reviews, $datumbox_api_key) {
        $positiveCount = 0;
        $negativeCount = 0;
        $neutralCount = 0;
        if (count($reviews) > 0) {
            foreach ($reviews as $review) {
                if ($review['final_sentiment'] == "PO") {
                    $positiveCount = $positiveCount + 1;
                } else if ($review['final_sentiment'] == "NE") {
                    $negativeCount = $negativeCount + 1;
                } else {
                    $neutralCount = $neutralCount + 1;
                }
            }

            $positivePercentage = ($positiveCount / count($reviews)) * 100;
            $negativePercentage = ($negativeCount / count($reviews)) * 100;
            $neutralPercentage = ($neutralCount / count($reviews)) * 100;
            $outputData = array();
            $outputData['positivePercentage'] = round($positivePercentage);
            $outputData['negativePercentage'] = round($negativePercentage);
            $outputData['neutralPercentage'] = round($neutralPercentage);
            $outputData['sentimentData'] = $reviews;
            $outputData['reviewCount'] = count($reviews);
            return $outputData;
        }else{
            $positivePercentage = 0;
            $negativePercentage = 0;
            $neutralPercentage = 0;
            $outputData = array();
            $outputData['positivePercentage'] = round($positivePercentage);
            $outputData['negativePercentage'] = round($negativePercentage);
            $outputData['neutralPercentage'] = round($neutralPercentage);
            $outputData['sentimentData'] = $reviews;
            $outputData['reviewCount'] = count($reviews);
            return $outputData;
        }
    }

}
