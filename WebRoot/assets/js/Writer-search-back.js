$(document).ready(function(){
	// var inputusername=$("#username1").val();
	// var inputpassword=$("#password1").val();
  $.ajax({
        url : "getMaterialAction",   //这个就是填相应的action
        type : "POST",             //数据发送方式
        dataType : "json",         // 接受数据格式
        async: false, 
        // 要传递的数据
        data : {
        },

    // 回调函数，接受服务器端返回给客户端的值，即result值
    success : function(data) {  
        var json=eval(data);
        var msg=json.msg;    
        if(msg!="-1"){
          var str=" ";
          for(var i=0;i<msg.length;i++){
            if(msg[i].state==0){
              str+="<tr>"+
                "<td>"+msg[i].materialName+"</td>"+
                "<td>"+msg[i].user.username+"</td>"+
                "<td>"+msg[i].date+"</td>"+
                "<td>"+"待审核"+"</td>"+
                "<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>下载</button></td>"+
                "<td><button class='upplode' midup='"+msg[i].mid+"' href='Writer-renew.html'>更新</button></td>"+

              "</tr>";
            }
          }
          $("#show_all").html(str);
          }
        },
      error:function(data){
          alert("数据加载失败");
      }
  }); 

$(".load").click(function(){ 
     var mid= $(this).attr("mid");
     window.location.href="downFileAction?mid="+mid;
   });


  $(".upplode").click(function(){ 
     var midd2= $(this).attr("midup");
      localStorage.setItem("filename", midd2);
     var file2=localStorage.getItem("filename");
     //alert(file2);
     window.location.href="Writer-renew.html";
   });

});



