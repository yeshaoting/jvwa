<%@ page language="java" pageEncoding="UTF-8"%>

<div class="row" ng-controller="StageCtrl">
  <div class="col-md-6">

    <!-- Widget -->
    <div class="widget">

      <!-- Widget title -->
      <div class="widget-head">
        <div class="pull-left">
          <h2>关卡7</h2>
        </div>
        <div class="clearfix"></div>
      </div>

      <div class="widget-content">
        <!-- Widget content -->
        <div class="padd">
          <!-- Contact box -->
          <div class="stage-content">
            <p>
              <h4>任务目标：请获得本页面的访问权限</h4>
              <h4>Tips：一些公开的来源控制方法也许并不安全</h4>
            </p>
          </div>
        </div>
        
      </div><!-- widget-content -->

      <div class="widget-foot">
        <div class="pull-right">
          <button class="btn btn-primary" ng-click="checkStage7Right()">验证权限</button>
        </div>
        <div class="clearfix"></div>
      </div>
    </div>
  </div>
  <!-- .col-md-12 -->
</div>
<!-- .row -->

