<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="zh_CN" ng-app="app">
<head>
<title>JVWA</title>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="JVWA" />
<meta name="author" content="叶绍亭(yeshaoting)" />
<meta name="description" content="Java Vulnerable Web Application" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script type="text/javascript">
  var Constants = {
    base_url : "${base_url}",
    server_url : "${server_url}",
    index_url : "${index_url}",
    resources_path : "${resources_path}",
    resources_version : "${resources_version}",
    max_stage: ${max_stage},
    is_open_stage: ${is_open_stage},
  };
</script>

<!-- just for file upload template -->
<link rel="stylesheet" href="${resources_path}/css/app.css?version=${resources_version}">

<!-- bootstrap -->
<link type="text/css" rel="stylesheet" href="${resources_path}/js/bootstrap/css/bootstrap.css?version=${resources_version}" />
<link type="text/css" rel="stylesheet" href="${resources_path}/js/bootstrap/css/bootstrap-theme.css?version=${resources_version}" />
<!-- Font awesome icon -->
<link type="text/css" rel="stylesheet" href="${resources_path}/css/font-awesome/css/font-awesome.css?version=${resources_version}" />
<!--[if IE 7]>
  <link type="text/css" rel="stylesheet" href="${static_path}/font-awesome/css/font-awesome-ie7.css?version=${resources_version}" />
  <![endif]-->

<!-- jQuery UI -->
<link rel="stylesheet" href="${resources_path}/js/angular-ui-select/select.css?version=${resources_version}">

<!-- custom style -->
<link rel="stylesheet" href="${resources_path}/css/main.css?version=${resources_version}">
<!-- Main stylesheet -->
<link rel="stylesheet" href="${resources_path}/css/style.css?version=${resources_version}">
<!-- Widgets stylesheet -->
<link rel="stylesheet" href="${resources_path}/css/widgets.css?version=${resources_version}">

<!-- HTML5 Support for IE -->
<!--[if lt IE 9]>
  <script src="${resources_path}/js/html5shim.js?version=${resources_version}"></script>
  <![endif]-->

<!-- jquery -->
<script type="text/javascript" src="${resources_path}/js/jquery/jquery.js?version=${resources_version}"></script>

<!-- angularjs -->
<script type="text/javascript" src="${resources_path}/js/angular/angular.js?version=${resources_version}"></script>
<script type="text/javascript" src="${resources_path}/js/angular/angular-sanitize.js?version=${resources_version}"></script>
<script type="text/javascript" src="${resources_path}/js/angular/angular-route.js?version=${resources_version}"></script>
<script type="text/javascript" src="${resources_path}/js/angular-ui-bootstrap/ui-bootstrap-tpls-0.11.0.js?version=${resources_version}"></script>
<script type="text/javascript" src="${resources_path}/js/angular-ui-utils/ui-utils.js?version=${resources_version}"></script>
<script type="text/javascript" src="${resources_path}/js/angular-ui-select/select.js?version=${resources_version}"></script>
<!--[if lte IE 8]>
<script>
window.myCustomTags = [ 'yourCustomDirective', 'somebodyElsesDirective' ]; // optional
</script>
<script type="text/javascript" src="${resources_path}/js/angular-ui-utils/ui-utils-ieshiv.js?version=${resources_version}"></script>
<![endif]-->
<script type="text/javascript" src="${resources_path}/js/angular-ui-router/angular-ui-router.js?version=${resources_version}"></script>
<script type="text/javascript" src="${resources_path}/js/angular-file-upload/angular-file-upload.js?version=${resources_version}"></script>

<!-- bootstrap -->
<script type="text/javascript" src="${resources_path}/js/bootstrap/js/bootstrap.js?version=${resources_version}"></script>

<!-- noty -->
<link href="${resources_path}/js/noty/noty.css?version=${resources_version}" rel="stylesheet">
<script src="${resources_path}/js/noty/noty.js?version=${resources_version}"></script>

<!-- bootbox dialog -->
<script type="text/javascript" src="${resources_path}/js/bootbox/bootbox.js?version=${resources_version}"></script>

<!-- custom -->
<script type="text/javascript" src="${resources_path}/js/app.js?version=${resources_version}"></script>
<script type="text/javascript" src="${resources_path}/js/config.router.js?version=${resources_version}"></script>
<script type="text/javascript" src="${resources_path}/js/main-ctrl.js?version=${resources_version}"></script>
<script type="text/javascript" src="${resources_path}/js/stage-ctrl.js?version=${resources_version}"></script>

<!-- custom utils -->
<script type="text/javascript" src="${resources_path}/js/notify.js?version=${resources_version}"></script>
<script type="text/javascript" src="${resources_path}/js/custom.js?version=${resources_version}"></script>

<!-- Favicon -->
<link rel="shortcut icon" href="${resources_path}/img/favicon.ico?version=${resources_version}">
</head>

<body>

  <%@ include file="/WEB-INF/common/navbar.jsp"%>

  <!-- Main content starts -->
  <div class="content" ng-controller="MainCtrl">

    <!-- Matter -->
    <div class="matter">
      <div class="container">
        <div class="row">
          <label class="label label-danger" ng-if="is_open_stage"><i class="icon-info-sign"></i> 公告：当前已经开放所有关卡，不再限制权限，快快来体验后面每一关吧。</label>
          <div ui-view></div>
        </div>
      </div>
    </div>
    <!-- Matter ends -->

    <div class="clearfix"></div>

  </div>
  <!-- Content ends -->

  <%-- <%@ include file="/WEB-INF/common/footer.jsp"%> --%>

</body>
</html>