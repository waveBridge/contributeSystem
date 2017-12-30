$(document).ready(function() {

	$("#btn").click(function() {

		$.ajax({
			url : "upFileAction",   //这个就是填相应的action
			type : "POST", 		   //数据发送方式
			dataType : "json",	   // 接受数据格式
			
			// 要传递的数据
			data : {
				materialName:'materialNameValue',   
				upload:'uploadValue'
			},
			
			// 回调函数，接受服务器端返回给客户端的值，即result值
			success : function(msg) {      //后台传回json
				var json = eval(msg);      
				var data = " ";
				if(msg=="1"){
					alert("上传成功");
					window.location.href='Writer-search';   //跳转页面
				}
				else if(msg=="-1")alert("文件过大，请上传1M以内文件");
				else if(msg=="-2")alert("文件类型不匹配");
				else if(msg=="0")alert("哈哈哈！你太倒霉啦！！未知错误");
				
			}

		});
	});
});
