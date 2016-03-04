/**
 * module content service
 */
app.service("ModuleContentService", [ '$rootScope', '$http', '$timeout', ModuleContentService ]);

function ModuleContentService($rootScope, $http, $timeout) {

  this.query = function($scope, moduleId, queryParams) {
    $timeout(function() {
      $http({
        method : 'GET',
        url : window.Constants.server_url + '/module/content/query/' + moduleId,
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
  }

  this.selectByModuleId = function($scope, moduleId) {
    $timeout(function() {
      $http({
        method : 'GET',
        url : window.Constants.server_url + '/module/content/selectByModuleId/' + moduleId,
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        $scope.selectContents = text.data;

      }); // $http

    });// $timeout
  }

  this.selectByLinkType = function($scope, linkType) {
    $timeout(function() {
      $http({
        method : 'GET',
        url : window.Constants.server_url + '/module/content/selectByLinkType/' + linkType,
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        $scope.selectContents = text.data;

      }); // $http

    });// $timeout
  }

  this.changeStatus = function($scope, moduleContentVO) {
    $timeout(function() {
      $http({
        method : 'GET',
        url : window.Constants.server_url + '/module/content/changeStatus/' + moduleContentVO.id,
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        moduleContentVO.status = !moduleContentVO.status;
        moduleContentVO.updateTime = text.data.updateTime;

      }); // $http

    });// $timeout
  }

  this.del = function($scope, arr) {
    $timeout(function() {
      $http({
        method : 'POST',
        url : window.Constants.server_url + '/module/content/del',
        data : $.param({
          id : arr
        }, true),
        headers : {
          'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
        }
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        $scope.checkAllStatus = !$scope.checkAllStatus;
        $rootScope.$broadcast("doModuleContentQuery", $scope.queryParams);

      }); // $http

    });// $timeout
  }

  this.save = function($scope, moduleContentParams) {

    $timeout(function() {
      $http({
        method : 'POST',
        url : window.Constants.server_url + '/module/content/save',
        data : $.param(moduleContentParams, true),
        headers : {
          'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
        }
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        $rootScope.$broadcast("doModuleContentQuery", $scope.queryParams);

      }); // $http

    });// $timeout

  }

};
