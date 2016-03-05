/**
 * stage controller
 */
app.controller("AdminCtrl", AdminCtrl);

var SUCCESS_MESSAGE = "恭喜你闯关成功! (*^ω^*)";

function AdminCtrl($http, $scope, $rootScope, $http, $log, $state, $location) {

	$scope.upgrade = function() {
		alert(SUCCESS_MESSAGE);
		changeLocation(Constants.server_url + '/sohu/index#/stage4', true);
	}

	//be sure to inject $scope and $location
	var changeLocation = function(url, forceReload) {
	  $scope = $scope || angular.element(document).scope();
	  if(forceReload || $scope.$$phase) {
	    window.location = url;
	  }
	  else {
	    //only use this if you want to replace the history stack
	    //$location.path(url).replace();

	    //this this if you want to change the URL and add it to the history stack
	    $location.path(url);
	    $scope.$apply();
	  }
	};
}
