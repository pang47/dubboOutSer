<%@page import="org.springframework.util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String realIp = request.getHeader("x-real-ip");
	if(StringUtils.isEmpty(realIp)){
		realIp = "NoReal";
	}else{
		realIp = realIp.split(",")[0];
	}
	String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>dubbo测试页面</title>
<script src="<%=basePath %>/js/jquery-1.9.1.min.js"></script>
<style type="text/css">
	table.gridtable {
		font-family: verdana,arial,sans-serif;
		font-size: 11px;
		color: #333333;
		border-width: 1px;
		border-color: #666666;
		border-collapse: collapse;
	}
	table.gridtable th{
		border-width: 1px;
		padding: 8px;
		border-style: solid;;
		border-color: #666666;
		background-color: #dedede;
	}
	table.gridtable td{
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #666666;
		background-color: #ffffff;
	}
	.talign_r{
		text-align: right;
	}
</style>
</head>
<body>
	<table class="gridtable">
		<tr>
			<th colspan="2">dubbo接口测试工具</th>
		</tr>
		<tr>
			<td style="width: 20%" class="talign_r">
				接口名称:
			</td>
			<td style="width: 80%;">
				<input type="text" id="process_id" value="testService.getAll">
				<button style="float: right;" onclick="post();">发送</button>
				<button style="float: right;margin-right: 3px;" onclick="$('#request_json').formatJson();">格式化请求报文</button>
				<button style="float: right;margin-right: 3px;" onclick="$('#response_json').formatJson();">格式化返回报文</button>
			</td>
		</tr>
		<tr>
			<td class="talign_r">
					请求JSON:
			</td>
			<td>
				<textarea rows="20" cols="100" id="request_json"></textarea>
			</td>
		</tr>
		<tr>
			<td class="talign_r">
					应答JSON:
			</td>
			<td>
				<textarea rows="20" cols="100" id="response_json"></textarea>
			</td>
		</tr>
	</table>
</body>
<script src="<%=basePath %>/js/jquery-extend.js"></script>
<script src="<%=basePath %>/js/index.js"></script>
<script>
	var realIp = "<%=realIp%>";
	
	$("#gridtable").find('th').each(function(){
		$(this).append(",地址:"+realIp);
	});
</script>
</html>

