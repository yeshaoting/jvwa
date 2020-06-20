<%@ page language="java" pageEncoding="UTF-8"%>

<div class="row" ng-controller="StageCtrl">
  <div class="col-md-6">

    <!-- Widget -->
    <div class="widget">

      <!-- Widget title -->
      <div class="widget-head">
        <div class="pull-left">
          <h2>主页</h2>
        </div>
        <div class="clearfix"></div>
      </div>

      <div class="widget-content">
        <!-- Widget content -->
        <div class="padd">
          <!-- Contact box -->
          <div class="stage-content">
            <p>
              <h1>欢迎你，<label class="label label-warning" ng-bind="user.username"></label></h1>
              <br/>
              <h3>当前，你已经闯过 <label class="label label-success" ng-bind="user.stage"></label> 关!</h3>
            </p>
            <br/><br/><br/>
            <p>
              <div class="pull-left">
                关卡列表：
              </div>
              <div class="list-group-horizontal">
                <a ng-repeat="i in range() track by $index" href="#/stage{{$index+1}}" class="btn stage-item"
                  ng-class="is_open_stage ? 'btn-info' : ($index + 1 <= user.stage ? 'btn-primary' : 'btn-danger')"><span
                  ng-bind="$index + 1"></span></a>
              </div>
            </p>
          </div>
        </div>
        
        <div class="widget-foot" ng-if="user.stage < max_stage">
          <div class="pull-right">
            <button class="btn btn-primary" ng-click="nextStage()">下一关卡</button>
          </div>
          <div class="clearfix"></div>
        </div>
        <div class="widget-foot" ng-if="user.stage >= max_stage">
          <div class="pull-right">
            哇，偶像你完成了所有关卡！
          </div>
          <div class="clearfix"></div>
        </div>
        
      </div>
      <!-- widget-content -->

    </div>
  </div>
  <!-- .col-md-12 -->
</div>
<!-- .row -->


