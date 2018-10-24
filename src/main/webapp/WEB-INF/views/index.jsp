<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html ng-app="app">
<head>
    <title>Push App</title>
</head>



<body style="min-height:100%; height:auto;">

<script src="libs/angularjs/1.7.2/angular.js"></script>
<script src="libs/angular-ui-router/1.0.15/angular-ui-router.js"></script>
<script src="libs/angularjs/1.7.2/angular-resource.js"></script>
<script src="libs/angular-animate/1.7.2/angular-animate.js"></script>
<script src="libs/angular-material/1.1.5/angular-material.js"></script>
<script src="libs/angular-aria/1.6.9/angular-aria.min.js"></script>
<script src="resources/app.js"></script>
<script src="resources/app/login/loginController.js"></script>
<script src="resources/app/login/loginDataService.js"></script>
<script src="resources/app/main/mainController.js"></script>
<script src="resources/app/main/mainDataService.js"></script>
<script src="resources/app/users/usersController.js"></script>
<script src="resources/app/notifications/notificationsController.js"></script>
<script src="resources/app/notifications/notificationsDataService.js"></script>

<link rel="stylesheet" href="libs/bootstrap/4.1.1/css/bootstrap.css">
<link rel="stylesheet" href="libs/angular-material/1.1.5/angular-material.min.css"/>
<link rel="stylesheet" href="resources/css/app.css">


<header style="background-color: black">
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header" style="color: #007bff;">
                <a class="navbar-brand">Push The Tempo</a>
            </div>

            <div class="nav navbar-nav navbar-right" style="color: #007bff;" ng-if="isAuthenticated">
                <a class="navbar-brand" ng-click="logout()" style="cursor: pointer">Logout</a>
            </div>
        </div>
    </nav>
</header>

<div ui-view></div>

</body>
</html>
