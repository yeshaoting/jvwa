/**
 * stage controller
 */
app.controller("Stage1Ctrl", Stage1Ctrl);
app.controller("Stage1Ctrl", Stage1Ctrl);

var SUCCESS_MESSAGE = "恭喜你闯关成功! (*^ω^*)";

function Stage1Ctrl($http, $scope, $rootScope, $http, $log, $state) {

	$scope.submit = function() {
		// var username = "stage1_user";
		// var password = "tHis1Is3aSim3p#ab$";
		var username = "yeshaoting";
		var password = "yeshaoting";

		if (username == $scope.username && password == $scope.password) {
			alert(SUCCESS_MESSAGE);
			$state.go("stage2");
		} else {
			Notify.error("用户名或密码错误，请重试！");
		}
	}

}

function Stage2Ctrl($http, $scope, $rootScope, $http, $log, $state) {
	$scope.submit = function() {
		var params = {
			username : $scope.username,
			password : $scope.password
		};
		
		$http({
			method : 'POST',
			url : Constants.server_url + '/sohu/stage2?' + $.param(params, true)
		}).then(function successCallback(response) {
			if (response.data.status != 200) {
				console.log(response.data.statusText);
				Notify.error("登录验证失败，要再接再厉哦。");
				return;
			}
			
			alert(SUCCESS_MESSAGE);
			$state.go("stage3");
		}, function errorCallback(response) {
			Notify.error(response.data.statusText);
		});
	}
}
