/**
 * module content controller
 */
app.controller("ModuleContentCtrl", ModuleContentCtrl);

function ModuleContentCtrl($state, $stateParams, $rootScope, $scope, $window, $http, $location, $log, $modal,
    ModuleService, ModuleContentService) {

  $scope.moduleId = $stateParams.id;
  $scope.checkAllStatus = false;

  $scope.currentPage = 1;
  $scope.queryParams = {
    page : 1,
    size : 20,
  };

  $scope.getModule = function() {
    ModuleService.queryById($scope, $scope.moduleId);
  }

  $scope.query = function(queryParams) {
    ModuleContentService.query($scope, $scope.moduleId, queryParams ? queryParams : $scope.queryParams);
  }

  $scope.changeStatus = function(moduleContentVO) {
    var tip = moduleContentVO.status ? "屏蔽" : "启用";
    bootbox.confirm('你确定' + tip + '"' + moduleContentVO.name + '么?', function(result) {
      if (result == true) {
        ModuleContentService.changeStatus($scope, moduleContentVO);
      }
    });

  }

  $scope.del = function(moduleContentVO) {
    bootbox.confirm('你确定删除' + moduleContentVO.name + '么?', function(result) {
      if (result == true) {
        var arr = [ moduleContentVO.id ];
        ModuleContentService.del($scope, arr);
      }
    });

  }

  $scope.delAllChecked = function() {
    var checkedboxes = $("td.check input:checked");
    if (checkedboxes.size() == 0) {
      window.Notify.error("请先勾选要删除的模块内容！");
      return;
    }

    bootbox.confirm('你确定删除所有勾选的模块内容么?', function(result) {
      if (result == true) {
        var arr = [];
        checkedboxes.each(function(k, v) {
          arr.push($(this).attr("data-id"));
        });

        ModuleContentService.del($scope, arr);
      }
    });

  }

  $scope.checkAll = function() {
    var checkboxes = $("td.check input");
    checkboxes.prop("checked", !$scope.checkAllStatus);
    $scope.checkAllStatus = !$scope.checkAllStatus;
  }

  // dialog参数
  $scope.opts = {
    controller : ModuleContentSaveCtrl,
    size : 'default',
    backdrop : 'static',
    templateUrl : Constants.resources_path + '/tpls/module-content-save-dialog.html?version='
        + Constants.resources_version,
    resolve : {}
  };

  $scope.openSaveDialog = function(moduleContentVO) {
    $scope.opts.resolve.moduleContentParams = function() {
      moduleContentVO = moduleContentVO || {};
      moduleContentVO.moduleId = $scope.moduleId;
      return $.extend(true, {}, moduleContentVO);
    }

    $scope.opts.resolve.selectContents = function() {
      return {};
    }

    if (!moduleContentVO) { // add
      ModuleContentService.selectByModuleId($scope, $scope.moduleId);
    } else { // edit
      $modal.open($scope.opts);
    }
  }

  $scope.$watch('selectContents', function(newValue, oldValue) {
    // first run
    if (newValue == oldValue) {
      return;
    }

    if ($scope.selectContents.length == 0) {
      window.Notify.warn("暂无可添加到当前模块的内容，请先添加对应的内容。");
      return;
    }

    $scope.opts.resolve.selectContents = function() {
      return $scope.selectContents;
    }

    $modal.open($scope.opts); // add dialog open handle

  });

  $scope.pageChanged = function() {
    $scope.queryParams.page = $scope.currentPage;
    $scope.query();
  }

  $rootScope.$on('doModuleContentQuery', function(event, queryParams) {
    $scope.query(queryParams);
  });

  $scope.getModule();
  $scope.query();

}

function ModuleContentSaveCtrl($rootScope, $scope, $window, $http, $filter, $modalInstance, ModuleContentService,
    moduleContentParams, selectContents) {

  $scope.moduleContentParams = moduleContentParams;
  $scope.moduleContentParams.rank = $scope.moduleContentParams.rank != undefined ? $scope.moduleContentParams.rank
      : 100;
  $scope.moduleContentParams.status = $scope.moduleContentParams.status != undefined ? $scope.moduleContentParams.status
      : true;

  $scope.moduleContentParams.contentIds = [];
  $scope.selectContents = selectContents;

  $scope.save = function() {
    if ($scope.moduleContentParams.contentId != undefined) { // edit时用到
      $scope.moduleContentParams.contentIds.push($scope.moduleContentParams.contentId);
    }

    var moduleContents = [];
    if ($scope.moduleContentParams.contentIds.length == 0) {
      window.Notify.error("请至少选择一个模块内容。");
      return;
    }

    // return;
    ModuleContentService.save($scope, $scope.moduleContentParams);
    $scope.close();
  }

  $scope.cancel = function() {
    $scope.close();
  }

  $scope.close = function() {
    $modalInstance.close();
  }
}
