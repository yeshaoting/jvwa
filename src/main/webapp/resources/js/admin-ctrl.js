/**
 * stage controller
 */
app.controller("AdminCtrl", AdminCtrl);

function AdminCtrl($http, $scope, $rootScope, $http, $log, $state, $location) {

	$scope.stage3Upgrade = function() {
		$http({
			method : 'POST',
			url : Constants.server_url + '/security/stage3/pass'
		}).then(function successCallback(response) {
			if (response.data.status != 200) {
				console.log(response.data.statusText);
				Notify.error("登录验证失败，要再接再厉哦。");
				return;
			}
			
			alert(SUCCESS_MESSAGE);
			$state.go("stage4", {}, {"reload": true});
		}, function errorCallback(response) {
			Notify.error(response.data.statusText);
		});
	}

}
