<?php // header('Access-Control-Allow-Origin: *'); ?>
<!DOCTYPE html>
<html>
    <head>
        <title>Rate Me!</title>
        <link href="/FYP/styles/main_layout.css" type="text/css" rel="stylesheet">  
        <script src="/FYP/scripts/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="/FYP/scripts/jquery-ui-1.10.3.js" type="text/javascript"></script>
        <script src="/FYP/scripts/ajaxfileupload.js" type="text/javascript"></script> 
        <script src= "/FYP/scripts/custom/app-namespace.js" type="text/javascript"></script>
        <script src="/FYP/scripts/custom/global.js" type="text/javascript"></script>   
        <script src="/FYP/scripts/custom/mainLayout.js"></script>
    </head>
    <body>
        <div id="mainContainer" class="mainContainer">
            <div id="headerContainer" class="headerContainer">
                <div id="loginHeader">
                    <div class='upperNav'>
                        <ul id="loginH">
                            <li>
                                <div id="loginuserdetails">
                                    <span id="userDetailsuserName"></span>
                                    <span id="userDetailsReputation"></span>
                                </div>
                            </li>
                            <li>
                                <div id="userImageContainer" class="userImageContainer">
                                    <img src="/Stacky/images/defaultUser.jpg" alt="user" class="imgUser" id="imgUser">
                                </div>
                            </li>
                            <li>
                                <div class="loginNav">
                                    <ul id='nav'>
                                        <li><a id="linkLogin"  href="#">Login</a></li>
                                        <li><a id="linkSignUp" href="#">Sign Up</a></li>
                                        <li><a id="linkLogOut" style="display: none" href="#">Logout</a></li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>            
            <div id="loadPageContainer" class="loadPageContainer"></div>
            <div id="footerContainer" class="footerContainer"></div>
        </div>
        <script type="text/javascript">
            var properties = {
                headerContainer: $("#headerContainer"),
                loadPageContainer: $("#loadPageContainer"),
                footerContainer: $("#footerContainer"),
                loginHeader: $("#loginHeader"),
                upperNav: $("#upperNav"),
                loginH: $("#loginH"),
                loginuserdetails: $("#loginuserdetails"),
                userDetailsuserName: $("#userDetailsuserName"),
                userDetailsReputation: $("#userDetailsReputation"),
                userImageContainer: $("#userImageContainer"),
                imgUser: $("#imgUser"),
                loginNav: $("#loginNav"),
                nav: $("#nav"),
                linkLogin: $("#linkLogin"),
                linkSignUp: $("#linkSignUp"),
                linkLogOut: $("#linkLogOut"),
                baseUrl: "<?php echo base_url(); ?>"
            }

            // Initialize the page script
            Home.MainLayout.Init(properties);
        </script>
    </body>
</html>