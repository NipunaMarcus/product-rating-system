(function ($) {
    App.Namespace.define("DialogHandler", {
        // define the error dialog box.
        ErrorDialog: function (element, title, message, ok) {
            $(element).attr("title", title);
            $(element).find('p').text(message);
            $(element).dialog({
                resizable: false,
                modal: true
            });

            if (ok != null) {
                $(element).dialog("option", "buttons",
                        [
                            {
                                text: "Ok",
                                icons: {
                                    primary: "ui-icon-error"
                                },
                                click: function () {
                                    ok();
                                }
                            }
                        ]
                        );
            }

            $(element).dialog("open");
        },
        // define the information dialog box.
        InformationDialog: function (element, title, message, ok) {
            $(element).attr("title", title);
            $(element).find('p').text(message);
            $(element).dialog({
                resizable: false,
                modal: true
            });

            if (ok != null) {
                $(element).dialog("option", "buttons",
                        [
                            {
                                text: "Ok",
                                icons: {
                                    primary: "ui-icon-error"
                                },
                                click: function () {
                                    ok();
                                }
                            }
                        ]
                        );
            }

            $(element).dialog("open");
        },
        // Define the confirmation dialog
        ConfirmationDialog: function (element, title, message, Yes, No) {
            $(element).attr("title", title);
            $(element).find('p').text(message);
            $(element).dialog({
                resizable: false,
                modal: true
            });

            if (No != null && Yes != null) {
                $(element).dialog("option", "buttons",
                        [
                            {
                                text: "No",
                                icons: {
                                    primary: "ui-icon-error"
                                },
                                click: function () {
                                    No();
                                }
                            },
                            {
                                text: "Yes",
                                icons: {
                                    primary: "ui-icon-error"
                                },
                                click: function () {
                                    Yes();
                                }
                            }
                        ]
                        );
            }

            $(element).dialog("open");
        },
        // define the Edit answer dialog box.
        EditAnswerDialog: function (element, title, data, update, cancel) {
            $(element).attr("title", title);
            $(element).find('#updateanswerContent').val(data.content);
            $(element).dialog({
                resizable: false,
                modal: true,
                width: 700,
                height: 500,
            });

            if (update != null) {
                $(element).dialog("option", "buttons",
                        [
                            {
                                text: "Cancel",
                                click: function () {
                                    cancel();
                                }
                            },
                            {
                                text: "Update",
                                icons: {
                                    primary: "ui-icon-error"
                                },
                                click: function () {
                                    update(data.answer_id);
                                }
                            }
                        ]
                        );
            }

            $(element).dialog("open");
        },
        // define the edit question dialog box.
        EditQuestionDialog: function (element, title, data, update, cancel) {
            $(element).attr("title", title);
            $(element).find('#txtquestionContent').val(data.content);
            $(element).find('#txtQuestionTitle').val(data.title);
            $(element).find('#cboxcategory').val(data.category_id);

            $(element).dialog({
                resizable: false,
                modal: true,
                width: 700,
                height: 600,
            });

            if (update != null) {
                $(element).dialog("option", "buttons",
                        [
                            {
                                text: "Cancel",
                                click: function () {
                                    cancel();
                                }
                            },
                            {
                                text: "Update",
                                icons: {
                                    primary: "ui-icon-error"
                                },
                                click: function () {
                                    update(data.question_id);
                                }
                            }
                        ]
                        );
            }

            $(element).dialog("open");
        },
        //Chnage password dialog
        ChangePasswordDialog: function (element, title, message, change, cancel) {
            $(element).attr("title", title);
            //$(element).find('p').text(message);
            $(element).dialog({
                resizable: false,
                modal: true,
                width: 600
            });

            $(element).addClass('changePasswordDialog');
            if (change != null) {
                $(element).dialog("option", "buttons",
                        [
                            {
                                text: "Cancel",
                                click: function () {
                                    cancel();
                                }
                            },
                            {
                                text: "Change",
                                icons: {
                                    primary: "ui-icon-error"
                                },
                                click: function () {
                                    change();
                                }
                            }
                        ]
                        );
            }

            $(element).dialog("open");
        },
        //Change user image dialog
        ChangeImgDialog: function (element, title, message, change, cancel) {
            $(element).attr("title", title);
            $(element).dialog({
                resizable: false,
                modal: true,
                width: 500,
                height: 200
            });

            if (change != null) {
                $(element).dialog("option", "buttons",
                        [
                            {
                                text: "Cancel",
                                click: function () {
                                    cancel();
                                }
                            },
                            {
                                text: "Change",
                                icons: {
                                    primary: "ui-icon-error"
                                },
                                click: function () {
                                    change();
                                }
                            }
                        ]
                        );
            }

            $(element).dialog("open");
        }
    });
}(jQuery));


