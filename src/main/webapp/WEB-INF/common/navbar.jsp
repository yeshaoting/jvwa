<%@ page language="java" pageEncoding="UTF-8"%>

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

      <!-- <ul class="nav navbar-nav">

      Upload to server link. Class "dropdown-big" creates big dropdown
      <li class="dropdown dropdown-big">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="label label-success"><i class="icon-cloud-upload"></i></span>
          上传到云服务器</a>
        Dropdown
        <ul class="dropdown-menu">
          <li>
            Progress bar
            <p>图片上传进度</p>
            Bootstrap progress bar
            <div class="progress progress-striped active">
              <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
                style="width: 40%">
                <span class="sr-only">完成40%</span>
              </div>
            </div>

            <hr />
            Progress bar
            <p>视频上传进度</p>
            Bootstrap progress bar
            <div class="progress progress-striped active">
              <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
                style="width: 80%">
                <span class="sr-only">完成80%</span>
              </div>
            </div>

            <hr />
            Dropdown menu footer
            <div class="drop-foot">
              <a href="#">查看所有</a>
            </div>

          </li>
        </ul>
      </li>

      Sync to server link
      <li class="dropdown dropdown-big">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="label label-danger"><i class="icon-refresh"></i></span> 同步到服务器</a>
        Dropdown
        <ul class="dropdown-menu">
          <li>
            Using "icon-spin" class to rotate icon.
            <p>
              <span class="label label-info"><i class="icon-cloud"></i></span>同步会员到服务器
            </p>
            <hr />
            <p>
              <span class="label label-warning"><i class="icon-cloud"></i></span>同步书签到云端
            </p>

            <hr />
            Dropdown menu footer
            <div class="drop-foot">
              <a href="#">查看所有</a>
            </div>

          </li>
        </ul>
      </li>

    </ul> -->

      <!-- Search form -->
      <!-- <form class="navbar-form navbar-left" role="search">
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Search">
      </div>
    </form> -->
      <!-- Links -->
      <ul class="nav navbar-nav pull-right">
        <li class="dropdown pull-right">
          <a data-toggle="collapse" class="dropdown-toggle" href="#" aria-expanded="true"> <i class="icon-user"></i> ${user.name } <b
            class="caret"></b>
          </a>
          <!-- Dropdown menu -->
          <ul class="dropdown-menu">
            <!-- <li>
            <a href="#"><i class="icon-user"></i> 资料</a>
          </li>
          <li>
            <a href="#"><i class="icon-cogs"></i> 设置</a>
          </li> -->
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