$(document).ready(function() {
	$("#btn").click(function() {
		$.ajax({
			url : "loginAction",   //这个就是填相应的action
			type : "POST", 		   //数据发送方式
			dataType : "json",	   // 接受数据格式
				
			// 要传递的数据
			data : {
				username:'ljq',   
				password:'123'
			},
				
			// 回调函数，接受服务器端返回给客户端的值，即result值
			success : function(data) {      //后台传回json
				var json = eval(data);      
				var data = "";
				alert(json.msg);
				//window.location.href='Writer-login.html';    //跳转页面
			},
			error:function(){
				console.log("asasas");
				alert("fail");
			}	
		});
	});
});
