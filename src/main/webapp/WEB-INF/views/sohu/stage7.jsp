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
              <h4>任务目标：</h4>
              <h4>Tips：</h4>
            </p>
            <hr />
            <p>
            <form class="form-horizontal" role="form">
              <div class="form-group">
                <label for="code" class="col-lg-3 control-label">密码：</label>
                <div class="col-lg-8">
                  <input ng-model="code" type="text" class="form-control" id="code" placeholder="密码">
                </div>
              </div>
            </form>
            </p>

          </div>
        </div>
        
        <div class="widget-foot">
          <div class="pull-right">
            <button class="btn btn-primary" ng-click="checkCode()">提交</button>
          </div>
          <div class="clearfix"></div>
        </div>

      </div><!-- widget-content -->

    </div>
  </div>
  <!-- .col-md-12 -->
</div>
<!-- .row -->


