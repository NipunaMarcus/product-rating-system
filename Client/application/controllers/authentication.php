<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of authentication
 *
 * @author Nipuna
 */
class Authentication extends CI_Controller {
    function __construct() {
        parent::__construct();
        $this->load->helper('url');
    }
    
    function isLogin(){
        $this->loadHome();
    }
    
    function loadLogin(){
        $this->load->view("login_view");
    }
    
    function loadHome(){
        $this->load->view("home_view");
    }
}
