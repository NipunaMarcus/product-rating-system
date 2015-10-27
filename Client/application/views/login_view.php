<link href="/FYP/styles/login_view.css" type="text/css" rel="stylesheet">    
<script src="/FYP/scripts/custom/login_view.js"></script>
<div id="loadPageContainer" class="loadPageContainer">
    <div id="loginContainer">
        <div class="containerLoginForm">
            <form id="signup">
                <div class="header">
                    <h3>Login</h3>
                    <p>Please enter login details</p>
                </div>
                <div class="sep"></div>
                <div class="inputs">
                    <input id="txtLoginUName" type="username" placeholder="User name" maxlength="20" autofocus />
                    <input id="txtLoginPassword" type="password" placeholder="Password" maxlength="10" />              
                    <a id="btnLogin" href="#">Log in</a>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
     var properties = {
         txtLoginUName: $("#txtLoginUName"),
         txtLoginPassword: $("#txtLoginPassword"),
         btnLogin: $("#btnLogin"),
         baseUrl: "<?php echo base_url(); ?>"
     }
     
     // Initialize the login view script
     Authentication.LoginView.Init(properties);
</script>