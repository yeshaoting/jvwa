/**
 * stage controller
 */
app.controller("StageCtrl", StageCtrl);

function StageCtrl($http, $scope, $rootScope, $http, $log, $state, $timeout) {

	$scope.maxStage = 10;
	
	$scope.nextStage = function() {
		var nextStage = $scope.user.stage + 1;
		$state.go("stage" + nextStage, {}, {"reload": true});
	}
	
	$scope.stage1Submit = function() {
		// var stage1Username = "stage1_user";
		// var state1Password = "tHis1Is3aSim3p#ab$";
		var stage1Username = "yeshaoting";
		var state1Password = "yeshaoting";

		if (stage1Username == $scope.stage1Username && state1Password == $scope.state1Password) {
			$http({
				method : 'POST',
				url : Constants.server_url + '/sohu/stage1/pass'
			}).then(function successCallback(response) {
				if (response.data.status != 200) {
					console.log(response.data.statusText);
					Notify.error(response.data.statusText);
					return;
				}
				
				alert(SUCCESS_MESSAGE);
				$state.go("stage2", {}, {"reload": true});
			}, function errorCallback(response) {
				Notify.error(response.data.statusText);
			});
		} else {
			Notify.error("用户名或密码错误，请重试！");
		}
	}

	
	$scope.stage2Submit = function() {
		var params = {
			username : $scope.stage2Username,
			password : $scope.state2Password
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
			$state.go("stage3", {}, {"reload": true});
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
	
	$scope.buy = function(id, price) {
		var params = {
			id: id,
			price: price
		}
	
		$http({
			method : 'POST',
			url : Constants.server_url + '/sohu/stage4/pass?' + $.param(params, true)
		}).then(function successCallback(response) {
			if (response.data.status != 200) {
				Notify.error(response.data.statusText);
				$scope.stageMoney();
				return;
			}
			
			alert(SUCCESS_MESSAGE);
			$state.go("stage5", {}, {"reload": true});
		}, function errorCallback(response) {
			Notify.error(response.data.statusText);
		});
	}
	
	$scope.stageMoney = function() {
		$timeout(function() {
			$http({
				method : 'GET',
				url : Constants.server_url + '/sohu/stage4/money',
			}).success(function(text, status, headers, config) {
				if (text.status != 200) {
					Notify.error(text.statusText);
					return;
				}

				$scope.virtualMoney = text.data;
			}); // $http

		});// $timeout
	}
	
	$scope.stageMoney();
	
	$scope.verfiySmsCode = function() {
		if (!$scope.smsCode) {
			Notify.error("请输入短信验证码！");
			return;
		}
		
		var params = {
			phone: $scope.phone,
			code: $scope.smsCode
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
			$state.go("stage6", {}, {"reload": true});
		}, function errorCallback(response) {
			Notify.error(response.data.statusText);
		});
		
	}
	
	//$scope.image_url = Constants.resources_path + '/img/image.jpg';
	$scope.image_url = Constants.server_url + '/sohu/stage6.jsp?file=image.jpg';
	$scope.checkSecureCode = function() {
		$http({
			method : 'POST',
			url : Constants.server_url + '/sohu/checkCode?code=' + $scope.secureCode
		}).then(function successCallback(response) {
			if (response.data.status != 200) {
				Notify.error(response.data.statusText);
				return;
			}
			
			alert(SUCCESS_MESSAGE);
			$state.go("stage7", {}, {"reload": true});
		}, function errorCallback(response) {
			Notify.error(response.data.statusText);
		});
	}
}

