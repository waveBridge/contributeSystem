$(document).ready(function(){

	$.ajax({
		url : "getMaterialAction",   //这个就是填相应的action
		type : "POST", 		   //数据发送方式
		dataType : "json",	   // 接受数据格式
		
		// 要传递的数据
		data : {
			
		},
		
		// 回调函数，接受服务器端返回给客户端的值，即result值
		success : function(data) {      //后台传回json
			alert("error 1");
			var json = eval(data);      
			var data = "";
			var n=json.cnt;//因为n为字符串吧？？？？？？？？？？？？？？？？？？？？？
			var N=parseInt(n);
			var work=json.msg;

			for (var i=0;i<N;i++)
			{
			$("#show_all").html("<tr><td>" + work[i].materialNname + "</td><td>" + work[i].user.username + "</td><td>" + work[i].date + "</td><td>" + work[i].state + "</td><td>" + "下载" + "</td><td>" + "<a href='Writer-renew.html'>更新</a>" + "</td></tr>");
			}

		}
		
	});
})