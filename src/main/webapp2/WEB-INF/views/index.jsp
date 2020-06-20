﻿﻿<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="zh_CN" ng-app="app">
<head>
<title>JVWA</title>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="JVWA" />
<meta name="description" content="Java Vulnerable Web Application" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script type="text/javascript">
  var Constants = {
    base_url : "${base_url}",
    server_url : "${server_url}",
    index_url : "${index_url}",
    resources_path : "${resources_path}",
    resources_version : "${resources_version}",
    fileUrlPrefix : "${fileUrlPrefix}",
    fileUploadMaxSize : "${fileUploadMaxSize}",
    isSuperAdmin : "${isSuperAdmin}",
  };

  Constants.moduleTypes = ${moduleTypes};
  Constants.templateTypes = ${templateTypes};
  Constants.linkTypes = ${linkTypes};
  Constants.nodeTypes = ${nodeTypes};

  Constants.moduleTypeMap = ${moduleTypeMap};
  Constants.templateTypeMap = ${templateTypeMap};
  Constants.linkTypeMap = ${linkTypeMap};
  Constants.nodeTypeMap = ${nodeTypeMap};
</script>

<!-- just for file upload template -->
<link rel="stylesheet" href="${styles_path}/app.css?version=${resources_version}">

<!-- bootstrap -->
<link type="text/css" rel="stylesheet" href="${scripts_path}/bootstrap/css/bootstrap.css?version=${resources_version}" />
<link type="text/css" rel="stylesheet" href="${scripts_path}/bootstrap/css/bootstrap-theme.css?version=${resources_version}" />
<!-- Font awesome icon -->
<link type="text/css" rel="stylesheet" href="${styles_path}/font-awesome/css/font-awesome.css?version=${resources_version}" />
<!--[if IE 7]>
  <link type="text/css" rel="stylesheet" href="${static_path}/font-awesome/css/font-awesome-ie7.css?version=${resources_version}" />
  <![endif]-->

<!-- jQuery UI -->
<link rel="stylesheet" href="${scripts_path}/angular-ui-select/select.css?version=${resources_version}">

<!-- custom style -->
<link rel="stylesheet" href="${styles_path}/main.css?version=${resources_version}">
<!-- Main stylesheet -->
<link rel="stylesheet" href="${styles_path}/style.css?version=${resources_version}">
<!-- Widgets stylesheet -->
<link rel="stylesheet" href="${styles_path}/widgets.css?version=${resources_version}">

<!-- HTML5 Support for IE -->
<!--[if lt IE 9]>
  <script src="${scripts_path}/html5shim.js?version=${resources_version}"></script>
  <![endif]-->

<!-- jquery -->
<script type="text/javascript" src="${scripts_path}/jquery/jquery.js?version=${resources_version}"></script>

<!-- angularjs -->
<script type="text/javascript" src="${scripts_path}/angular/angular.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/angular/angular-sanitize.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/angular/angular-route.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/angular-ui-bootstrap/ui-bootstrap-tpls-0.11.0.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/angular-ui-utils/ui-utils.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/angular-ui-select/select.js?version=${resources_version}"></script>
<!--[if lte IE 8]>
<script>
window.myCustomTags = [ 'yourCustomDirective', 'somebodyElsesDirective' ]; // optional
</script>
<script type="text/javascript" src="${scripts_path}/angular-ui-utils/ui-utils-ieshiv.js?version=${resources_version}"></script>
<![endif]-->
<script type="text/javascript" src="${scripts_path}/angular-ui-router/angular-ui-router.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/angular-file-upload/angular-file-upload.js?version=${resources_version}"></script>

<!-- bootstrap -->
<script type="text/javascript" src="${scripts_path}/bootstrap/js/bootstrap.js?version=${resources_version}"></script>

<!-- noty -->
<script type="text/javascript" src="${scripts_path}/noty/packaged/jquery.noty.packaged.js?version=${resources_version}"></script>

<!-- bootbox dialog -->
<script type="text/javascript" src="${scripts_path}/bootbox/bootbox.js?version=${resources_version}"></script>

<!-- custom -->
<script type="text/javascript" src="${scripts_path}/app.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/config.router.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/main-ctrl.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/user-service.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/user-ctrl.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/module-service.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/module-ctrl.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/content-service.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/content-ctrl.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/module-content-service.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/module-content-ctrl.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/file-upload.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/file-service.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/file-ctrl.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/changelog-service.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/changelog-ctrl.js?version=${resources_version}"></script>

<!-- custom utils -->
<script type="text/javascript" src="${scripts_path}/notify.js?version=${resources_version}"></script>
<script type="text/javascript" src="${scripts_path}/custom.js?version=${resources_version}"></script>

<!-- Favicon -->
<link rel="shortcut icon" href="${images_path}/favicon.ico?version=${resources_version}">
</head>

<body ng-app="app">

  <%@ include file="/WEB-INF/common/navbar.jsp"%>

  <%@ include file="/WEB-INF/common/header.jsp"%>

  <!-- Main content starts -->
  <div class="content" ng-controller="MainCtrl">

    <%@ include file="/WEB-INF/common/sidebar.jsp"%>

    <!-- Main bar -->
    <div class="mainbar">

      <%@ include file="/WEB-INF/common/breadcrumbs.jsp"%>

      <!-- Matter -->
      <div class="matter">
        <div class="container" ui-view></div>
      </div>
      <!-- Matter ends -->

    </div>
    <!-- Mainbar ends -->

    <div class="clearfix"></div>

  </div>
  <!-- Content ends -->

  <%@ include file="/WEB-INF/common/footer.jsp"%>

  <!-- Scroll to top -->
  <span class="totop"><a href="#"><i class="icon-chevron-up"></i></a></span>

</body>
</html>