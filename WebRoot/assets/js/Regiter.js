//验证码倒计时
var countdown=60; 
var flag=0;
function settime(obj) { 
  abc(obj);
  function abc(obj){
    
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
                      alert("已发送邮件");
                    }else{
                      alert("邮件发送失败！");
                      obj.removeAttribute("disabled"); 
                      obj.value="免费获取验证码"; 
                      countdown = 60;
                    }
            }
            // error : function( ) {     //有错误
            //         alert("邮件发送失败！");
            //           obj.removeAttribute("disabled"); 
            //           obj.value="免费获取验证码"; 
            //           countdown = 60;
            // }
    });
    // if(msgFlag=="1"){
    //   alert("已发送邮件");
    // }else{
    //   alert("已发送邮件2222");
    // }
    // msgFlag==" ";
    obj.removeAttribute("disabled"); 
    obj.value="免费获取验证码"; 
    countdown = 60;
  }

}
//邮箱格式验证
 $(document).ready(function(){
 $("#captcha").click(function(){
  if($("#email").val()=="")
  {
   alert("邮箱不能为空");
   flag=0;
   return false;
  }
  var email=$("#email").val();
  if(!email.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/))
  {
   alert("格式不正确！请重新输入");
   flag=0;
   return false;
  }
  flag=1;
 })
}) 

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

