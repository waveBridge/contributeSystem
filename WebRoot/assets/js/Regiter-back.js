function zhuce(){
        var inputusername=$("#username").val();
        var inputnickname=$("#nickname").val();
        var inputpw1=$("#pw1").val();
        var inputcompany=$("#company").val();
        var inputaddress=$("#address").val();
        var inputphone=$("#phone").val();
        var inputemail=$("#email").val();
        var inputvcode=$("#vcode").val();
        $.ajax({
        url : "registerAction",   //这个就是填相应的action
        type : "POST",             //数据发送方式
        dataType : "json",         // 接受数据格式
        
        // 要传递的数据
        data : {
            username:inputusername,   
            password:inputpw1,
            nickname:inputnickname,
            email:inputemail,
            vcode:inputvcode,
            company:inputcompany,
            address:inputaddress,
            phone:inputphone
        },

        // 回调函数，接受服务器端返回给客户端的值，即result值
        success : function(data) {      //后台传回json
                var json = eval(data);      
                var data = " ";
                var message=json.msg;

                if(message=="3"){
                  alert("用户名重复");
                }else if(message=="2") {
                    alert("验证码错误或失效");
                }else if(message=="0"){
                    alert("服务器原因注册失败");
                }else if(message=="1"){
                    alert("注册成功");
                    window.location.href='Writer-main.html';
                }
        },
        error: function (data) {
           alert("注册失败");
        }
    // complete: function(){
    //  alert("登录失败2");
    // }
    }); 
}