'use strict';

/**
 * Config for the router
 */
app.run([ '$rootScope', '$state', '$stateParams', function($rootScope, $state, $stateParams) {
  $rootScope.$state = $state;
  $rootScope.$stateParams = $stateParams;

  // 监听状态改变
  $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
     console.log("from state: " + fromState.name + ", to state: " + toState.name);
  });
}]);

app.config(function($stateProvider, $urlRouterProvider) {

  $urlRouterProvider.when("", "/dashboard");
  $urlRouterProvider.when("/", "/dashboard");
  $urlRouterProvider.when("/index", "/dashboard");
  //$urlRouterProvider.otherwise('/dashboard');

  $stateProvider.state('dashboard', {
    url : '/dashboard',
    template : '<div ui-view class="fade-in-up"></div>',
  });

  $stateProvider.state('stage1', {
    url : '/stage1',
    templateUrl : Constants.resources_path + '/tpls/sohu/stage1.html?version=' + Constants.resources_version,
  }).state('stage2', {
    url : '/stage2',
    templateUrl : Constants.resources_path + '/tpls/sohu/stage2.html?version=' + Constants.resources_version,
  }).state('stage3', {
    url : '/stage3',
    templateUrl : Constants.resources_path + '/tpls/sohu/stage3.html?version=' + Constants.resources_version,
  }).state('stage4', {
    url : '/stage4',
    templateUrl : Constants.resources_path + '/tpls/sohu/stage4.html?version=' + Constants.resources_version,
  }).state('stage5', {
    url : '/stage5',
    templateUrl : Constants.resources_path + '/tpls/sohu/stage5.html?version=' + Constants.resources_version,
  }).state('stage6', {
    url : '/stage6',
    templateUrl : Constants.resources_path + '/tpls/sohu/stage6.html?version=' + Constants.resources_version,
  }).state('stage7', {
    url : '/stage7',
    templateUrl : Constants.resources_path + '/tpls/sohu/stage7.html?version=' + Constants.resources_version,
  }).state('stage8', {
    url : '/stage8',
    templateUrl : Constants.resources_path + '/tpls/sohu/stage8.html?version=' + Constants.resources_version,
  }).state('stage9', {
    url : '/stage9',
    templateUrl : Constants.resources_path + '/tpls/sohu/stage9.html?version=' + Constants.resources_version,
  }).state('stage10', {
    url : '/stage10',
    templateUrl : Constants.resources_path + '/tpls/sohu/stage10.html?version=' + Constants.resources_version,
  })

});