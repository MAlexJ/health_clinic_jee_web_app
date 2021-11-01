<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="${pageContext.request.contextPath}/admin">
    <title>Health clinic</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="icon" href="${pageContext.request.contextPath}/admin/img/favicon.ico" type="image/x-icon">
    <%--  styles  --%>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/webjars/bootstrap/4.1.3/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/admin/css/dashboard.css"/>

    <!-- bootstrap scripts -->
    <script src="${pageContext.request.contextPath}/webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>

    <%--  angularjs  --%>
    <script src="${pageContext.request.contextPath}/webjars/angularjs/1.8.2/angular.min.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/angularjs/1.8.2/angular-cookies.min.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/angular-ui-router/1.0.20/angular-ui-router.min.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/angular-sanitize/1.8.2/angular-sanitize.min.js"></script>

    <%--  scripts  --%>
    <script src="${pageContext.request.contextPath}/admin/js/modules/module.js"></script>
    <script src="${pageContext.request.contextPath}/admin/js/services/service.js"></script>
    <script src="${pageContext.request.contextPath}/admin/js/controllers/navBarController.js"></script>

    <script src="${pageContext.request.contextPath}/admin/js/components/home.js"></script>
    <script src="${pageContext.request.contextPath}/admin/js/components/appointments.js"></script>
    <script src="${pageContext.request.contextPath}/admin/js/components/management.js"></script>

</head>
<body ng-app="app">
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top" ng-controller="navBarController">
    <button id="btn-menu-toggle" class="navbar-toggler navbar-toggler-right hidden-lg-up"
            type="button"
            data-toggle="collapse"
            data-target="#navbarsExampleDefault"
            aria-controls="navbarsExampleDefault"
            aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="navbar-brand brandDiv">Health clinic</div>
    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li ui-sref-active="active" class="nav-item" ng-click="hidingNavBar()">
                <a ui-sref="home" class="nav-link">Home</a>
            </li>
            <li ui-sref-active="active" class="nav-item" ng-click="hidingNavBar()">
                <a ui-sref="appointments" class="nav-link">Appointments</a>
            </li>
            <li ui-sref-active="active" class="nav-item" ng-click="hidingNavBar()">
                <a ui-sref="management" class="nav-link">Management</a>
            </li>
        </ul>
        <form class="form-inline mt-2 mt-md-0"
              action="${pageContext.request.contextPath}/admin/logout"
              method="get">
            <button class="btn btn-outline-danger my-2 my-sm-0 btnSignUp" type="submit">SignUp</button>
        </form>
    </div>
</nav>
<ui-view>
    <div class="row justify-content-md-center" style="padding-top: 120px;">
        <div class="row">
            <div class="col-lg-4"></div>
            <div class="col-lg-4">
                <div class="loader"></div>
            </div>
            <div class="col-lg-4"></div>
        </div>
    </div>
</ui-view>
</body>
</html>
