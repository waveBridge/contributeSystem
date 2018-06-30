var msg;
window.onload = function() {
	$.ajax({
        url : "getClassifyMaterialAction",   //这个就是填相应的action
        type : "POST",             //数据发送方式
        dataType : "json",         // 接受数据格式
        async: false, 
        // 要传递的数据
        data : {
        },

	    // 回调函数，接受服务器端返回给客户端的值，即result值
	    success : function(data) {  
	    	var json=eval(data);
	    	msg=json.msg; 
	    	var length=json.cnt;

	    	if(msg!="0"){
	    		
	    	// 	var str=" ";

	    	// 	for(var i=0;i<length;i++){
	    			
	    	// 			str+="<li data-id='id-1' data-type='category1 'class='four columns m-bot-35'>"+
						// 	"<div class='hover-item'>"+
						// 		"<div class='view view-first'>"+
						// 			"<img src='images/content/port-2-2.jpg' alt='Ipsum' >"+
						// 			"<div class='mask'></div>	"+
						// 			"<div class='abs'>"+
						// 				"<a href='images/content/port-2-2.jpg' class='lightbox zoom info'></a><a href='portfolio-single.html' class='link info'></a>"+
						// 			"</div>	"+
						// 		"</div>"+
						// 		"<div class='lw-item-caption-container'>"+
						// 			"<a class='a-invert' href='portfolio-single.html' >"+
						// 				"<div class='item-title-main-container clearfix'>"+
						// 					"<div class='item-title-text-container'>"+
						// 						"<span class='bold'>"+msg[i].cid+"</span> "+
						// 					"</div>"+
						// 				"</div>"+
						// 			"</a>"+
						// 			"<div class='item-caption'>"+
						// 				"<div class='name-sty'>"+msg[i].classifyName+"</div>"+
						// 				"<div class='name-sty'>"+msg[i].classifyName+"</div>"+
						// 				"<div class='name-sty'>"+msg[i].classifyName+"</div>"+
						// 			"</div>"+
						// 		"</div>"+
						// 	"</div>"+
						// "</li>"

	    	// 	}
	    	// 	$("#show_all").html(str);
	    	}else{
	    		alert("数据加载错误");
	    	}
	    },
	    error:function(data){
	    	alert("数据加载失败");
	    }
	}); 
}

function fenlei(a){
	
}

function findClass(a){
	switch(a){
		case 1:return '哲学';
	}
}