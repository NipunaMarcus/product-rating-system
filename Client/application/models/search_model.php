<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of search_model
 *
 * @author Nipuna
 */
class search_model extends CI_Model {

    function __construct() {
        parent::__construct();
        $this->load->database();
        $this->load->model('sentimentAnalysis');
    }

    function searchContent($text) {
        $inputData = array();
        $this->db->select("product.product_name,review.review_id,review.text,review.final_sentiment");
        $this->db->from("review");
        $this->db->join("product", "product.product_id = review.product_id");
        $this->db->where("product.product_name LIKE '%$text%'");
        $res = $this->db->get();

        foreach ($res->result() as $raw) {
            $inputData[] = array('text'=>$raw->text,'final_sentiment'=>$raw->final_sentiment,'product_name'=>$raw->product_name,'review_id'=>$raw->review_id);
        }
        
        $result = array();
        if (count($inputData) > 0) {
            $result = $this->sentimentAnalysis->analyzeSentimentValue($inputData, "4326e701c5b700bfb4c0ee42365238bb");
        }else{
            $result = $this->sentimentAnalysis->analyzeSentimentValue($inputData,"4326e701c5b700bfb4c0ee42365238bb");
        }
        return $result;
    }

}
