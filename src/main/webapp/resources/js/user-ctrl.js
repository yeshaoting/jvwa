/**
 * user controller
 */
app.controller("UserCtrl", UserCtrl);

function UserCtrl($state, $rootScope, $scope, $window, $http, $location, $log, $modal, UserService) {

  $scope.currentPage = 1;
  $scope.queryParams = {
    page : 1,
    size : 20,
  };

  $scope.query = function(queryParams) {
    UserService.query($scope, queryParams ? queryParams : $scope.queryParams);
  }

  $scope.changeStatus = function(user) {
    var tip = user.status ? "屏蔽" : "启用";
    bootbox.confirm('你确定' + tip + '"' + user.name + '么?', function(result) {
      if (result == true) {
        UserService.changeStatus($scope, user);
      }
    });

  }

  $scope.openSaveDialog = function(user) {
    // dialog参数
    $scope.opts = {
      controller : UserSaveCtrl,
      size : 'default',
      backdrop : 'static',
      templateUrl : Constants.resources_path + '/tpls/user-save-dialog.html?version=' + Constants.resources_version,
      resolve : {
        userParams : function() {
          return $.extend(true, {}, user);
        }
      }
    };

    $modal.open($scope.opts);
  }

  $scope.pageChanged = function() {
    $scope.queryParams.page = $scope.currentPage;
    $scope.query();
  }

  $rootScope.$on('doUserQuery', function(event, queryParams) {
    $scope.query(queryParams);
  });

  $scope.query();

}

function UserSaveCtrl($rootScope, $scope, $window, $http, $filter, $modalInstance, UserService, userParams) {

  $scope.userParams = userParams;
  $scope.userParams.status = $scope.userParams.status != undefined ? $scope.userParams.status : true;

  $scope.save = function() {
    UserService.save($scope, $scope.userParams);
    $scope.close();
  }

  $scope.cancel = function() {
    $scope.close();
  }

  $scope.close = function() {
    $modalInstance.close();
  }
}
