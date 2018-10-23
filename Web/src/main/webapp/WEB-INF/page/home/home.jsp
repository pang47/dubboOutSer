<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String bathPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/inc.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<style type="text/css">
	body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;overflow: hidden;}
</style>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">后台管理</div>
			<!-- <ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="javascript:void(0);">控制台</a>
				</li>
				<li class="layui-nav-item"><a href="javascript:void(0);">商品管理</a>
				</li>
				<li class="layui-nav-item"><a href="javascript:void(0);">用户</a>
				</li>
				<li class="layui-nav-item"><a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">邮件管理</a>
						</dd>
						<dd>
							<a href="">消息管理</a>
						</dd>
						<dd>
							<a href="">授权管理</a>
						</dd>
					</dl></li>
			</ul> -->

			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="http://t.cn/RCzsdCq" class="layui-nav-img"> Tao
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="">退了</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="javascript:;">易惠</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">POST请求</a>
								<input type="hidden" value='/page/currPage?str=net/post'>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item"><a href="javascript:;">工具</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">列表一</a>
							</dd>
							<dd>
								<a href="javascript:;">列表二</a>
							</dd>
							<dd>
								<a href="">超链接</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item"><a href="">阿里云</a></li>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<div class="layui-tab layui-tab-card" style="margin-top: 0px;">
				<ul class="layui-tab-title">
					<li class="layui-this">首页</li>
					<!-- <li class="layui-this">网站设置</li>
					<li>用户管理</li>
					<li>权限分配</li>
					<li>商品管理</li>
					<li>订单管理</li> -->
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						首页内容
					</div>
					<!-- <div class="layui-tab-item layui-show" style="width: 100%;height: 600px;">
						<iframe src="http://localhost:8081/Web/page/currPage?str=net/post"  frameborder="no" width="100%" height="100%"></iframe>
					</div>
					<div class="layui-tab-item">2</div>
					<div class="layui-tab-item">3</div>
					<div class="layui-tab-item">4</div>
					<div class="layui-tab-item">5</div>
					<div class="layui-tab-item">6</div> -->
				</div>
			</div>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			© copyright chentao
		</div>
	</div>
</body>
<script src="/Web/js/layui/layuiConfig.js"></script>
<script src="/Web/js/page/home/home.js"></script>
</html>