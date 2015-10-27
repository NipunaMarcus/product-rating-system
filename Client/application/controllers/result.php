<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of result
 *
 * @author Nipuna
 */
class Result extends CI_Controller{
    function __construct() {
        parent::__construct();
        $this->load->helper('url');
    }
    
    function index(){
        $this->load->view("result_view");
    }
}
