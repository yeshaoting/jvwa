<%@ page language="java" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- navbar starts -->
<div class="navbar navbar-fixed-top bs-docs-nav" role="banner">

  <div class="conjtainer">
    <!-- Navigation starts -->
    <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
      <ul class="nav navbar-nav pull-left">
        <li class="pull-left"><a href="#/dashboard"><span class="label label-success"><i
              class="icon-home"></i></span> 主页</a></li>
      </ul>

      <!-- Links -->
      <ul class="nav navbar-nav pull-right">
        <li class="dropdown pull-right">
          <a target="_blank" href="#"><i class="icon-github-alt"></i> Lark咨询：叶绍亭(yeshaoting)</a>
        </li>
        <li class="dropdown pull-right"><a data-toggle="collapse" class="dropdown-toggle" href="#"
          aria-expanded="true"> <i class="icon-user"></i> ${username } <b class="caret"></b>
        </a> <!-- Dropdown menu -->
          <ul class="dropdown-menu">
            <li><a href="${server_url}/user/logout" target="_self"><i class="icon-off"></i> 退出</a></li>
          </ul></li>
      </ul>
    </nav>

  </div>
</div>
<!-- navbar ends -->