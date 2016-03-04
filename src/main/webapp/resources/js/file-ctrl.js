/**
 * file controller
 */
app.controller("FileCtrl", FileCtrl);

function FileCtrl($state, $rootScope, $scope, $window, $http, $location, $log, $modal, FileService) {

  $scope.currentPage = 1;
  $scope.queryParams = {
    page : 1,
    size : 20,
  };

  $scope.query = function(queryParams) {
    FileService.query($scope, queryParams ? queryParams : $scope.queryParams);
  }

  $scope.pageChanged = function() {
    $scope.queryParams.page = $scope.currentPage;
    $scope.query();
  }

  $rootScope.$on('doFileQuery', function(event, queryParams) {
    $scope.query(queryParams);
  });

  $scope.query();

}

