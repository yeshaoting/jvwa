/**
 * changelog controller
 */
app.controller("ChangelogCtrl", ChangelogCtrl);

function ChangelogCtrl($state, $rootScope, $scope, $window, $http, $location, $log, $modal, ChangelogService) {

  $scope.currentPage = 1;
  $scope.queryParams = {
    page : 1,
    size : 20,
  };

  $scope.query = function(queryParams) {
    ChangelogService.query($scope, queryParams ? queryParams : $scope.queryParams);
  }

  $scope.changeStatus = function(changelog) {
    var tip = changelog.status ? "屏蔽" : "启用";
    bootbox.confirm('你确定' + tip + '"' + changelog.title + '么?', function(result) {
      if (result == true) {
        ChangelogService.changeStatus($scope, changelog);
      }
    });

  }
  
  $scope.del = function(changelog) {
    bootbox.confirm('你确定删除' + changelog.title + '么?', function(result) {
      if (result == true) {
        ChangelogService.del($scope, changelog.id);
      }
    });

  }

  $scope.openSaveDialog = function(changelog) {
    // dialog参数
    $scope.opts = {
      controller : ChangelogSaveCtrl,
      size : 'default',
      backdrop : 'static',
      templateUrl : Constants.resources_path + '/tpls/changelog-save-dialog.html?version=' + Constants.resources_version,
      resolve : {
        changelogParams : function() {
          return $.extend(true, {}, changelog);
        }
      }
    };

    $modal.open($scope.opts);
  }

  $scope.pageChanged = function() {
    $scope.queryParams.page = $scope.currentPage;
    $scope.query();
  }

  $rootScope.$on('doChangelogQuery', function(event, queryParams) {
    $scope.query(queryParams);
  });

  $scope.query();

}

function ChangelogSaveCtrl($rootScope, $scope, $window, $http, $filter, $modalInstance, ChangelogService, changelogParams) {

  $scope.changelogParams = changelogParams;
  $scope.changelogParams.nodeType = $scope.changelogParams.nodeType != undefined ? $scope.changelogParams.nodeType : 'mv';
  $scope.changelogParams.force = $scope.changelogParams.force != undefined ? $scope.changelogParams.force : false;
  
  // 防止管理人员操作失误，新增与修改状态都为禁止
  $scope.changelogParams.status = false;

  $scope.save = function() {
    ChangelogService.save($scope, $scope.changelogParams);
    $scope.close();
  }

  $scope.cancel = function() {
    $scope.close();
  }

  $scope.close = function() {
    $modalInstance.close();
  }
}
