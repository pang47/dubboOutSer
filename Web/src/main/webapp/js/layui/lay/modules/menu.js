layui.define(['jquery','element'],function(exports){
	var $ = layui.jquery;
	var element = layui.element;
	
	var obj = {
		init:function(_el){
			if(_el.indexOf('#')!=0)
				_el = '#'+_el;
			$(_el).addClass('layui-side');
			$(_el).addClass('layui-bg-black');
			
			var _h = '';
			//_h+='<div class="layui-side-scroll">';
			//_h+='	<ul class="layui-nav layui-nav-tree" lay-filter="leftMenu">';
			_h+='		<li class="layui-nav-item layui-nav-itemed"><a class="" href="javascript:;">易惠</a>';
			_h+='			<dl class="layui-nav-child">';
			_h+='				<dd>';
			_h+='					<a href="javascript:;" onclick="">POST请求</a>';
			_h+='				</dd>';
			//_h+='				<dd>';
			//_h+='					<a href="javascript:;">列表二</a>';
			//_h+='				</dd>';
			//_h+='				<dd>';
			//_h+='					<a href="javascript:;">列表三</a>';
			//_h+='				</dd>';
			_h+='			</dl>';
			_h+='		</li>';
			_h+='		<li class="layui-nav-item"><a class="" href="javascript:;">工具</a>';
			_h+='			<dl class="layui-nav-child">';
			_h+='				<dd>';
			_h+='					<a href="javascript:;" onclick="">用户</a>';
			_h+='				</dd>';
			//_h+='				<dd>';
			//_h+='					<a href="javascript:;">列表二</a>';
			//_h+='				</dd>';
			//_h+='				<dd>';
			//_h+='					<a href="javascript:;">列表三</a>';
			//_h+='				</dd>';
			_h+='			</dl>';
			_h+='		</li>';
			//_h+='	</ul>';
			//_h+='</div>';
			$(_el).append(_h);
			
			var _b = '';
			_b+='<div class="layui-body">';
			_b+='	<div class="layui-tab layui-tab-card" style="margin-top: 0px;">';
			_b+='		<ul class="layui-tab-title">';
			_b+='			<li class="layui-this">首页</li>';
			_b+='		</ul>';
			_b+='		<div class="layui-tab-content">';
			_b+='			<div class="layui-tab-item layui-show">首页展示</div>';
			_b+='		</div>';
			_b+='	</div>';
			_b+='</div>';
			
			$(_el).parent().append(_b);
		}
	}
	
	exports('menu',obj);
});