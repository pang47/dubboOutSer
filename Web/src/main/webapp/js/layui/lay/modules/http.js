layui.define(['jquery','layer'],function(exports){
	var $ = layui.jquery;
	var layer = layui.layer;
	
	function getLocation(){
		var curWwwPath = window.document.location.href;
		var pathName = window.document.location.pathname;
	    var pos = curWwwPath.indexOf(pathName);
	    //获取主机地址，如： http://localhost:8083
	    var localhostPath = curWwwPath.substring(0, pos);
	    return localhostPath;
	}
	
	var obj = {
		ajax:function(serviceId,methodId,param,callBack){
			var cont = {};
			cont.service_id = serviceId;
			cont.method_id = methodId;
			cont.params = param;
			console.log(cont);
			$.ajax({
				url:getLocation()+'/Web/data/commonInvoke.do',
				dataType:'json',
				data :cont,
				type:'POST',	
				success:function(data){
					if(data.retCode=="0"){
						if(callBack) callBack(data.retObj);
					}else{
						layer.alert(data.retMsg);
					}
					
				},
				error:function(data){
					alert(JSON.stringify(data));
				}
			});
		},
		commonAjax:function(action,param,callBack){
			$.ajax({
				url:getLocation()+'/Web/'+action+'.do',
				dataType:'json',
				contentType:'application/json',
				data :JSON.stringify(param),
				type:'POST',
				//cache:false,		
				success:function(data){
					if(callBack) callBack(data);
				},
				error:function(data){
					alert(JSON.stringify(data));
				}
			});
		}
	};
	exports('http',obj);
});