<link href="/FYP/styles/result_view.css" type="text/css" rel="stylesheet">  
<script src="/FYP/scripts/custom/result_view.js" type="text/javascript"></script>  
<div id="resultContainer" class="resultContainer">
    <div id="searchBar" class="searchBar">
        <input type="text" id="searchTxt" class="searchTxt"/>
        <input type="button" value="Search" id="btnsearch" class="btnsearch"/>
    </div>
    <hr>
    <div id="summarizereview" class="summarizereview">
        <div id="bestPositiveReview" class="bestPositiveReview">
            <h4>Best Positive Review</h4>
            <p id="bestPosRevTxt" class="bestPosRevTxt">There is no best positive review found</p>
        </div>
        <div id="bestNegativeReview" class="bestNegativeReview">
            <h4>Best Negative Review</h4>
            <p id="bestNegRevTxt" class="bestNegRevTxt">There is no best negative review found </p>
        </div>
    </div>
    <div id="percentageContainer" class="percentageContainer">
        <label style="color: aliceblue">Suggestion:</label><span id="txtsuggestion" style="color: aliceblue"></span>
        <div id="negativePerContainer" class="negativePerContainer">
            <span class="percentageTitles">Negative</span>
            <span id="txtnegtivePer" class="txtnegtivePer"></span>
        </div>
        <div id="neutralPerContainer" class="neutralPerContainer">
            <span class="percentageTitles">Neutral</span>
            <span id="txtneutralPer" class="txtneutralPer"></span>
        </div>
        <div id="positivePerContainer" class="positivePerContainer">
            <span class="percentageTitles">Positive</span>
            <span id="txtpositivePer" class="txtpositivePer"></span>
        </div>
        <div id="reviewStatics" class="reviewStatics">
            <span style="color: aliceblue">Number of Reviews analyzed:</span><span id="txtReviewStatic" class="txtReviewStatic" style="color: aliceblue"></span>
        </div>
    </div>
<!--    <div id="sideNavContainer">
        <ul id="sideNav">
            <li>
                <span id="sideNavShare">Share</span>
                <ul>
                    <li>Rate Me!</li>
                    <li>Facebook</li>
                    <li>Twitter</li>
                </ul>
            </li>
            <li>
                <span id="sideNavSave">Save Result</span>
            </li>
            <li>
                <span id="sideNavCharts">Show Charts</span>
            </li>
        </ul>
    </div>-->
</div>
<script type="text/javascript">
    var properties = {
        btnsearch: $("#btnsearch"),
        searchTxt: $("#searchTxt"),
        loadPageContainer: $("#loadPageContainer"),
        txtnegtivePer: $("#txtnegtivePer"),
        txtneutralPer: $("#txtneutralPer"),
        txtpositivePer: $("#txtpositivePer"),
        txtReviewStatic: $("#txtReviewStatic"),
        sideNavShare: $("#sideNavShare"),
        sideNavSave: $("#sideNavSave"),
        sideNavCharts: $("#sideNavCharts"),
        bestPosRevTxt: $("#bestPosRevTxt"),
        bestNegRevTxt: $("#bestNegRevTxt"),
        txtsuggestion: $("#txtsuggestion"),
        baseUrl: "<?php echo base_url(); ?>"
    };
    
    //Initialize the initialize the script
    Result.ResultView.Init(properties);
</script>