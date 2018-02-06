﻿var myclients = myclients || {};
myclients.app = angular.module("myclients", ["ngRoute", "jenevamodule"]);

myclients.app.config(function ($routeProvider) {
	$routeProvider
		.when("/", {
			templateUrl: "client/list",
			controller: "clientListController"
		})
		.when("/client/list", {
			templateUrl: "client/list",
			controller: "clientListController"
		})
		.when("/client/create", {
			templateUrl: "client/create",
			controller: "clientCreateController"
		})
		.when("/client/edit/:id", {
			templateUrl: "client/edit",
			controller: "clientEditController"
		})
		.otherwise({
			templateUrl: "home/notfound"
		});
});

$jeneva.settings.baseUrl = "/myclients/";