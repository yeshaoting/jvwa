<%@ page language="java" pageEncoding="UTF-8"%>

<div class="row">
  <div class="col-md-6" ng-controller="StageCtrl">

    <!-- Widget -->
    <div class="widget">

      <!-- Widget title -->
      <div class="widget-head">
        <div class="pull-left">
          <h2>关卡2</h2>
        </div>
        <div class="clearfix"></div>
      </div>

      <div class="widget-content">
        <!-- Widget content -->
        <div class="padd">
          <!-- Contact box -->
          <div class="stage-content">
            <p>
            <h4>任务目标：本题没有可登入的用户名密码，请绕过登录验证。</h4>
            <h4>Tips：就算是判断了用户名 and 密码 都正确，也是被禁用的。</h4>
            </p>
            <hr />
            <p>
            <form class="form-horizontal" role="form">
              <div class="form-group">
                <label for="stage2Username" class="col-lg-3 control-label">用户：</label>
                <div class="col-lg-8">
                  <input ng-model="stage2Username" type="text" class="form-control" id="stage2Username" placeholder="用户">
                </div>
              </div>
              <div class="form-group">
                <label for="state2Password" class="col-lg-3 control-label">密码：</label>
                <div class="col-lg-8">
                  <input ng-model="state2Password" type="password" class="form-control" id="state2Password" placeholder="密码">
                </div>
              </div>
            </form>
            </p>

          </div>
        </div>
        
        <div class="widget-foot">
          <div class="pull-right">
            <button class="btn btn-primary" ng-click="stage2Submit()">提交</button>
          </div>
          <div class="clearfix"></div>
        </div>
      </div><!-- widget-content -->

    </div>
  </div>
  <!-- .col-md-12 -->
</div>
<!-- .row -->


