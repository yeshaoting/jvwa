<%@ page language="java" pageEncoding="UTF-8"%>

<!-- Sidebar -->
<div class="sidebar">
  <div class="sidebar-dropdown">
    <a href="#">导航</a>
  </div>


  <!--- Sidebar navigation -->
  <!-- If the main navigation has sub navigation, then add the class "has_sub" to "li" of main navigation. -->
  <ul id="nav">
    <!-- Main menu with font awesome icon -->
    <li>
      <a class="dashboard" ng-class="{open:$state.includes('dashboard')}" ui-sref="dashboard" href="#/dashboard"><i class="icon-dashboard"></i> 首页</a>
    </li>

    <li>
      <a class="user" ng-class="{open:$state.includes('user')}" ui-sref="user" href="#/user"><i class="icon-group"></i> 用户管理</a>
    </li>

    <!-- 模块管理 -->
    <li>
      <a class="module" ng-class="{open:$state.includes('module')}" ui-sref="module.list" href="#/module/list"><i class="icon-folder-close"></i>
        模块管理</a>
    </li>

    <li>
      <a class="content" ng-class="{open:$state.includes('content')}" ui-sref="content" href="#/content"><i class="icon-reorder"></i> 内容管理</a>
    </li>

    <!-- 文件管理 -->
    <li class="file" class="has_sub" ng-class="{active:$state.includes('file')}">
      <a ng-class="{open:$state.includes('file')}" class="{{$state.includes('file') ? 'subdrop' : ''}}" href=""><i class="icon-file"></i> 文件管理<span
        class="pull-right"><i class="icon-chevron-right"></i></span></a>
      <ul>
        <li ui-sref-active="active">
          <a ui-sref="file.upload" href="#/file/upload">文件上传</a>
        </li>
        <li ui-sref-active="active">
          <a ui-sref="file.list" href="#/file/list">文件列表</a>
        </li>
      </ul>
    </li>

    <li>
      <a class="content" ng-class="{open:$state.includes('changelog')}" ui-sref="changelog" href="#/changelog"><i class="icon-reorder"></i> 更新记录管理</a>
    </li>

  </ul>
</div>

<!-- Sidebar ends -->