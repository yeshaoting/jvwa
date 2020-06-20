<%@ page language="java" pageEncoding="UTF-8"%>

<!-- Page heading -->
<div class="page-head">
  <h2 class="pull-left">
    <i class="{{menu.icon ? menu.icon : icon-home }}"></i>
    <span ng-bind="menu.name"></span>
  </h2>

  <!-- Breadcrumb -->
  <div class="bread-crumb pull-right">
    <a href="#/dashboard"><i class="icon-home"></i> 首页</a>
    <span ng-repeat="breadcrumb in breadcrumbs">
      <span class="divider">/</span>
      <a href="{{breadcrumb.link}}" ng-class="{'bread-current':$last}"><span ng-bind="breadcrumb.name"></span></a>
    </span>

  </div>

  <div class="clearfix"></div>

</div>
<!-- Page heading ends -->
