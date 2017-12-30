 $(document).ready(function() {
	var tbody=document.getElementById("tbody-result");
	var show=localStorage.getItem("username");
	//alert(show);
	$("#user").innerHTML=show;
	$.ajax({
			url : "getAllMaterialAction",   
			type : "POST", 		   
			dataType : "json",	   
			async: false,	
			data : {

			},				

			success : function(data) {  
				var json=eval(data);
				console.log(json); 
				var msg=data.msg;       
				//msg属性article name time state  butn
				if(msg!="0"){
					var str=" ";
					
					for(var i=0;i<msg.length;i++){
						if(msg[i].state==0){
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td><select class='state'>"+
									"<option value='0' selected='selected'>待审阅</option>"+
									"<option value='1'>录用</option>"+
									"<option value='2'>退稿</option>"+
									"<option value='3'>修改</option>"+
								"</select></td>"+
								"<td><button mid='"+msg[i].mid+"' type='button' class='btn btn-success change'>确认</button></td>"+
							"</tr>";
						}else if(msg[i].state==1){
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td><select class='state'>"+
									"<option value='0'>待审阅</option>"+
									"<option value='1' selected='selected'>录用</option>"+
									"<option value='2'>退稿</option>"+
									"<option value='3'>修改</option>"+
								"</select></td>"+
								"<td><button mid='"+msg[i].mid+"' type='button' class='btn btn-success change'>确认</button></td>"+
							"</tr>";
						}else if(msg[i].state==2){
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td><select class='state'>"
									"<option value='0'>待审阅</option>"+
									"<option value='1'>录用</option>"+
									"<option value='2' selected='selected'>退稿</option>"+
									"<option value='3'>修改</option>"+
								"</select></td>"+
								"<td><button mid='"+msg[i].mid+"' type='button' class='btn btn-success change'>确认</button></td>"+
							"</tr>";
						}else if(msg[i].state==3){
							str+="<tr>"+
								"<td>"+msg[i].materialName+"</td>"+
								"<td>"+msg[i].user.username+"</td>"+
								"<td>"+msg[i].date+"</td>"+
								"<td><select class='state'>"+
									"<option value='0'>待审阅</option>"+
									"<option value='1'>录用</option>"+
									"<option value='2'>退稿</option>"+
									"<option value='3'selected='selected'>修改</option>"+
								"</select></td>"+
								"<td><button mid='"+msg[i].mid+"' type='button' class='btn btn-success change'>确认</button></td>"+
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

	


	

	 $(".change").click(function(){  
     		//  var myselect=document.getElementById("state");
		// var value=$("#select option:selected").val();
   		var mid= $(this).attr("mid");
   		//alert(mid);
   		var value=$(this).parent().prev().children(".state").find("option:selected").val();
   		//alert(value);
   		$.ajax({
			url : "changeStateAction",   
			type : "POST", 		 
			dataType : "json",	   
			async: false,	
			
			data : {
				mid:mid,
				state:value
			},				
			
			success : function(data) { console.log("修改访问成功");},
   			error:function(data){console.log("修改访问失败");}
 	 	});  
 	 	window.location.href="Admin-main.html";
 	});  


	// function update(){
	// 	$.ajax({
	// 		url : "changeStateAction",   
	// 		type : "POST", 		 
	// 		dataType : "json",	   
				
			
	// 		data : {
	// 			state:value;
	// 		},				
			
	// 		success : function(data) { 
				// var json=eval(data);
				// var msg=data.msg;       
	// 				if(msg!="0"){
	// 				for(var i=0;i<data;i++){
	// 					if(msg[i].state==0){
	// 						str+="<tr>"+
	// 							"<td>"+msg[i].materialName+"</td>"+
	// 							"<td>"+msg[i].user.username+"</td>"+
	// 							"<td>"+msg[i].date+"</td>"+
	// 							"<td><select id='state'>"+
	// 								"<option value='0' selected='selected'>待审阅</option>"+
	// 								"<option value='1'>录用</option>"+
	// 								"<option value='2'>退稿</option>"+
	// 								"<option value='3'>修改</option>"+
	// 							"</select></td>"+
	// 							"<td><button type='button' class='btn btn-success' onclick='skip();update()''>确认</button></td>"+
	// 						"</tr>";
	// 					}else if(msg[i].state==1){
	// 						str+="<tr>"+
	// 							"<td>"+msg[i].materialName+"</td>"+
	// 							"<td>"+msg[i].user.username+"</td>"+
	// 							"<td>"+msg[i].date+"</td>"+
	// 							"<td><select id='state'>"+
	// 								"<option value='0'>待审阅</option>"+
	// 								"<option value='1' selected='selected'>录用</option>"+
	// 								"<option value='2'>退稿</option>"+
	// 								"<option value='3'>修改</option>"+
	// 							"</select></td>"+
	// 							"<td><button type='button' class='btn btn-success' onclick='skip();update()''>确认</button></td>"+
	// 						"</tr>";
	// 					}else if(msg[i].state==2){
	// 						str+="<tr>"+
	// 							"<td>"+msg[i].materialName+"</td>"+
	// 							"<td>"+msg[i].user.username+"</td>"+
	// 							"<td>"+msg[i].date+"</td>"+
	// 							"<td><select id='state'>"+
	// 								"<option value='0'>待审阅</option>"+
	// 								"<option value='1'>录用</option>"+
	// 								"<option value='2' selected='selected'>退稿</option>"+
	// 								"<option value='3'>修改</option>"+
	// 							"</select></td>"+
	// 							"<td><button type='button' class='btn btn-success' onclick='skip();update()''>确认</button></td>"+
	// 						"</tr>";
	// 					}else{
	// 						str+="<tr>"+
	// 							"<td>"+msg[i].materialName+"</td>"+
	// 							"<td>"+msg[i].user.username+"</td>"+
	// 							"<td>"+msg[i].date+"</td>"+
	// 							"<td><select id='state'>"+
	// 								"<option value='0'>待审阅</option>"+
	// 								"<option value='1'>录用</option>"+
	// 								"<option value='2'>退稿</option>"+
	// 								"<option value='3'  selected='selected'>修改</option>"+
	// 							"</select></td>"+
	// 							"<td><button type='button' class='btn btn-success' onclick='skip();update()''>确认</button></td>"+
	// 						"</tr>";
	// 					}
	// 				}
	// 				tbody.innerHTML=str;
	// 				}
	// 			},
	// 		//});
	// 		error:function(data){
	// 				alert("数据加载失败");
	// 		}
	// 	});	
	// }


});
