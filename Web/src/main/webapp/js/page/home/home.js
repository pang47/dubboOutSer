layui.use(['element','http','menu'], function(){
	var element = layui.element;
	var http = layui.http;
	var menu = layui.menu;
	
	//menu.init('menu');
	
	/*element.on('nav(leftMenu)', function(elem){
		//console.log(elem)
		layer.msg(elem.text());
	});*/
	
	var height = $('.layui-bg-black').height() - $('.layui-tab-title').height() - $(".layui-footer").height();
	
	$(".layui-nav-tree").find('a').each(function(){
		$(this).click(function(){
			var url = $(this).siblings('input').val();
			var title = $(this).html();
			$(this).parents('.layui-bg-black').siblings('.layui-body').find('ul').append('<li class="that">'+title+'</li>').find('.that').removeClass('that')
				.addClass('layui-this').siblings('li').each(function(){
					$(this).removeClass('layui-this');
				}).parents('ul').siblings('.layui-tab-content')
				.append('<div class="that layui-tab-item" id="aaa" style="width: 100%;"><iframe src="'+url+'"  frameborder="no" width="100%" height="600px"></iframe></div>')
				.find('.that').removeClass('that').addClass('layui-show').siblings('div').each(function(){
					$(this).removeClass('layui-show');
				}).siblings('.layui-show').each(function(){
					setIframeHeight(this);
				});
		});
	});
	
	function setIframeHeight(that) {
		var iframe = $(that).find('iframe');
		if (iframe) {
			var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
			if (iframeWin.document.body) {
				iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
			}
		}
	}
	
});