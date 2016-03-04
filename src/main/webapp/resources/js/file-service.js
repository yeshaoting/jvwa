/**
 * file service
 */
app.service("FileService", [ '$rootScope', '$http', '$timeout', FileService ]);

function FileService($rootScope, $http, $timeout) {

  this.query = function($scope, queryParams) {

    $timeout(function() {
      $http({
        method : 'GET',
        url : window.Constants.server_url + '/file/query',
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
  
  this.save = function($scope, fileParams) {

    $timeout(function() {
      $http({
        method : 'POST',
        url : window.Constants.server_url + '/file/save',
        data : $.param(fileParams, true),
        headers : {
          'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
        }
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        $rootScope.$broadcast("doFileQuery", $scope.queryParams);

      }); // $http

    });// $timeout

  }

};
