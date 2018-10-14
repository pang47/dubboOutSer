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
<link rel="stylesheet" href="<%=basePath %>/js/layui/css/layui.css"  media="all">
<%@include file="/inc.jsp"%>
<body>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>发送POST请求</legend>
	</fieldset>
	<div class="layui-form layui-form-pane">
		<div class="layui-form-item">
			<label class="layui-form-label">请求URL</label>
			<div class="layui-input-block">
				<input type="text" name="url" id="url" placeholder="请输入URL" class="layui-input" value="http://axhealth.dataquanzhou.com:10777/ehcService/gateway.do">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">appkey</label>
			<div class="layui-input-block">
				<input type="text" name="appkey" id="appkey" placeholder="请输入appkey" class="layui-input" value="GChzvsDctNJtVErWXlLauCQIyCWVZBOc">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">termid</label>
			<div class="layui-input-block">
				<input type="text" name="termid" id="termid" placeholder="请输入termid" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">请求报文</label>
		    <div class="layui-input-block">
		      <textarea placeholder="请输入内容" class="layui-textarea" name="request" id="request"></textarea>
		    </div>
		</div>
		<div style="padding: 15px; background-color: #F2F2F2;">
			<div class="layui-row layui-col-space15">
		    	<div class="layui-card">
		        	<div class="layui-card-header">发送情况</div>
		        	<div class="layui-card-body" id="logMsg">
		        		日志信息:
		        	</div>
		      	</div>
			</div>
		</div>
		<div style="padding: 15px;text-align: center;">
			<button class="layui-btn layui-btn-normal" id="postBtn">发送</button>
		</div>		
	</div>
</body>
<script src="<%=basePath %>/js/layui/layuiConfig.js"></script>
<script src="<%=basePath %>/js/page/net/post.js"></script>

</html>