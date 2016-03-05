/**
 * stage controller
 */
app.controller("StageCtrl", StageCtrl);

var SUCCESS_MESSAGE = "恭喜你，闯关成功啦! (*^ω^*)";

function StageCtrl($http, $scope, $rootScope, $http, $log, $state) {

	$scope.login = function() {
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

	
	$scope.login2 = function() {
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
	
	
	$scope.phone = "13800138000";
	$scope.sendSmsCode = function() {
		$http({
			method : 'POST',
			url : Constants.server_url + '/sohu/sms/code/send?phone=' + $scope.phone
		}).then(function successCallback(response) {
			if (response.data.status != 200) {
				Notify.error(response.data.statusText);
				return;
			}
			
			alert("新的3位数字验证码已经通过短信，发送到" + $scope.phone + "的手机上，请注意查收！");
		}, function errorCallback(response) {
			Notify.error(response.data.statusText);
		});
		
	}
	
	$scope.verfiySmsCode = function() {
		if (!$scope.code) {
			Notify.error("请输入短信验证码！");
			return;
		}
		
		var params = {
			phone: $scope.phone,
			code: $scope.code
		}
		$http({
			method : 'POST',
			url : Constants.server_url + '/sohu/sms/code/verfiy?' + $.param(params, true)
		}).then(function successCallback(response) {
			if (response.data.status != 200) {
				Notify.error(response.data.statusText);
				return;
			}
			
			alert(SUCCESS_MESSAGE);
			$state.go("stage6");
		}, function errorCallback(response) {
			Notify.error(response.data.statusText);
		});
		
	}
	
	//$scope.image_url = Constants.resources_path + '/img/image.jpg';
	$scope.image_url = Constants.server_url + '/sohu/stage6.jsp?file=image.jpg';
	$scope.checkCode = function() {
		$http({
			method : 'POST',
			url : Constants.server_url + '/sohu/checkCode?code=' + $scope.code
		}).then(function successCallback(response) {
			if (response.data.status != 200) {
				Notify.error(response.data.statusText);
				return;
			}
			
			alert(SUCCESS_MESSAGE);
			$state.go("stage7");
		}, function errorCallback(response) {
			Notify.error(response.data.statusText);
		});
	}
}

