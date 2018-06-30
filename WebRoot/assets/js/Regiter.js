var countdown=60; 
var flag=0;

// 密码一致性判断
function validate() {
    var pw1 = document.getElementById("pw1").value;
    var pw2 = document.getElementById("pw2").value;
    if(pw1 == pw2) {
        document.getElementById("tishi").innerHTML="<font color='green'>两次密码相同</font>";
        document.getElementById("submit").disabled = false;
    }
    else {
        document.getElementById("tishi").innerHTML="<font color='red'>两次密码不相同</font>";
        document.getElementById("submit").disabled = true;
    }
}



function yanzheng(obj) { 
//邮箱验证
	// alert("9999");
	var eemail = document.getElementById("email").value;
	// alert(eemail);
	if(eemail=="")
	{
		alert("邮箱不能为空");
		flag=0;
		// alert("邮箱1="+flag);
		return false;
	}else if(!eemail.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/))
	{
		alert("格式不正确！请重新输入");
		flag=0;
		// alert("邮箱2="+flag);
		return false;
	} else {
		// alert("邮箱="+flag);
		flag=1;
	}
	settime(obj);
}


//验证码倒计时

function settime(obj) { 

// alert("1="+flag);

	if(flag==1){
    // alert("345");
    	var inputEmail=$("#email").val();
    	$.ajax({
        	url : "getVCodeAction",   //这个就是填相应的action
            type : "POST",             //数据发送方式
            dataType : "json",         // 接受数据格式
            
            // 要传递的数据
            data : {
              email:inputEmail
            },
            
            // 回调函数，接受服务器端返回给客户端的值，即result值
            
            success : function(data) {      //后台传回json
                   
                var json = eval(data);      
                var data = " ";
                var message=json.msg;
                
                // alert(message);
                // alert(typeof message);
                //msgFlag=message;
                if(message=="1"){
                  // alert("已发送邮件");
                }else{
                  alert("邮件发送失败！");
                  obj.removeAttribute("disabled"); 
                  obj.value="免费获取验证码"; 
                  countdown = 60;
                }
            }
    	});
	    obj.removeAttribute("disabled"); 
	    obj.value="免费获取验证码"; 
	    countdown = 60;
	}
  	abc(obj);
}
function abc(obj){
		// alert("2="+flag);
		// alert("count="+countdown);
      	countdown=countdown*flag;
      	if (countdown == 0 ) { 
			obj.removeAttribute("disabled"); 
			obj.value="免费获取验证码"; 
			countdown = 60; 
			return;
      	}
      	else  if(countdown > 0) { 
          	obj.setAttribute("disabled", true); 
          	obj.value="重新发送(" + countdown + ")"; 
          	countdown--;   
      	} 
      	setTimeout(function() {abc(obj) },1000)    
  	}


// $(document).ready(function(){
	
// 	$("#captcha").click(function(){
// 		alert("0004="+flag);
// 		if($("#email").val()=="")
// 		{
// 			alert("邮箱不能为空");
// 			flag=0;
// 			alert("邮箱1="+flag);
// 			return false;
// 		}
// 		var email=$("#email").val();
// 		if(!email.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/))
// 		{
// 			alert("格式不正确！请重新输入");
// 			flag=0;
// 			alert("邮箱2="+flag);
// 			return false;
// 		}
// 		alert("邮箱="+flag);
// 		flag=1;
// 	})
// }) 




//邮箱格式验证

// function yanzheng(){
// 	alert("9999");
// 	var eemail = document.getElementById("email").value;
// 	if(eemail=="")
// 	{
// 		alert("邮箱不能为空");
// 		flag=0;
// 		alert("邮箱1="+flag);
// 		return false;
// 	}else if(!eemail.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/))
// 	{
// 		alert("格式不正确！请重新输入");
// 		flag=0;
// 		alert("邮箱2="+flag);
// 		return false;
// 	} else {
// 		alert("邮箱="+flag);
// 		flag=1;
// 	}

// }

