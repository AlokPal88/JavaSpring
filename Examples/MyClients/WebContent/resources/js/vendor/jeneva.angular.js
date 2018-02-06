var $jeneva = $jeneva || {};
$jeneva.settings = {};
$jeneva.settings.baseUrl = "/";

$jeneva.module = angular.module("jenevamodule", []);
$jeneva.module.factory("jeneva", ["$http", "$q", "$rootScope", function ($http, $q, $rootScope) {
	var service = { onBeforeAjax: null, onAfterAjax: null };
	service.scope = $rootScope;

	service.url = function(link) {
		return $jeneva.settings.baseUrl + link;
	};

	service.setScope = function(scope) {
		service.scope = scope;
	};

	service.post = function (method, input, replaceErrors) {
		if (replaceErrors != false) {
			replaceErrors = true;
		}

		service.ajaxStart();
		var deferred = $q.defer();
		$http({
			method: 'POST',
			url: service.url(method),
			data: input
		})
		.success(function(data, status) {
			service.clearErrors();
			deferred.resolve(data);
			service.ajaxEnd();
		})
		.error(function(data, status) {
			deferred.reject();
			service.ajaxEnd();
			if (replaceErrors) {
				service.clearErrors();
			}

			service.showErrors(data);
		});
		return deferred.promise;
	};

	service.get = function (method, replaceErrors) {
		if (replaceErrors != true) {
			replaceErrors = false;
		}

		service.ajaxStart();
		var deferred = $q.defer();
		$http({
			method: 'GET',
			url: service.url(method)
		})
		.success(function(data, status) {
			deferred.resolve(data);
			service.ajaxEnd();
		})
		.error(function(data, status) {
			deferred.reject();
			service.ajaxEnd();
			if (replaceErrors) {
				service.clearErrors();
			}

			service.showErrors(data);
		});
		return deferred.promise;
	};

	service.all = function(promises) {
		return $q.all(promises);
	};

	service.ajaxCallCount = 0;

	service.ajaxStart = function() {
		if(0 == service.ajaxCallCount) {
			if(service.onBeforeAjax) service.onBeforeAjax();
		}

		service.ajaxCallCount++;
	};

	service.ajaxEnd = function() {
		service.ajaxCallCount--;
		if (0 == service.ajaxCallCount) {
			setTimeout(function () {
				if (0 == service.ajaxCallCount) {
					if (service.onAfterAjax) service.onAfterAjax();
				}
			}, 200);
		}
	};

	service.showErrors = function(fail) {
		if (!service.scope) return;
		service.scope.$$jverrors = fail.failures;
	};

	service.clearErrors = function() {
		if (!service.scope) return;
		service.scope.$$jverrors = null;
	};

	return service;
}]);

$jeneva.module.directive("jvPath", function ($http) {
	return {
		require: "ngModel",
		link: function (scope, element, attrs, controller) {
			scope.$watch(attrs.ngModel, function () {
				controller.$setValidity("jvpath", true);
			});

			scope.$watch("$$jverrors", function (errors, oldVal) {
				if (!errors) {
					controller.$setValidity("jvpath", true);
					return;
				}

				var key = attrs.jvPath;
				if (key) {
					var valid = true;
					var msglist = [];
					for (var i = 0; i < errors.length; i++) {
						if (key == errors[i].key) {
							valid = false;
							msglist.push(errors[i].text);
						}
					}

					if (valid) {
						controller.$setValidity("jvpath", true);
						controller.$jvlist = null;
					}
					else {
						controller.$setValidity("jvpath", false);
						controller.$jvlist = msglist;
					}
				}
			});
		}
	}
});

$jeneva.module.directive("jvErrorKey", function ($http) {
	return {
		link: function (scope, element, attrs, controller) {
			scope.$watch("$$jverrors", function (errors, oldVal) {
				element.html("");
				if (!errors) return;

				var key = attrs.jvErrorKey;
				for (var i = 0; i < errors.length; i++) {
					if (!errors[i].key && !key) {
						element.html(errors[i].text);
						break;
					}

					if (key == errors[i].key) {
						element.html(errors[i].text);
						break;
					}
				}
			});
		}
	}
});