/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function ($) {
    // Define a new namespace
    App.Namespace.define("Home.HomeView", {
        // Holds the current page state.
        State: null,
        // Initialize the about view.
        Init: function (properties) {
            // Dispose the page state.
            Home.HomeView.Dispose();

            // Set the page state.
            Home.HomeView.State = properties;

            // Configure the page.
            Home.HomeView.Configure();
        },
        // Configure the page
        Configure: function () {
            // Reference to the page state.
            var state = Home.HomeView.State;

            // searchHandler: Reference for the SearchHandler function
            var searchHandler = new SearchHandler();

            function SearchHandler() {
                var instance = this;

                // Initialize the handler.
                this.init = function () {
                    // Bind click event to the search button
                    $(state.btnSearch).click(executeSearch);
                };

                // Execute the search.
                var executeSearch = function () {
                    var url = state.baseUrl + "index.php/search/searchReviews";
                    $.ajax({
                        url: url,
                        type: "POST",
                        dataType: "json",
                        data: {
                            content: $(state.txtSearch).val()
                        },
                        success: function (data) {
                            if (data == null) {

                            } else if (data.isSuccess) {
                                // set the search data.
                                Home.MainLayout.SearchObj = data;
                                
                                // Load the result view.
                                loadResultView();
                            }else{
                                
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
        },
        // Dispose the page state.
        Dispose: function () {
            Home.HomeView.State = null;
        }
    });
}(jQuery));