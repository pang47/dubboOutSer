<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>POST发送</title>
</head>
<link rel="stylesheet" href="<%=basePath%>/js/layui/css/layui.css"
	media="all">
<%@include file="/inc.jsp"%>
<body>
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>发送POST请求</legend>
	</fieldset>
	<div class="layui-form layui-form-pane">
		<div class="layui-form-item noDisabled">
			<div class="layui-inline">
				<label class="layui-form-label">环境选择</label>
				<div class="layui-input-block">
					<select lay-verify="required" lay-search="env" name="env"
						lay-filter="env">
						<option value=""></option>
						<option value="1001,01">安溪电子健康卡环境</option>
						<option value="1001,02">安溪预交金大屏数据</option>
						<option value="1001,03">榕医通预交金大屏数据</option>
					</select>
				</div>
			</div>
		</div>

		<div class="layui-form-item noDisabled">
			<label class="layui-form-label">请求URL</label>
			<div class="layui-input-block">
				<input type="text" name="url" id="url" placeholder="请输入URL"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">appId</label>
			<div class="layui-input-block">
				<input type="text" name="appId" id="appId" placeholder="请输入appId"
					class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">appkey</label>
			<div class="layui-input-block">
				<input type="text" name="appkey" id="appkey" placeholder="请输入appkey"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">termid</label>
			<div class="layui-input-block">
				<input type="text" name="term_id" id="term_id"
					placeholder="请输入termid" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item bigScreen">
			<label class="layui-form-label">sign_key</label>
			<div class="layui-input-block">
				<input type="text" name="sign_key" id="sign_key"
					placeholder="请输入sign_key" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item bigScreen">
			<label class="layui-form-label">encrypt_key</label>
			<div class="layui-input-block">
				<input type="text" name="encrypt_key" id="encrypt_key"
					placeholder="请输入encrypt_key" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item layui-form-text noDisabled">
			<label class="layui-form-label">请求报文</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入内容" class="layui-textarea" name="request"
					id="request"></textarea>
			</div>
		</div>
		<div style="padding: 15px; background-color: #F2F2F2;">
			<div class="layui-row layui-col-space15">
				<div class="layui-card">
					<div class="layui-card-header">发送情况</div>
					<div class="layui-card-body" id="logMsg" style="word-wrap: break-word">日志信息:</div>
				</div>
			</div>
		</div>
		<div style="padding: 15px; text-align: center;">
			<button class="layui-btn layui-btn-normal" id="postBtn">发送</button>
		</div>
	</div>
</body>
<script src="<%=basePath%>/js/layui/layuiConfig.js"></script>
<script src="<%=basePath%>/js/page/net/post.js"></script>

</html>