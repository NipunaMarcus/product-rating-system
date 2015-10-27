/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function ($) {
    // Define a new namespace
    App.Namespace.define("Result.ResultView", {
        // Holds the current page state.
        State: null,
        // Initialize the about view.
        Init: function (properties) {
            // Dispose the page state.
            Result.ResultView.Dispose();

            // Set the page state.
            Result.ResultView.State = properties;

            // Configure the page.
            Result.ResultView.Configure();
        },
        // Configure the page
        Configure: function () {
            // Reference to the page state.
            var state = Result.ResultView.State;

            // searchHandler: Reference for the SearchHandler function
            var searchHandler = new SearchHandler();

            // uiHandler: Reference for the UIHandler function
            var uiHandler = new UIHandler();

            function SearchHandler() {
                var instance = this;

                // Initialize the handler.
                this.init = function () {
                    // Bind click event to the search button
                    $(state.btnsearch).click(executeSearch);
                };

                // Execute the search.
                var executeSearch = function () {
                    var url = state.baseUrl + "index.php/search/searchReviews";
                    $.ajax({
                        url: url,
                        type: "POST",
                        dataType: "json",
                        data: {
                            content: $(state.searchTxt).val()
                        },
                        success: function (data) {
                            if (data == null) {

                            } else if (data.isSuccess) {
                                // set the search data.
                                Home.MainLayout.SearchObj = data;

                                // Load the result view.
                                loadResultView()
                            } else {

                            }
                        }
                    });
                };

                // Load the result view.
                var loadResultView = function () {
                    var url = state.baseUrl + "index.php/search/loadResultView";
                    $.ajax({
                        url: url,
                        type: "POST",
                        success: function (data) {
                            // Empty the loaded page container
                            $(state.loadPageContainer).empty();

                            // Rebind the loading page
                            $(state.loadPageContainer).html(data);
                        }
                    });
                };

                // Initialize the handler.
                instance.init();
            }

            function UIHandler() {
                var instance = this;

                this.init = function () {
                    loadSearchedResult();
                };

                var loadSearchedResult = function () {
                    var data = Home.MainLayout.SearchObj;
                    $(state.txtnegtivePer).text(data.negativePercentage + "%");
                    $(state.txtneutralPer).text(data.neutralPercentage + "%");
                    $(state.txtpositivePer).text(data.positivePercentage + "%");
                    $(state.txtReviewStatic).text(data.reviewCount);
                    
                    if(data.negativePercentage > data.neutralPercentage &&  data.negativePercentage >data.positivePercentage){
                        $(state.txtsuggestion).text("Don't buy this product");
                    }else if(data.neutralPercentage > data.negativePercentage &&  data.neutralPercentage >data.positivePercentage){
                        $(state.txtsuggestion).text("Well I cannot say. You have to decide");
                    }else if(data.positivePercentage > data.neutralPercentage && data.positivePercentage > data.negativePercentage){
                        $(state.txtsuggestion).text("You should buy this product");
                    }else{
                        $(state.txtsuggestion).text("Sorry!.I don't know.");
                    }
                };

                instance.init();
            }
        },
        // Dispose the page state.
        Dispose: function () {
            Result.ResultView.State = null;
        }
    });
}(jQuery));