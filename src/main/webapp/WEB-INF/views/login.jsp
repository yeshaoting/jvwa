<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="zh_CN">
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
  };
</script>

<!-- bootstrap -->
<link type="text/css" rel="stylesheet" href="${scripts_path}/bootstrap/css/bootstrap.css?version=${resources_version}" />
<link type="text/css" rel="stylesheet" href="${scripts_path}/bootstrap/css/bootstrap-theme.css?version=${resources_version}" />
<!-- Font awesome icon -->
<link type="text/css" rel="stylesheet" href="${styles_path}/font-awesome/css/font-awesome.css?version=${resources_version}" />
<!--[if IE 7]>
  <link type="text/css" rel="stylesheet" href="${static_path}/font-awesome/css/font-awesome-ie7.css?version=${resources_version}" />
  <![endif]-->

<!-- HTML5 Support for IE -->
<!--[if lt IE 9]>
  <script src="${scripts_path}/html5shim.js?version=${resources_version}"></script>
  <![endif]-->

<!-- Main stylesheet -->
<link rel="stylesheet" href="${styles_path}/style.css?version=${resources_version}">
<link rel="stylesheet" href="${styles_path}/main.css?version=${resources_version}">

<!-- Favicon -->
<link rel="shortcut icon" href="${images_path}/favicon.ico?version=${resources_version}">
</head>

<body>

  <!-- Form area -->
  <div id="login-form" class="admin-form">
    <div class="container">

      <div class="row">
        <div class="col-md-12">

          <div class="show">
            <c:if test="${not empty errorMsg}">
              <i class="icon-info-sign icon-large"></i>
              <label class="label label-danger">${errorMsg}</label>
            </c:if>
            <c:if test="${empty errorMsg}">
              <label class="label">&nbsp;</label>
            </c:if>
          </div>

          <!-- Login form -->
          <form class="form-horizontal" method="POST" action="${server_url}/user/login">
            <!-- Widget starts -->
            <div class="widget worange">
              <!-- Widget head -->
              <div class="widget-head">
                <i class="icon-lock"></i> 用户登记
              </div>

              <div class="widget-content">
                <div class="padd">

                  <!-- Username -->
                  <div class="form-group">
                    <label class="control-label col-lg-3" for="username">用户名：</label>
                    <div class="col-lg-8">
                      <input type="text" class="form-control" id="username" name="username" placeholder="用户名" value="${not empty user ? user.username : '' }">
                    </div>
                  </div>

                </div>
              </div>

              <div class="widget-foot">
                <!-- action -->
                <div class="pull-right">
                  <button id="login" class="btn btn-primary">登记</button>
                  <button type="reset" class="btn btn-default">重置</button>
                </div>
                <div class="clearfix"></div>
              </div>

            </div>

          </form>

        </div>
      </div>
    </div>
  </div>

  <!-- jquery -->
  <script type="text/javascript" src="${scripts_path}/jquery/jquery.js?version=${resources_version}"></script>
  <!-- bootstrap -->
  <script type="text/javascript" src="${scripts_path}/bootstrap/js/bootstrap.js?version=${resources_version}"></script>
  <!-- noty -->
  <script type="text/javascript" src="${scripts_path}/noty/packaged/jquery.noty.packaged.js?version=${resources_version}"></script>
  <!-- custom utils -->
  <script type="text/javascript" src="${scripts_path}/notify.js?version=${resources_version}"></script>
  <!-- login -->
  <script type="text/javascript" src="${scripts_path}/login.js?version=${resources_version}"></script>
</body>
</html>