'use strict';

/**
 * Config for the router
 */
app.run([ '$rootScope', '$state', '$stateParams', function($rootScope, $state, $stateParams) {
  $rootScope.$state = $state;
  $rootScope.$stateParams = $stateParams;

  // 监听状态改变
  $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
	 //$state.reload(toState.name);
	 $rootScope.$broadcast('checkUserInfo');
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
    templateUrl : Constants.server_url + '/security/stage/0?version=' + Constants.resources_version,
  });

  $stateProvider.state('stage0', {
    url : '/stage0',
    templateUrl : Constants.server_url + '/security/stage/0?version=' + Constants.resources_version,
  }).state('stage1', {
    url : '/stage1',
    templateUrl : Constants.server_url + '/security/stage/1?version=' + Constants.resources_version,
  }).state('stage2', {
    url : '/stage2',
    templateUrl : Constants.server_url + '/security/stage/2?version=' + Constants.resources_version,
  }).state('stage3', {
    url : '/stage3',
    templateUrl : Constants.server_url + '/security/stage/3?version=' + Constants.resources_version,
  }).state('stage4', {
    url : '/stage4',
    templateUrl : Constants.server_url + '/security/stage/4?version=' + Constants.resources_version,
  }).state('stage5', {
    url : '/stage5',
    templateUrl : Constants.server_url + '/security/stage/5?version=' + Constants.resources_version,
  }).state('stage6', {
    url : '/stage6',
    templateUrl : Constants.server_url + '/security/stage/6?version=' + Constants.resources_version,
  }).state('stage7', {
    url : '/stage7',
    templateUrl : Constants.server_url + '/security/stage/7?version=' + Constants.resources_version,
  }).state('stage8', {
    url : '/stage8',
    templateUrl : Constants.server_url + '/security/stage/8?version=' + Constants.resources_version,
  }).state('stage9', {
    url : '/stage9',
    templateUrl : Constants.server_url + '/security/stage/9?version=' + Constants.resources_version,
  }).state('stage10', {
    url : '/stage10',
    templateUrl : Constants.server_url + '/security/stage/10?version=' + Constants.resources_version,
  }).state('admin', {
    url : '/admin',
    templateUrl : Constants.server_url + '/security/admin?version=' + Constants.resources_version,
  })

});