function shangchuan() {
	var formData = new FormData(document.getElementById('formFile'));
	console.log(formData);
	$.ajax({
		url : "upFileAction",   //这个就是填相应的action
		type : 'POST', 
		dataType : formData, 
		// 告诉jQuery不要去处理发送的数据
		processData : false, 
		// 告诉jQuery不要去设置Content-Type请求头
		contentType : false,
		beforeSend:function(){
		console.log("正在进行，请稍候");
		},
		success : function(responseStr) { 
		if(responseStr.status===0){
			alert("上传成功");
			console.log("成功"+responseStr);
			window.location.href='Writer-search.html';
		}else{
			console.log("失败");
			alert("上传失败");
		}
		}, 
		error : function(responseStr) { 
			alert("服务器错误");
		} 
	});
}
