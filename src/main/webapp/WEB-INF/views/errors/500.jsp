<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>FUN学堂-页面错误</title>
<link rel="stylesheet" type="text/css" href="${styles_path}/error.css?version=${resources_version}">
</head>
<body>
  <div class="wrong">
    <div class="wrong_title">
      <span>500 ！</span>
    </div>
    <div class="wrong_p">
      <p>额，服务器刚刚开了小差</p>
      <p>
        快<a href="javascript:location.reload();">刷新</a>试试，也可以<a href="javascript:history.go(-1);">点击返回</a>，或者直接<a href="${index_url}">去首页</a>
      </p>
    </div>
  </div>

  <div class="hidden">
    <h3>
      错误码：
      <%=request.getAttribute("javax.servlet.error.status_code")%>
    </h3>
    <h3>异常类型：</h3><%=request.getAttribute("javax.servlet.error.exception_type")%>
    <br>
    <h3>异常信息：</h3><%=request.getAttribute("javax.servlet.error.message")%>
    <br>
    <h3>堆栈信息：</h3>
    <%
      if (exception != null) {
        exception.printStackTrace(new java.io.PrintWriter(out));
      }
    %>
  </div>

</body>
</html>