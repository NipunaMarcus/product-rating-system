<link href="/FYP/styles/home_view.css" type="text/css" rel="stylesheet">  
<script src="/FYP/scripts/custom/home_view.js" type="text/javascript"></script>  
<div id="homePageContainer">
    <div id="logoContainer" class="logoContainer">
        <img src="styles/content/FYPLogo.png" alt="logo"/>
    </div>
    <div id="searchbox">
        <div class="clearfix">
            <div class="searchContainer">             
                <input type="text" placeholder="search" id="txtSearch" maxlength="50"/>
                <span class="pointerSearch">
                    <input id="btnSearch" type="button" value="Search" alt="Search"/>
                </span>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var properties = {
        btnSearch: $("#btnSearch"),
        txtSearch: $("#txtSearch"),
        loadPageContainer: $("#loadPageContainer"),
        baseUrl: "<?php echo base_url(); ?>"
    };
    
    //Initialize the initialize the script
    Home.HomeView.Init(properties);
</script>