(function($){
	$.fn.trColorChange = function() {
		$(this).find("tbody tr:even").addClass("success");
		$(this).find("tbody tr:odd").addClass("warning");
	};
	
	$.fn.confirmOperator = function(opts) {
		var settings = $.extend({
			msg:"该操作不可逆，确定进行该操作吗？",
			eventName:"click",
			title:"删除"
		},opts||{});
		$(this).on(settings.eventName,function(event){
			var url = $(this).attr("url");
			$("#dialog-confirm").attr("title",settings.title);
			$("#dialog-confirm").html("<p><span class='ui-icon ui-icon-alert' style='float:left; margin:0 7px 20px 0;'></span>"+settings.msg+"</p>");
			$("#dialog-confirm").dialog({
			      resizable: false,
			      height:180,
			      modal: true,
			      buttons: {
			        "确定": function() {
			          $(this).dialog( "close" );
			          window.location.href=url;
			        },
			        "取消": function() {
			          $(this).dialog( "close" );
			        }
			      }
			 });
		});
	};
	
	$.fn.limit=function() {  
	    var self = $("[limit]");  
	    self.each(function(){  
	        var objString = $(this).text();
	        var objLength = $(this).text().length;  
	        var num = $(this).attr("limit");  
	        $(this).attr("title",objString);  
	        if(objLength > num){  
	            objString = $(this).text(objString.substring(0,num) + "...");  
	        }  
	    });  
	};
	
})(jQuery);

