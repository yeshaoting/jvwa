/**
 * stage controller
 */
app.controller("AdminCtrl", AdminCtrl);

var SUCCESS_MESSAGE = "恭喜你闯关成功! (*^ω^*)";

function AdminCtrl($http, $scope, $rootScope, $http, $log, $state) {

	$scope.upgrade = function() {
		alert(SUCCESS_MESSAGE);
		$state.go("stage2");
	}

}
