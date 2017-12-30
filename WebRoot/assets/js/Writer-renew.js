//localStorage.setItem("filename", "<<舞动青春>>");

var file2=localStorage.getItem("filename");
$(function(){
	
	$('#disabledTextInput').val(file2);
    $('#disabledTextInput').attr('disabled',true);
});
