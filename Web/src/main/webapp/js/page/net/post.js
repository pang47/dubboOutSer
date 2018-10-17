layui.use(['http','layer','map','form'], function(){
	//工具初始化
	var http = layui.http;
	var layer = layui.layer;
	var map = layui.map;
	//表单根据这个做
	var form = layui.form;
	
	
	var type = "";

	
	//存储对应的URL-密钥信息
	
	form.on('select(env)',function(data){
		console.log(data);
		var params = data.value.split(",");
		var cont = {};
		cont = {
			ini_type:params[0],
			ini_class:params[1]
		};
		type = data.elem[data.elem.selectedIndex].text
		if(type.indexOf("大屏")!=-1){
			$('.layui-form-item').each(function(){
				if(!$(this).hasClass('noDisabled')&&!$(this).hasClass('bigScreen')){
					$(this).hide();
				}else{
					$(this).show();
				}
			});
		}else{
			$('.layui-form-item').each(function(){
				if(!$(this).hasClass('noDisabled')&&$(this).hasClass('bigScreen')){
					$(this).hide();
				}else{
					$(this).show();
				}
			});
		}
		http.ajax('INICOFINGSERVICE','getIniConfig',cont,function(data){
			for(var i=0;i<data.length;i++){
				var bean = data[i];
				$("#"+bean['ini_code']).val(bean['ini_code_value']);
			}
		});
	});
		
	$("#postBtn").click(function(){
		var cont = {};
		cont.appkey=$("#appkey").val().trim();
		cont.reqObj=JSON.parse($("#request").val().trim());
		if(!cont.reqObj){
			layer.alert("请输入请求报文");
			return;
		}
		$("#logMsg").empty();
		if(type.indexOf("大屏")!=-1){
			sendPost(cont);
		}else{
			http.commonAjax('ehc/MD5Util',cont,function(result){
				if(result.retCode=="0"){
					var retData = result.retObj;
					$("#logMsg").append("sign before MD5:"+retData.beforeSign);
					$("#logMsg").append("<br>sign after MD5:"+retData.sign);
					cont.reqObj.sign = retData.sign;
					sendPost(cont);
				}else{
					layer.alert(result.retMsg);
				}
			});
		}
	});
	
	function sendPost(cont){
		cont.url = $("#url").val();
		cont.term_id = $("#term_id").val();
		cont.sign_key = $("#sign_key").val();
		cont.encrypt_key = $("#encrypt_key").val();
		cont.isCommon = type.indexOf('大屏')!=-1?false:true;
		http.commonAjax('ehc/sendPost',cont,function(result){
			if(result.retCode=="0"){
				debugger;
				var retData = result.retObj;
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