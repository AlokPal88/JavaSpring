<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app="myclients">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>MyClients Single Page Angular Application</title>

	<script type="text/javascript" src="<c:url value="/resources/js/vendor/angular.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/vendor/angular-route.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/vendor/jeneva.angular.js" />"></script>

	<script type="text/javascript" src="<c:url value="/resources/js/app.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/filters/idtext.js" />"></script>

	<script type="text/javascript" src="<c:url value="/resources/js/controllers/clientCreateController.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/controllers/clientListController.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/controllers/clientEditController.js" />"></script>
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"/>
</head>
<body>
	<ng-view>
	</ng-view>
</body>
</html>