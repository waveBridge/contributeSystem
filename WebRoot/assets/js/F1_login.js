window.onload = function(){  
    var cli = document.getElementById("click"); 
    var pass = document.getElementById("password1"); 
    cli.onclick = function(){  
        if(pass.type == "text"){
            pass.type = "password";
            cli.src = "assets/images/eye.png";
        }
        else {
            pass.type = "text";
            cli.src = "assets/images/eye1.png";
        }
    }
}

function validateForm(){
    var user = document.getElementById("username").value;
    var pass = document.getElementById("password1").value;
    if (user == null || user == "" || pass == null || pass == "" ){
        alert("账号、密码不能为空！");
        return false;
    }
} 