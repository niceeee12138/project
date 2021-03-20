function login() {
    var mngId = document.getElementById("mngId").value;
    var mngPwd = document.getElementById("mngPwd").value;
    var manager = {
        mngId: mngId,
        mngPwd: mngPwd
    }
    var Id=10000;
    var Pwd=123456;
    var manager1 = {
        mngId: Id,
        mngPwd: Pwd
    }
    //发异步，把数据提交给后端
    $.ajax({
        url: "admin/login",
        type: "POST",
        data: JSON.stringify(manager1),
        dataType: "json",
        contentType: 'application/json;charset=utf-8',
        success: function(data) {
            if (data.status == 200) {
                window.location.href="index";
            } else {
                alert(data.msg);
            }
        }
    });
}


