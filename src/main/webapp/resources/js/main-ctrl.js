/**
 * main controller
 */
app.controller("MainCtrl", MainCtrl);

var SUCCESS_MESSAGE = "恭喜你，闯关成功啦! (*^ω^*)";

function MainCtrl($http, $scope, $rootScope, $http, $log, $state, $timeout) {

	$scope.max_stage = Constants.max_stage;

	$scope.checkUserInfo = function() {
		$timeout(function() {
			$http({
				method : 'GET',
				url : Constants.server_url + '/user/info',
			}).success(function(text, status, headers, config) {
				if (text.status != 200) {
					Notify.error(text.statusText);
					return;
				}

				$scope.user = text.data;
			}); // $http

		});// $timeout
	}

	$rootScope.$on('checkUserInfo', function(event) {
		$scope.checkUserInfo();
	});
	
	//$scope.checkUserInfo();

}
