/**
 * content controller
 */
app.controller("ContentCtrl", ContentCtrl);

function ContentCtrl($state, $rootScope, $scope, $window, $http, $location, $log, $modal, ContentService,
    ModuleContentService) {

  $scope.currentPage = 1;
  $scope.queryParams = {
    page : 1,
    size : 20,
  };

  $scope.query = function(queryParams) {
    ContentService.query($scope, queryParams ? queryParams : $scope.queryParams);
  }

  $scope.changeStatus = function(content) {
    var tip = content.status ? "屏蔽" : "启用";
    bootbox.confirm('你确定' + tip + '"' + content.name + '么?', function(result) {
      if (result == true) {
        ContentService.changeStatus($scope, content);
      }
    });

  }

  $scope.forceDel = function(content) {
    bootbox.confirm('你确定强制删除' + content.name + '及其关联模块么?', function(result) {
      if (result == true) {
        ContentService.forceDel($scope, content.id);
      }
    });

  }

  // dialog参数
  $scope.opts = {
    controller : ContentSaveCtrl,
    size : 'default',
    backdrop : 'static',
    templateUrl : Constants.resources_path + '/tpls/content-save-dialog.html?version=' + Constants.resources_version,
    resolve : {}
  };

  $scope.openSaveDialog = function(content) {
    $scope.opts.resolve.contentParams = function() {
      content = content || {};
      return $.extend(true, {}, content);
    }

    /*
     * $scope.opts.resolve.selectContents = function() { return {}; }
     * 
     * if (content && content.linkType) {
     * ModuleContentService.selectByLinkType($scope, content.linkType); }
     * 
     * if (!content) { // add ModuleContentService.selectByLinkType($scope,
     * content.linkType); } else { // edit $modal.open($scope.opts); }
     */

    $modal.open($scope.opts);
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

  $rootScope.$on('doContentQuery', function(event, queryParams) {
    $scope.query();
  });

  $scope.query();

}

function ContentSaveCtrl($rootScope, $scope, $window, $http, $filter, $modalInstance, ContentService, contentParams) {

  $scope.contentParams = contentParams;
  $scope.contentParams.nodeType = $scope.contentParams.nodeType != undefined ? $scope.contentParams.nodeType : 'mv';
  $scope.contentParams.rank = $scope.contentParams.rank != undefined ? $scope.contentParams.rank : 100;
  $scope.contentParams.status = $scope.contentParams.status != undefined ? $scope.contentParams.status : true;

  $scope.save = function() {
    ContentService.save($scope, $scope.contentParams);
    $scope.close();
  }

  $scope.cancel = function() {
    $scope.close();
  }

  $scope.close = function() {
    $modalInstance.close();
  }
}
