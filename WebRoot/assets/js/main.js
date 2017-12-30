$(document).ready(function() {
	var show=localStorage.getItem("username");
	//alert("用户："+show);
	$("#user").innerHTML=show;
	var tbody=document.getElementById("tbody-result");
		$.ajax({
			url : "getAllMaterialAction",   
			type : "POST", 		   
			dataType : "json",	   
			async: false,	
			data : {

			},				

			success : function(data) {  
				console.log(data); 
				var json=eval(data);
				var msg=json.msg;    
				//msg属性article name time state  butn
				if(msg!="-1"){
					var str=" ";
					//var data=msg.data;
					for(var i=0;i<msg.length;i++){
						if(msg[i].state==0){
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td>"+"待审核"+"</td>"+
								"<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>确认</button></td>"+
							"</tr>";
						}else if(msg[i].state==1){
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td>"+"录用"+"</td>"+
								"<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>确认</button></td>"+
							"</tr>";
						}else if(msg[i].state==2){
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td>"+"退稿"+"</td>"+
								"<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>确认</button></td>"+
							"</tr>";
						}else{
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td>"+"修改"+"</td>"+
								"<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>确认</button></td>"+
							"</tr>";
						}
						
					}
				tbody.innerHTML=str;
				}
			},
			error:function(data){
					alert("数据加载失败");
			}
		});


	//点击全部，显示全部投稿内容
	$("#first").bind("click",function(){ 
	    $("#tbody-result tr").empty("");   
		$.ajax({
			url : "getAllMaterialAction",   
			type : "POST", 		   
			dataType : "json",	   
				
			data : {

			},				

			success : function(data) {     
				var json=eval(data);
				var msg=json.msg;     
				//msg属性article name time state  butn
				if(msg!="-1"){
					var str=" ";
					//var data=msg.data;
					for(var i=0;i<msg.length;i++){
						if(msg[i].state==0){
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td>"+"待审核"+"</td>"+
								"<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>确认</button></td>"+
							"</tr>";
						}else if(msg[i].state==1){
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td>"+"录用"+"</td>"+
								"<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>确认</button></td>"+
							"</tr>";
						}else if(msg[i].state==2){
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td>"+"退稿"+"</td>"+
								"<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>确认</button></td>"+
							"</tr>";
						}else{
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td>"+"修改"+"</td>"+
								"<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>确认</button></td>"+
							"</tr>";
						}
						
					}
				tbody.innerHTML=str;
				}
			},
			error:function(data){
					alert("数据加载失败");
			}
		});
	});
	

	//点击时间查询，稿件按时间排列好
	$("#second").bind("click",function(){ 
	    $("#tbody-result tr").empty("");   
		$.ajax({
			url : "getAllMaterialAction",   
			type : "POST", 		   
			dataType : "json",	   
				
			data : {

			},				

			success : function(data) { 
				var json=eval(data);
				var msg=json.msg;         
				if(msg!="-1"){
					var str=" ";
					//var data=msg.data;
					for(var i=0;i<msg.length;i++){
						if(msg[i].state==0){
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td>"+"待审核"+"</td>"+
								"<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>确认</button></td>"+
							"</tr>";
						}else if(msg[i].state==1){
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td>"+"录用"+"</td>"+
								"<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>确认</button></td>"+
							"</tr>";
						}else if(msg[i].state==2){
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td>"+"退稿"+"</td>"+
								"<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>确认</button></td>"+
							"</tr>";
						}else{
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td>"+"修改"+"</td>"+
								"<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>确认</button></td>"+
							"</tr>";
						}
						
					}
				tbody.innerHTML=str;
				}
			},
			error:function(data){
					alert("数据加载失败");
			}
		});
	});
	


	//点击待处理稿件，显示未处理稿件
	$("#third").bind("click",function(){ 
	 $("#tbody-result tr").empty(""); 
		$.ajax({
			url : "getAllMaterialAction",   
			type : "POST", 		   
			dataType : "json",	   
				
			data : {

			},				

			success : function(data) {       
				var json=eval(data);
				var msg=json.msg;   
				if(msg!="-1"){
					var str=" ";
					//var data=msg.data;
					for(var i=0;i<msg.length;i++){
						if(msg[i].state==0){
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td>"+"<a href='Admin-modify.html'>待审核</a>"+"</td>"+
								"<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>确认</button></td>"+
							"</tr>";
						}
						
					}
				tbody.innerHTML=str;
				}
			},
			error:function(data){
					alert("数据加载失败");
			}
		});
	});
	


	//查询功能，输入作者名，显示该作者所有稿件
	$("#right").bind("click",function(){  
		$("#tbody-result tr").empty("");
		var value=$('#search').val();
		$.ajax({
			url : " getMaterialByAuthorAction",   //这个就是填相应的action
			type : "POST", 		   //数据发送方式
			dataType : "json",	   // 接受数据格式
				
			// 要传递的数据
			data : {
				nickname:value
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
								"<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>确认</button></td>"+
							"</tr>";
						}else if(msg[i].state==1){
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td>"+"录用"+"</td>"+
								"<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>确认</button></td>"+
							"</tr>";
						}else if(msg[i].state==2){
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td>"+"退稿"+"</td>"+
								"<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>确认</button></td>"+
							"</tr>";
						}else{
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td>"+"修改"+"</td>"+
								"<td><button mid='"+msg[i].mid+"' type= 'button' class='btn btn-info load'>确认</button></td>"+
							"</tr>";
						}
					}
					tbody.innerHTML=str;
					}
				},
			//});
			error:function(data){
					alert("数据加载失败");
			}
		});
	});


	 $(".load").click(function(){ 
		 var mid= $(this).attr("mid");
		 window.location.href="downFileAction?mid="+mid;
	 });
	 
//	 $(".load").click(function(){  
//   		var mid= $(this).attr("mid");
//   		alert(mid);
//   		$.ajax({
//			url : "downFileAction",   
//			type : "POST", 		 
//			dataType : "json",	   
//			
//			data : {
//				mid:mid,
//			},				
//			success : function(data) { console.log("下载访问成功");},
//   			error:function(data){console.log("下载访问失败");}
// 	 	}); 
// 	});  

	
});
