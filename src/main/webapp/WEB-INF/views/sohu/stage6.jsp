<%@ page language="java" pageEncoding="UTF-8"%>

<div class="row" ng-controller="StageCtrl">
  <div class="col-md-6">

    <!-- Widget -->
    <div class="widget">

      <!-- Widget title -->
      <div class="widget-head">
        <div class="pull-left">
          <h2>关卡6</h2>
        </div>
        <div class="clearfix"></div>
      </div>

      <div class="widget-content">
        <!-- Widget content -->
        <div class="padd">
          <!-- Contact box -->
          <div class="stage-content">
            <p>
              <h4>任务目标：找到可以通过本关的密码并提交。</h4>
              <h4>Tips：密码被硬编码到了当前页面服务器上的php代码里。</h4>
              <%-- 休想知道密码：sATa3HGe6 --%>
            </p>
            <hr />
            <p class="center">
            <img title="这个页面暗藏玄机" ng-src="{{image_url}}" width="500" />
            <form class="form-horizontal" role="form">
              <div class="form-group">
                <label for="secureCode" class="col-lg-3 control-label">密码：</label>
                <div class="col-lg-8">
                  <input ng-model="secureCode" type="text" class="form-control" id="secureCode" placeholder="密码">
                </div>
              </div>
            </form>
            </p>

          </div>
        </div>
        
        <div class="widget-foot">
          <div class="pull-right">
            <button class="btn btn-primary" ng-click="checkSecureCode()">提交</button>
          </div>
          <div class="clearfix"></div>
        </div>

      </div><!-- widget-content -->

    </div>
  </div>
  <!-- .col-md-12 -->
</div>
<!-- .row -->


