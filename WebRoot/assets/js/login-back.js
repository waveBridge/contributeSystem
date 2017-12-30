function denglu(){
	var inputusername=$("#username1").val();
	var inputpassword=$("#password1").val();
		$.ajax({
        url : "loginAction",   //这个就是填相应的action
        type : "POST",             //数据发送方式
        dataType : "json",         // 接受数据格式
        
        // 要传递的数据
        data : {
          username:inputusername,
          password:inputpassword
        },

		// 回调函数，接受服务器端返回给客户端的值，即result值
		success : function(data) {      //后台传回json
                var json = eval(data);      
                var data = " ";
                var message=json.msg;

                if(message=="0"){
                  alert("用户名或密码错误");
                }else if(message=="1") {
                  window.location.href='Writer-main.html';
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
