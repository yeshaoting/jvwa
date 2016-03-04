'use strict';

/**
 * Config for the router
 */
app.run([ '$rootScope', '$state', '$stateParams', function($rootScope, $state, $stateParams) {
  $rootScope.$state = $state;
  $rootScope.$stateParams = $stateParams;

  // 状态
  $rootScope.statuses = [ {
    "name" : '启用',
    "value" : true
  }, {
    "name" : '屏蔽',
    "value" : false
  } ];
  
  // 是否强制更新
  $rootScope.forces = [ {
    "name" : '强制更新',
    "value" : true
  }, {
    "name" : '正常更新',
    "value" : false
  } ];

  // 系统中涉及的类型
  $rootScope.moduleTypes = Constants.moduleTypes;
  $rootScope.templateTypes = Constants.templateTypes;
  $rootScope.nodeTypes = Constants.nodeTypes;
  $rootScope.linkTypes = Constants.linkTypes;
  
//  $rootScope.nodeTypeMap = Constants.nodeTypeMap;
//  $rootScope.linkTypeMap = Constants.linkTypeMap;
  
  $rootScope.fileUrlPrefix = Constants.fileUrlPrefix;
  $rootScope.fileUploadMaxSize = Constants.fileUploadMaxSize;
  
  $rootScope.isSuperAdmin = Constants.isSuperAdmin;

  // 监听状态改变
  $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
    // console.log("to state: " + toState.name);
    $rootScope.menu = $rootScope.menus[toState.name.replace(".", "_")];
    // console.log($rootScope.menu);
  });

  // 菜单列表
  $rootScope.menus = {
    dashboard : {
      state : "dashboard",
      name : "首页",
      link : "#/dashboard",
      icon : "icon-dashboard"
    },
    module_list : {
      state : "module.list",
      name : "模块管理",
      link : "#/module/list",
      icon : "icon-folder-close"
    },
    module_save : {
      state : "module.save",
      name : "新增模块",
      link : "#/module/save"
    },
    module_content : {
      state : "module.list",
      name : "模块内容管理",
      link : "#/module/list",
      icon : "icon-reorder"
    },
    content : {
      state : "content",
      name : "内容管理",
      link : "#/content",
      icon : "icon-reorder"
    },
    file_list : {
      state : "file.list",
      name : "文件列表",
      link : "#/file/list",
      icon : "icon-file"
    },
    file_upload : {
      state : "file.upload",
      name : "文件上传",
      link : "#/file/upload",
      icon : "icon-file"
    },
    user : {
      state : "user",
      name : "用户管理",
      link : "#/user",
      icon : "icon-group"
    },
    changelog : {
      state : "changelog",
      name : "更新记录管理",
      link : "#/changelog",
      icon : "icon-user"
    }
  };

} ]);

app.config(function($stateProvider, $urlRouterProvider) {

  $urlRouterProvider.when("", "/dashboard");
  $urlRouterProvider.when("/", "/dashboard");
  $urlRouterProvider.when("/index", "/dashboard");
  // $urlRouterProvider.otherwise('/dashboard');

  $stateProvider.state('dashboard', {
    url : '/dashboard',
    template : '<div ui-view class="fade-in-up"></div>',
    controller : function($rootScope, $scope, $stateParams) {
      $rootScope.breadcrumbs = [];
    }
  });

  $stateProvider.state('module', {
    abstract : true,
    url : '/module',
    template : '<div ui-view class="fade-in-up"></div>'
  }).state('module.list', {
    url : '/list',
    templateUrl : Constants.resources_path + '/tpls/module.html?version=' + Constants.resources_version,
    controller : function($rootScope, $scope, $stateParams) {
      $rootScope.breadcrumbs = [];
      $rootScope.breadcrumbs.push($rootScope.menus.module_list);
    }
  }).state('module.save', {
    url : '/save',
    templateUrl : Constants.resources_path + '/tpls/module-save-dialog.html?version=' + Constants.resources_version,
    controller : function($rootScope, $scope, $stateParams) {
      $rootScope.breadcrumbs = [];
      $rootScope.breadcrumbs.push($rootScope.menus.module_list);
      $rootScope.breadcrumbs.push($rootScope.menus.module_save);
    }
  }).state('module.content', {
    url : '/content/{id:int}',
    templateUrl : Constants.resources_path + '/tpls/module-content.html?version=' + Constants.resources_version,
    controller : function($rootScope, $scope, $stateParams) {
      $rootScope.breadcrumbs = [];
      $rootScope.breadcrumbs.push($rootScope.menus.module_list);
      $rootScope.breadcrumbs.push({
        state : "module.content",
        name : "模块内容管理",
        link : "#/module/content/" + $stateParams.id
      });
    }
  })

  $stateProvider.state('content', {
    url : '/content',
    templateUrl : Constants.resources_path + '/tpls/content.html?version=' + Constants.resources_version,
    controller : function($rootScope, $scope, $stateParams) {
      $rootScope.breadcrumbs = [];
      $rootScope.breadcrumbs.push($rootScope.menus.content);
    }
  });

  $stateProvider.state('file', {
    abstract : true,
    url : '/file',
    template : '<div ui-view class="fade-in-up"></div>'
  }).state('file.list', {
    url : '/list',
    templateUrl : Constants.resources_path + '/tpls/file-list.html?version=' + Constants.resources_version,
    controller : function($rootScope, $scope, $stateParams) {
      $rootScope.breadcrumbs = [];
      $rootScope.breadcrumbs.push($rootScope.menus.content);
    }
  }).state('file.upload', {
    url : '/upload',
    templateUrl : Constants.resources_path + '/tpls/file-upload.html?version=' + Constants.resources_version,
    controller : function($rootScope, $scope, $stateParams) {
      $rootScope.breadcrumbs = [];
      $rootScope.breadcrumbs.push($rootScope.menus.content);
    }
  });

  $stateProvider.state('user', {
    url : '/user',
    templateUrl : Constants.resources_path + '/tpls/user.html?version=' + Constants.resources_version,
    controller : function($rootScope, $scope, $stateParams) {
      $rootScope.breadcrumbs = [];
      $rootScope.breadcrumbs.push($rootScope.menus.user);
    }
  });
  
  $stateProvider.state('changelog', {
    url : '/changelog',
    templateUrl : Constants.resources_path + '/tpls/changelog.html?version=' + Constants.resources_version,
    controller : function($rootScope, $scope, $stateParams) {
      $rootScope.breadcrumbs = [];
      $rootScope.breadcrumbs.push($rootScope.menus.changelog);
    }
  });

});