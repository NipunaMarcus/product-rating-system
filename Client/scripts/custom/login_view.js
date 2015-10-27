/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function ($) {
    // Define a new namespace for the script. structure: [Controller].[Page]
    App.Namespace.define("Authentication.LoginView", {
        // State of the web page.
        State: null,
        // Initialize the script.
        Init: function (properties) {
            //Dispose the page current state.
            Home.MainLayout.Dispose();

            //Set the page state.
            Home.MainLayout.State = properties;

            //Configure the functions.
            Home.MainLayout.Configure();
        },
        // Configure the page functionality.
        Configure: function () {
            // Reference to the page state.
            var state = Home.MainLayout.State;
        },
        // Dispose the page current state.
        Dispose: function () {
            // Reset the state.
            Home.MainLayout.State = null;
        }
    });
}(jQuery));


