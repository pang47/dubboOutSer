layui.define(['jquery'],function(exports){
	var map = function(){
		this.keys = new Array();
		this.values = new Array();
		this.put = function(_k, _v){
			var _i = -1;
			for(var i=0;i<this.keys.length;i++){
				if(this.keys[i] == _k){
					_i = i;
				}
			}
			if(_i == -1){
				this.keys.add(_k);
				this.values.add(_v);
			}else{
				this.values[i] = _v;
			}
		}
		this.get = function(_k){
			for(var i=0;i<this.keys.length;i++){
				if(this.keys[i] == _k){
					return this.values[i];
				}
			}
			return null;
		}
		this.size = function(){
			return this.keys.length;
		}
		this.remove = function(_k){
			for(var i=0;i<this.keys.length;i++){
				if(this.keys[i] == _k){
					this.keys.splice(i,1);
					this.values.splice(i,1);
				}
			}
		}
		this.isEmpty = function(){
			return this.keys.length==0?true:false;
		}
	}
	exports('map',map);
});