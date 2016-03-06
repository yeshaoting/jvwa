<%@ page language="java" pageEncoding="UTF-8"%>

<div class="row" ng-controller="StageCtrl">
  <div class="col-md-6">

    <!-- Widget -->
    <div class="widget">

      <!-- Widget title -->
      <div class="widget-head">
        <div class="pull-left">
          <h2>关卡4</h2>
        </div>
        <div class="clearfix"></div>
      </div>

      <div class="widget-content">
        <!-- Widget content -->
        <div class="padd">
          <!-- Contact box -->
          <div class="stage-content">
            <p>
              <h4>任务目标：通过本页面提供的虚拟货币购买价值最高的物品</h4>
              <h4>Tips：0 - (-1) = 1</h4>
            </p>
          </div>
        </div>
            
        <div class="widget-content referrer">
          <table class="table table-striped table-bordered table-hover center">
            <tr>
              <th class="center">物品名称</th>
              <th class="center">物品价格</th>
              <th class="center">操作</th>
            </tr>
            <tr>
              <td>红米1手机</td>
              <td>$399</td>
              <td>
                <button class="btn btn-default" title="购买" ng-click="buy(0)">购买</button>
              </td>
            </tr>
            <tr>
              <td>小米5手机</td>
              <td>$1999</td>
              <td>
                <button class="btn btn-default" title="购买" ng-click="buy(1)">购买</button>
              </td>
            </tr>
            <tr>
              <td>劳斯莱斯</td>
              <td>$7999999</td>
              <td>
                <button class="btn btn-default" title="购买" ng-click="buy(2)">购买</button>
              </td>
            </tr>
          </table>
        </div>

        <div class="widget-foot">
          <div class="pull-right">
            <label class="label label-warning">你现在拥有$20000虚拟货币</label>
          </div>
          <div class="clearfix"></div>
        </div>

      </div><!-- widget-content -->

    </div>
  </div>
  <!-- .col-md-12 -->
</div>
<!-- .row -->


