layui.use(['form','upload','layer','jquery'],function (){
    var form = layui.form;
    var layer = layui.layer;
    var $ = layui.jquery;
    form.on('submit(execute)',function (){
        var mngId=$('#username').val();
        var pwd1=$('#password').val();
        var pwd2=$('#passwords').val();
        if(pwd1!=pwd2){
            layer.msg("两次密码不一致！");
        }
        var name=$('#name').val();
        var tel=$('#mobile').val();
       var status=document.getElementsByName("status");
        for (var i = 0; i < status.length; i++) {
            if (status[i].checked == true) {
               var mngStatus=status[i].value;
            }
        }

        $.ajax({
            type:'post'
            ,url:'addManagerType'
            ,dataType:'json'
            ,data:{
                'mngId':mngId,
                'mngPwd':pwd1,
                'mngName':name,
                'mngTel':tel,
                'mngStatus':status

            }
            ,success:function (data){


            }
            ,error:function (data){
                layer.msg("服务器崩了!");
            }
        })

    })
})