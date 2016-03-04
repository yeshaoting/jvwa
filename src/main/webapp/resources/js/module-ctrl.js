/**
 * module controller
 */
app.controller("ModuleCtrl", ModuleCtrl);

function ModuleCtrl($state, $rootScope, $scope, $window, $http, $location, $log, $modal, ModuleService) {

  $scope.currentPage = 1;
  $scope.queryParams = {
    page : 1,
    size : 20,
  };

  $scope.query = function(queryParams) {
    ModuleService.query($scope, queryParams ? queryParams : $scope.queryParams);
  }

  $scope.showPushPage = function(module) {
    $state.go("module.content", module);
  }

  $scope.changeStatus = function(module) {
    var tip = module.status ? "屏蔽" : "启用";
    bootbox.confirm('你确定' + tip + '"' + module.name + '么?', function(result) {
      if (result == true) {
        ModuleService.changeStatus($scope, module);
      }
    });

  }
  
  $scope.del = function(module) {
    bootbox.confirm('你确定删除' + module.name + '么?', function(result) {
      if (result == true) {
        ModuleService.del($scope, module.id);
      }
    });

  }
  
  $scope.forceDel = function(module) {
    bootbox.confirm('你确定强制删除' + module.name + '及其关联内容么?', function(result) {
      if (result == true) {
        ModuleService.forceDel($scope, module.id);
      }
    });

  }

  $scope.openSaveDialog = function(module) {
    // dialog参数
    $scope.opts = {
      controller : ModuleSaveCtrl,
      size : 'default',
      backdrop : 'static',
      templateUrl : Constants.resources_path + '/tpls/module-save-dialog.html?version=' + Constants.resources_version,
      resolve : {
        moduleParams : function() {
          return $.extend(true, {}, module);
        }
      }
    };

    $modal.open($scope.opts);
  }

  $scope.pageChanged = function() {
    $scope.queryParams.page = $scope.currentPage;
    $scope.query();
  }

  $rootScope.$on('doModuleQuery', function(event, queryParams) {
    $scope.query(queryParams);
  });

  $scope.query();

}

function ModuleSaveCtrl($rootScope, $scope, $window, $http, $filter, $modalInstance, ModuleService, moduleParams) {

  $scope.moduleParams = moduleParams;
  $scope.moduleParams.nodeType = $scope.moduleParams.nodeType != undefined ? $scope.moduleParams.nodeType : 'mv';
  $scope.moduleParams.rank = $scope.moduleParams.rank != undefined ? $scope.moduleParams.rank : 100;
  $scope.moduleParams.status = $scope.moduleParams.status != undefined ? $scope.moduleParams.status : true;

  $scope.save = function() {
    ModuleService.save($scope, $scope.moduleParams);
    $scope.close();
  }

  $scope.cancel = function() {
    $scope.close();
  }

  $scope.close = function() {
    $modalInstance.close();
  }
}
