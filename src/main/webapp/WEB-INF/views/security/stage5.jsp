<%@ page language="java" pageEncoding="UTF-8"%>

<div class="row" ng-controller="StageCtrl">
  <div class="col-md-6">

    <!-- Widget -->
    <div class="widget">

      <!-- Widget title -->
      <div class="widget-head">
        <div class="pull-left">
          <h2>关卡5</h2>
        </div>
        <div class="clearfix"></div>
      </div>

      <div class="widget-content">
        <!-- Widget content -->
        <div class="padd">
          <!-- Contact box -->
          <div class="stage-content">
            <p>
              <h4>任务目标：通过收到的短信验证码完成手机验证。</h4>
              <h4>Tips：有时候采用最原始的方法收到的效果反而更显著。</h4>
            </p>
            <hr />
            <p>
            <form class="form-horizontal" role="form">
              <div class="form-group">
                <label for="phone" class="col-lg-3 control-label">手机号：</label>
                <div class="col-lg-8">
                  <input ng-model="phone" ng-disabled="true" type="text" class="form-control" id="phone" placeholder="手机号">
                </div>
              </div>
              <div class="form-group">
                <label for="smsCode" class="col-lg-3 control-label">验证码：</label>
                <div class="col-lg-8">
                  <input ng-model="smsCode" type="text" class="form-control" id="smsCode" placeholder="验证码">
                </div>
              </div>
            </form>
            </p>

          </div>
        </div>
        
        <div class="widget-foot">
          <div class="pull-right">
            <button class="btn btn-primary" ng-click="verfiySmsCode()">验证</button>
            <button class="btn btn-danger" ng-click="sendSmsCode()">获取短信验证码</button>
          </div>
          <div class="clearfix"></div>
        </div>

      </div><!-- widget-content -->

    </div>
  </div>
  <!-- .col-md-12 -->
</div>
<!-- .row -->


