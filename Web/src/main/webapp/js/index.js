$(function(){
	//alert(1);
	/*var cont = {};
	cont.test = 1;
	cont.test2 = 'ca';
	$("#request_json").val(JSON.stringify(cont));*/
});

function post(){
	if($('#process_id').val().trim() == ''){
		alert('输入有误');
		return;
	}
	var list = $('#process_id').val().trim().split('.');
	debugger;
	if(list.length<2){
		alert('格式错误');
		return;
	}
	if($('#request_json').val().trim()!=""&&!isJson($('#request_json').val().trim())){
		alert('格式错误');
		return;
	}
	if((list[1].indexOf('get')==-1)&&!isJson($('#request_json').val().trim())){
		alert('格式错误');
		return;
	}
	
	
	var cont = {};
	cont.service_id = list[0];
	cont.method_id = list[1];
	cont.params = $('#request_json').val().trim()!=''?JSON.parse($('#request_json').val().trim()):{};

	$.ajax({
		url:getLocation()+'/Web/test/dubbo.do',
		dataType:'json',
		data :cont,
		type:'POST',
		//cache:false,		
		success:function(data){
			if(data.retCode == '0'){
				var retData = data.retObject;
				if(retData.retCode == '0'){
					$("#response_json").val(JSON.stringify(retData.object));
				}else{
					$("#response_json").val(retData.message);
				}
			}else{
				$("#response_json").val(data.retMsg);
			}
		},
		error:function(data){
			alert(JSON.stringify(data));
		}
	});
}

function getLocation(){
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPath = curWwwPath.substring(0, pos);
    return localhostPath;
}

function isJson(str){
	if(typeof str == 'string'){
		try{
			var obj = JSON.parse(str);
			if(obj && typeof obj == 'object')
				return true;
			else
				return false;
		}catch (e) {
			return false;
		}
	}
}
