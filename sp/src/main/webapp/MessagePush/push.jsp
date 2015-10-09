<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style>
	div.bare-box {
	    background: #FFF none repeat scroll 0% 0%;
	    border-radius: 5px;
	    color: #323F48;
	    border: 1px solid #BDC6CD;
	    margin-bottom: 20px;
	}
	div.bare-box h4.leader {
	    border-top: 0px none;
	}
	div.bare-box h4 {
	    clear: both;
	    border-top: 1px solid #CCC;
	    margin: 0px;
	    padding: 10px 15px 0px;
	    font-weight: bold;
	}
	.container-main h4 {
	    font-size: 15px;
	    color: #323F48;
	    margin-bottom: 15px;
	}
	div.bare-box div.bare-box {
	    background-color: #F8F8F8;
	    border: 1px solid #CCC;
	    padding: 0px;
	    margin: 5px 15px 15px;
	}
	#id_alert {
	    font-size: 16px;
	    width: 98%;
	    height: 65px;
	    padding: 10px;
	}
	ul, ol {
	    padding: 0px;
	    margin: 0px;
	    list-style-type: none;
	}
	.bare-box input[type="text"], .bare-box select, .bare-box textarea {
	    background-color: #FAFAFA;
	    font: 15px/20px Arial,sans-serif;
	    height: 33px;
	    margin: 0px;
	    border-radius: 5px;
	    width: 398px;
	    transition: box-shadow 0.2s linear 0s;
	    border: 1px solid rgba(0, 0, 0, 0.2);
	}
	div.bare-box div.bare-box ul li {
	    padding: 5px;
	}
	#preview-chars, span #id_rp_limit_count {
	    font-weight: 700;
	    font-size: 22px;
	    font-style: italic;
	    font-family: Constantia,Georgia;
	}
	span.warning {
		color:#F99;
	}
	span.alert {
		color:#F00;
	}
	div.actions {
	    text-align: right;
	    clear: both;
	    padding: 10px;
	}
</style>

<script type="text/javascript">
$(function(){  
    $("#id_alert").bind({  
         "keyup": function (e) {  
        	 checkWords($(this).val());
         }  
    });
    
    $( "#dialog" ).dialog({
        autoOpen: false,
        show: {
          effect: "blind",
          duration: 1000
        }
      });
});  
var urlReg ={  
    "urlRegText":"((news|telnet|nttp|file|http|ftp|https)://){1}(([-A-Za-z0-9]+(\\.[-A-Za-z0-9]+)*(\\.[-A-Za-z]{2,5}))|([0-9]{1,3}(\\.[0-9]{1,3}){3}))(:[0-9]*)?(/[-A-Za-z0-9_\\$\\.\\+\\!\\*\\(\\),;:@&=\\?/~\\#\\%]*)*"  
};  
// 字符串长度  
function smartLen(str) {  
    str = str.replace(/(^[\s\n]+)|([\s\n]+$)/,"").replace(/[\s\n]+/g," ");  
    str = str.replace(new RegExp(urlReg.urlRegText, "gi"), new Array(12).join("aa"));  
    return Math.ceil(($.trim(str.replace(/[^\u0000-\u00ff]/g, "aa")).length) / 2);  
}  
function checkWords(content){  
    var len = 72-smartLen(content);  
      if (len < 0) {  
        $("#submitButton").attr("disabled",true);  
        $("#preview-chars").addClass("alert");
	   	
      } else {  
    	 if(len < 12 && len > 3 ) {
    		$("#preview-chars").removeClass("alert");
    		$("#preview-chars").addClass("warning");
    	} else {
   			$("#preview-chars").removeClass("warning");
    		if(len <= 3) {
        		$("#preview-chars").addClass("alert");
    		};
    	}
    	$("#submitButton").attr("disabled",false);          
    }  
        $("#preview-chars").html(len);     
};


function push() {
	$("#loadinggif").css("display","");
	$.post("${pageContext.request.contextPath}/MessagePush/push.do",
			{"content":$("#id_alert").val()},
			function(data) {
				if(data.result=="true") {
	            	$("#loadinggif").css("display","none");
	            	$("#successModal").modal("show");
	            }
			});
}
</script>

<div class="app-notification-wrapper">
    <form method="post" id="push-form" class="confirm_this" action="${pageContext.request.contextPath }/MessagePush/push.do">
        <div class="bare-box">
            <h4 class="leader">
                推送内容
            </h4>
            <div id="step-1">
                <div class="bare-box">
                    <ul class="form">
                        <li><textarea id="id_alert" rows="10" cols="40" name=content></textarea></li>
                        <li class="remaining"><div class="" id="remaining-bytes">
                                您还剩下
                                <span id="preview-chars">72</span>
                                个汉字可以输入
                            </div></li>
                    </ul>
                </div>
                
                <div class="actions">
                    <img src="${pageContext.request.contextPath }/images/loading.gif" style="padding-right: 10px; display: none;" id="loadinggif">
                    <button type="button" id="submitButton" class="btn btn-primary" onclick="push()">立即发送</button>
                </div>
            </div>
        </div>
    </form>
</div>


 <div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-body">
      	<h4 class="modal-title" id="myModalLabel">发送成功</h4>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
                    
                    
