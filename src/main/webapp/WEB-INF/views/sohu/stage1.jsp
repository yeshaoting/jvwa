<%@ page language="java" pageEncoding="UTF-8"%>

<div class="row" ng-controller="StageCtrl">
  <div class="col-md-6">

    <!-- Widget -->
    <div class="widget">

      <!-- Widget title -->
      <div class="widget-head">
        <div class="pull-left">
          <h2>关卡1</h2>
        </div>
        <div class="clearfix"></div>
      </div>

      <div class="widget-content">
        <!-- Widget content -->
        <div class="padd">
          <!-- Contact box -->
          <div class="stage-content">
            <p>
              <h4>任务目标：找出正确的用户名与密码并通过验证。</h4>
              <h4>Tips：这个页面用户与密码不需要去服务器交互验证。</h4>
            </p>
            <hr />
            <p>
            <form class="form-horizontal" role="form">
              <div class="form-group">
                <label for="stage1Username" class="col-lg-3 control-label">用户：</label>
                <div class="col-lg-8">
                  <input ng-model="stage1Username" type="text" class="form-control" id="stage1Username" placeholder="用户">
                </div>
              </div>
              <div class="form-group">
                <label for="state1Password" class="col-lg-3 control-label">密码：</label>
                <div class="col-lg-8">
                  <input ng-model="state1Password" type="password" class="form-control" id="state1Password" placeholder="密码">
                </div>
              </div>
            </form>
            </p>

          </div>
        </div>
        
        <div class="widget-foot">
          <div class="pull-right">
            <button class="btn btn-primary" ng-click="stage1Submit()">提交</button>
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


