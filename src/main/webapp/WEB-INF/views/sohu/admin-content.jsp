<%@ page language="java" pageEncoding="UTF-8"%>

<div class="row" ng-controller="AdminCtrl">
  <div class="col-md-6">

    <!-- Widget -->
    <div class="widget">

      <!-- Widget title -->
      <div class="widget-head">
        <div class="pull-left">
          <h2>WEB安全渗透实战-后台管理系统</h2>
        </div>
        <div class="clearfix"></div>
      </div>

      <div class="widget-content">
        <!-- Widget content -->
        <div class="widget-content referrer">
          <!-- Widget content -->

          <table class="table table-striped table-bordered table-hover center">
            <tr>
              <th class="center">用户名</th>
              <th class="center">当前关卡</th>
              <th class="center">操作</th>
            </tr>
            <tr>
              <td>***juan***@sina.com</td>
              <td>3</td>
              <td>
                <a href="#upgrade" title="升级">升级</a> |
                <a href="#reset" title="重置比分">重置比分</a> |
                <a href="#forbid" title="禁用账号">禁用账号</a>
              </td>
            </tr>
            <tr>
              <td>**sh**q*un*@sohu.com</td>
              <td>9</td>
              <td>
                <a href="#upgrade" title="升级">升级</a> |
                <a href="#reset" title="重置比分">重置比分</a> |
                <a href="#forbid" title="禁用账号">禁用账号</a>
              </td>
            </tr>
            <tr>
              <td>*w***y*e*@google.com</td>
              <td>4</td>
              <td>
                <a href="#upgrade" title="升级">升级</a> |
                <a href="#reset" title="重置比分">重置比分</a> |
                <a href="#forbid" title="禁用账号">禁用账号</a>
              </td>
            </tr>
            <tr>
              <td>**l***y*@163.com</td>
              <td>1</td>
              <td>
                <a href="#upgrade" title="升级">升级</a> |
                <a href="#reset" title="重置比分">重置比分</a> |
                <a href="#forbid" title="禁用账号">禁用账号</a>
              </td>
            </tr>
            <tr>
              <td>yeshaoting@163.com</td>
              <td>3</td>
              <td>
                <a href="#upgrade" title="升级" ng-click="upgrade()">升级</a> |
                <a href="#reset" title="重置比分">重置比分</a> |
                <a href="#forbid" title="禁用账号">禁用账号</a>
              </td>
            </tr>
            <tr>
              <td>***w*e*n*@qq.com</td>
              <td>7</td>
              <td>
                <a href="#upgrade" title="升级">升级</a> |
                <a href="#reset" title="重置比分">重置比分</a> |
                <a href="#forbid" title="禁用账号">禁用账号</a>
              </td>
            </tr>
            <tr>
              <td>**mi**n*k*@139.com</td>
              <td>0</td>
              <td>
                <a href="#upgrade" title="升级">升级</a> |
                <a href="#reset" title="重置比分">重置比分</a> |
                <a href="#forbid" title="禁用账号">禁用账号</a>
              </td>
            </tr>
            <tr>
              <td>**c**zh**@126.com</td>
              <td>2</td>
              <td>
                <a href="#upgrade" title="升级">升级</a> |
                <a href="#reset" title="重置比分">重置比分</a> |
                <a href="#forbid" title="禁用账号">禁用账号</a>
              </td>
            </tr>
            <tr>
              <td>*wa***pe**@163.com</td>
              <td>0</td>
              <td>
                <a href="#upgrade" title="升级">升级</a> |
                <a href="#reset" title="重置比分">重置比分</a> |
                <a href="#forbid" title="禁用账号">禁用账号</a>
              </td>
            </tr>
          </table>

          <div class="widget-foot"></div>
        </div>
      </div>
      <!-- widget-content -->

    </div>
  </div>
  <!-- .col-md-12 -->
</div>
<!-- .row -->

