function denglu(){
	var username=$("#username1").val();
	var password=$("#password1").val();
		$.ajax({
        url : "loginAction",   //这个就是填相应的action
        type : "POST",             //数据发送方式
        dataType : "json",         // 接受数据格式
        
        // 要传递的数据
        data : {
          username:username,
          password:password
        },

		// 回调函数，接受服务器端返回给客户端的值，即result值
		success : function(data) {      //后台传回json
                var json = eval(data);      
                var data = " ";
                var message=json.msg;
                if(message=="0"){
                  alert("登录失败");
                }else{
                  window.location.href='main.html';
                }
        },
        error: function (data) {
           alert("登录失败");
    	},
    // complete: function(){
    // 	alert("登录失败2");
    // }
	});	
}
