;(function($){
	$.fn.extend({
		'formatJson':function(){
			debugger;
			var _v = this.val();
			if(!_v) return;
			try{
				this.val(JSON.stringify(JSON.parse(_v), null, 4));
			}catch(e){
				alert(e.description);
			}
			return this;
		}
	});
})(jQuery);