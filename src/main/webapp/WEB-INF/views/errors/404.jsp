<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>JVWA-404页面</title>
<link rel="stylesheet" type="text/css" href="${styles_path}/error.css?version=${resources_version}">
</head>
<body>
  <div class="wrong">
    <div class="wrong_title">
      <span>404 ！</span>
    </div>
    <div class="wrong_p">
      <p>糟糕，你访问的页面去了别的星球</p>
      <p>
        你可以<a href="javascript:history.go(-1);">点击返回</a>，或者直接<a href="${index_url}">去首页</a>
      </p>
    </div>
  </div>
</body>
</html>