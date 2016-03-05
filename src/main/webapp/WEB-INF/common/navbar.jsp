<%@ page language="java" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- navbar starts -->
<div class="navbar navbar-fixed-top bs-docs-nav" role="banner">

  <div class="conjtainer">
    <!-- Menu button for smallar screens -->
    <div class="navbar-header">
      <button class="navbar-toggle btn-navbar" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
        <span>菜单</span>
      </button>
      <!-- Site name for smallar screens -->
      <a href="index.html" class="navbar-brand hidden-lg">首页</a>
    </div>



    <!-- Navigation starts -->
    <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">

      <!-- Links -->
      <ul class="nav navbar-nav pull-right">
        <li class="dropdown pull-right">
          <a data-toggle="collapse" class="dropdown-toggle" href="#" aria-expanded="true"> <i class="icon-user"></i> ${user.name } <b
            class="caret"></b>
          </a>
          <!-- Dropdown menu -->
          <ul class="dropdown-menu">
            <li>
              <a href="${server_url}/user/logout" target="_self"><i class="icon-off"></i> 退出</a>
            </li>
          </ul>
        </li>

      </ul>
    </nav>

  </div>
</div>
<!-- navbar ends -->