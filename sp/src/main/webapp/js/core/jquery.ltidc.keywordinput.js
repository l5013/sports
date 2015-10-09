(function($){
	var setting;
	$.fn.keywordinput = function(opts) {
		setting = $.extend({
			number:25,
			url:"",
			exists_id:"keyword-exists"
		},opts||{});
		init(this);
		$(this).keyup(inputKeyword);
		/**
		 * 通过事件委派处理
		 */
		$("#keywords-wrap").on("click","a.keyword-shut",function(event){
			var data = $(this).next().val();
			var that = this;
			$.ajax({
				url:setting.url,
				data:{name:data},
				success: function(data) {
					if(data=="success") {
						$(that).parent(".keyword-in").remove();
					} else {
						alert("资源已被使用，不能删除！");
					}
				}
			});
			event.preventDefault();
		});
	}
	function initAddKeyword() {
		$("#"+setting.exists_id+" span").each(function() {
			var ki = createKeyword($(this).html());
			$("#keywords-wrap").append(ki);
		})
	}
	function init(input) {
		$(input).addClass("keyword-input");
		$(input).wrap("<div id='keyword-container'></div>")
				.before("<div id='keywords-wrap'></div>");
		initAddKeyword();
	}
	function inputKeyword(event) {
		var code = event.keyCode;
		if(code==188) {
			var c = $(this).val().split(",")[0];
			if(c!="") {
				if($(".keyword-in").length>=setting.number) {
					alert("最多只能允许添加"+setting.number+"个机柜位置");
					event.preventDefault();
					return false;
				}
				var aks = $("input[name='aks']");
				for(var i=0;i<aks.length;i++) {
					if($(aks[i]).val()==c) {
						alert("不能添加重复的机柜");
						event.preventDefault();
						return false;
					}
				}
				var ki = createKeyword(c);
				$("#keywords-wrap").append(ki);
				$(this).val("");
			}
		}
	}
	function createKeyword(val) {
		return "<div class='keyword-in'><span>"+val
		+"</span><a href='#' class='keyword-shut'>×</a><input type='hidden' name='aks' value='"+val+"'/></div>";
	}
})(jQuery)