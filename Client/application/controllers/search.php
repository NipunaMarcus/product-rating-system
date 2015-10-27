<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of search
 *
 * @author Nipuna
 */
class Search extends CI_Controller{
    function __construct() {
        parent::__construct();
        $this->load->helper('url');
        $this->load->model('search_model');
    }
    
    function index(){
        $this->load->view("result_view");
    }
    
    function searchReviews(){    
        $searchContent = $this->input->post('content');
        if(!isset($searchContent)){
            $data['isSuccess'] = false;
            $data['message'] = "Please enter the data to search";
            $this->output->set_content_type("text/json");
            echo json_encode($data);
        }else{
            $data = $this->search_model->searchContent($searchContent);
            $data['isSuccess'] = true;
            $data['message'] = "";
            $this->output->set_content_type("text/json");
            echo json_encode($data);
        }
    }
    
    function loadResultView(){
        $this->load->view("result_view");
    }
}
