/**
 * user service
 */
app.service("UserService", [ '$rootScope', '$http', '$timeout', UserService ]);

function UserService($rootScope, $http, $timeout) {

  this.query = function($scope, queryParams) {

    $timeout(function() {
      $http({
        method : 'GET',
        url : window.Constants.server_url + '/user/query',
        params : queryParams
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        $scope.data = text.data;
        $scope.data.totalPage = Math.ceil($scope.data.totalCount / $scope.data.size);
        
      }); // $http

    });// $timeout

  };
  
  this.changeStatus = function($scope, user) {

    $timeout(function() {
      $http({
        method : 'GET',
        url : window.Constants.server_url + '/user/changeStatus/' + user.id,
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        user.status = !user.status;
        user.updateTime = text.data.updateTime;
        
      }); // $http

    });// $timeout

  }

  this.save = function($scope, userParams) {

    $timeout(function() {
      $http({
        method : 'POST',
        url : window.Constants.server_url + '/user/save',
        data : $.param(userParams, true),
        headers : {
          'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
        }
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        $rootScope.$broadcast("doUserQuery", $scope.queryParams);

      }); // $http

    });// $timeout

  }

};
