layui.use(['http','layer'], function(){
	//工具初始化
	var http = layui.http;
	var layer = layui.layer;
	
	$("#postBtn").click(function(){
		var cont = {};
		cont.appkey=$("#appkey").val().trim();
		cont.reqObj=JSON.parse($("#request").val().trim());
		console.log(cont);
		$("#logMsg").empty();
		http.commonAjax('ehc/MD5Util',cont,function(result){
			if(result.retCode=="0"){
				var retData = result.retObject;
				$("#logMsg").append("sign before MD5:"+retData.beforeSign);
				$("#logMsg").append("<br>sign after MD5:"+retData.sign);
				cont.reqObj.sign = retData.sign;
				sendPost(cont);
			}else{
				layer.alert(result.retMsg);
			}
		});
	});
	
	function sendPost(cont){
		cont.url = $("#url").val();
		http.commonAjax('ehc/sendPost',cont,function(result){
			if(result.retCode=="0"){
				var retData = result.retData;
				var retList = retData.split(";");
				for(var i=0;i<retList.length;i++){
					$("#logMsg").append("<br>"+retList[i]);
				}
			}else{
				layer.alert(result.retMsg);
			}
		});
	}
});