<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Code Challenge 3</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <style type="text/css">
        .invalidInput {
            border: 2px solid #ed9e9e;
            border-radius: 7px;
            outline: none;
            box-shadow: 0 0 10px #ed9e9e;
        }
    </style>
</head>
<body ng-app="codechallengeApp" ng-controller="MainController">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <h1>Das Code Challenge 3</h1>
                <p>home awe inspiring text right here</p>
            </div>
        </div>

        <div class="row">
            <div class="col-12 well">
                <input id="name" placeholder="Enter your name here" ng-model="name" />
                <input id="send" type="button" value="Say hello" ng-click="send()" />
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                    <ul id="recent">
                        <li ng-repeat="request in requests">{{request.Content}}</li>
                    </ul>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
    <script type="application/javascript">
        var app = angular.module( 'codechallengeApp', [] );
        app.controller( 'MainController', function( $scope ) {
            $scope.requests = Array();
            $scope.name = "";

            $scope.update = function(){
                $.get("/api/messages/recent", function(json) {
                    // clear array
                    $scope.$apply(function () {
                        $scope.requests = Array();
                        $.each(json.messages, function (message) {
                            $scope.requests.push(json.messages[message].Message)
                        });
                    });
                })
            };
            $scope.update();

            $scope.send = function(){
                console.log("Magic");
                var name = $scope.name;
                name = encodeURIComponent(name);

                $.post("/api/messages/names/" + name, function() {
                    // Only remove the name if successfull
                    $scope.$apply(function(){
                        $scope.name = "";
                    });
                    // Update
                    $scope.update();
                }).fail(function() {
                    // We do not want to remove the name, give the user
                    // a chance to correct the error
                    $("#name").addClass("invalidInput");
                });
            };

            $scope.$watch('[name]', function(){
                var valid = true;
                if($scope.name.length < 3){
                    valid = false;
                }
                // Maby more checks later?

                if(valid){
                    $("#name").removeClass("invalidInput");
                    $("#send").prop("disabled",false);
                } else {
                    if($scope.name.length > 0) {
                        $("#name").addClass("invalidInput");
                    }
                    $("#send").prop("disabled",true);
                }
            });
        });
    </script>
</body>
</html>
