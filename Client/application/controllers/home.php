<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of home
 *
 * @author Nipuna
 */
class Home extends CI_Controller{
    function __construct() {
        parent::__construct();
        $this->load->helper('url');
    }
    
    function index(){
       $this->load->view("main_layout_view");
       //$this->load->view("home_view");
    }
    
    function loadHomePage(){
        $this->load->view("home_view");
    }
    
    function loadLoginPage(){
        $this->load->view("login_view");
    }
}
