/**
 * module service
 */
app.service("ModuleService", [ '$rootScope', '$http', '$timeout', ModuleService ]);

function ModuleService($rootScope, $http, $timeout) {

  this.queryById = function($scope, moduleId) {

    $timeout(function() {
      $http({
        method : 'GET',
        url : window.Constants.server_url + '/module/query',
        params : {
          page : 1,
          size : 1,
          id : moduleId
        }
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        if (text.data.contents.length >= 1) {
          $scope.module = text.data.contents[0];
        } else {
          window.Notify.error("模块 " + id + " 不存在。");
        }
        
      }); // $http

    });// $timeout

  };

  this.query = function($scope, queryParams) {

    $timeout(function() {
      $http({
        method : 'GET',
        url : window.Constants.server_url + '/module/query',
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
  
  this.changeStatus = function($scope, module) {

    $timeout(function() {
      $http({
        method : 'GET',
        url : window.Constants.server_url + '/module/changeStatus/' + module.id,
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        module.status = !module.status;
        module.updateTime = text.data.updateTime;
        
      }); // $http

    });// $timeout

  }

  this.save = function($scope, moduleParams) {

    $timeout(function() {
      $http({
        method : 'POST',
        url : window.Constants.server_url + '/module/save',
        data : $.param(moduleParams, true),
        headers : {
          'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
        }
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        $rootScope.$broadcast("doModuleQuery", $scope.queryParams);

      }); // $http

    });// $timeout

  }
  
  this.del = function($scope, id) {
    $timeout(function() {
      $http({
        method : 'DELETE',
        url : window.Constants.server_url + '/module/del/' + id
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        $rootScope.$broadcast("doModuleQuery", $scope.queryParams);

      }); // $http

    });// $timeout
  }
  
  this.forceDel = function($scope, id) {
    $timeout(function() {
      $http({
        method : 'DELETE',
        url : window.Constants.server_url + '/module/forceDel/' + id
      }).success(function(text, status, headers, config) {
        if (text.status != 200) {
          window.Notify.error(text.statusText);
          return;
        }

        $rootScope.$broadcast("doModuleQuery", $scope.queryParams);

      }); // $http

    });// $timeout
  }

};
