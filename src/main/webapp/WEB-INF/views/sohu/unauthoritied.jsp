<%@ page language="java" pageEncoding="UTF-8"%>

<div class="row">
  <div class="col-md-6" ng-controller="StageCtrl">

    <!-- Widget -->
    <div class="widget">

      <!-- Widget title -->
      <div class="widget-head">
        <div class="pull-left">
          <h2>无权限关卡</h2>
        </div>
        <div class="clearfix"></div>
      </div>

      <div class="widget-content">
        <!-- Widget content -->
        <div class="padd">
          <!-- Contact box -->
          <div class="stage-content">
            <p>
              <h1>hi，<label class="label label-warning" ng-bind="user.username"></label></h1>
              <br/>
              <h3>你暂无本第 <label class="label label-danger" ng-bind="${id}"></label> 关权限，请一步一个脚印，先完成前面的关卡!</h3>
            </p>
          </div>
        </div>
      </div><!-- widget-content -->

      <div class="widget-foot" ng-if="user.stage < maxStage">
        <div class="pull-right">
          <button class="btn btn-primary" ng-click="nextStage()">下一关卡</button>
        </div>
        <div class="clearfix"></div>
      </div>
    </div>
  </div>
  <!-- .col-md-12 -->
</div>
<!-- .row -->


