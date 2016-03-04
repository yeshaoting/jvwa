/**
 * content service
 */
app.service("ContentService", [ '$rootScope', '$http', '$timeout', ContentService ]);

function ContentService($rootScope, $http, $timeout) {

  this.query = function($scope) {

    $timeout(function() {
      $http({
        method : 'GET',
        url : window.Constants.server_url + '/content/query',
        params : $scope.queryParams
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

  this.changeStatus = function($scope, content) {

    $timeout(function() {
      $http({
        method : 'GET',
        url : window.Constants.server_url + '/content/changeStatus/' + content.id,
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        content.status = !content.status;
        content.updateTime = text.data.updateTime;
        
      }); // $http

    });// $timeout

  }
  
  this.save = function($scope, contentParams) {

    $timeout(function() {
      $http({
        method : 'POST',
        url : window.Constants.server_url + '/content/save',
        data : $.param(contentParams, true),
        headers : {
          'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
        }
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }
        
        $rootScope.$broadcast("doContentQuery", $scope.queryParams);

      }); // $http

    });// $timeout

  }
  
  this.forceDel = function($scope, id) {
    $timeout(function() {
      $http({
        method : 'DELETE',
        url : window.Constants.server_url + '/content/forceDel/' + id
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        $rootScope.$broadcast("doContentQuery", $scope.queryParams);

      }); // $http

    });// $timeout
  }

};
