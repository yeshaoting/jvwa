/**
 * changelog service
 */
app.service("ChangelogService", [ '$rootScope', '$http', '$timeout', ChangelogService ]);

function ChangelogService($rootScope, $http, $timeout) {

  this.query = function($scope, queryParams) {

    $timeout(function() {
      $http({
        method : 'GET',
        url : window.Constants.server_url + '/changelog/query',
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

  this.changeStatus = function($scope, changelog) {

    $timeout(function() {
      $http({
        method : 'GET',
        url : window.Constants.server_url + '/changelog/changeStatus/' + changelog.id,
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        changelog.status = !changelog.status;
        changelog.updateTime = text.data.updateTime;

      }); // $http

    });// $timeout

  }

  this.del = function($scope, id) {
    $timeout(function() {
      $http({
        method : 'DELETE',
        url : window.Constants.server_url + '/changelog/del/' + id
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        $rootScope.$broadcast("doChangelogQuery", $scope.queryParams);

      }); // $http

    });// $timeout
  }

  this.save = function($scope, changelogParams) {

    $timeout(function() {
      $http({
        method : 'POST',
        url : window.Constants.server_url + '/changelog/save',
        data : $.param(changelogParams, true),
        headers : {
          'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
        }
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        $rootScope.$broadcast("doChangelogQuery", $scope.queryParams);

      }); // $http

    });// $timeout

  }

};
