<%@ page language="java" pageEncoding="UTF-8"%>

<div class="row" ng-controller="StageCtrl">
  <div class="col-md-6">

    <!-- Widget -->
    <div class="widget">

      <!-- Widget title -->
      <div class="widget-head">
        <div class="pull-left">
          <h2>关卡8</h2>
        </div>
        <div class="clearfix"></div>
      </div>

      <div class="widget-content">
        <!-- Widget content -->
        <div class="padd">
          <!-- Contact box -->
          <div class="stage-content">
            <p>
              <h4>任务目标：请主动设置你的过关级别为8</h4>
              <h4>Tips：你猜到他们猜不到你猜得到他们猜不到你猜得到</h4>
            </p>
            <hr />
            <p class="center">
                <img ng-src='{{stage8QrcodeUrl}}' width="250" />
            </p>
          </div>
        </div>
        
      </div><!-- widget-content -->

    </div>
  </div>
  <!-- .col-md-12 -->
</div>
<!-- .row -->

