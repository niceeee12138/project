layui.use(['form','upload','layer','jquery'],function (){
    var form = layui.form;
    var layer = layui.layer;
    var $ = layui.jquery;

    //监听提交事件
    form.on('submit(sub)',function (){
        var mngId=$('#mngId').val();
        var mngPwd=$('#mngPwd').val();
            // 发起ajax请求
            $.ajax({
                type:'post'
                ,url:'login'
                ,dataType:'json'
                ,data:{
                    'mngId':mngId,
                    'mngPwd':mngPwd
                }
                ,success:function (data){
                    //注册成功则msg==200
                    if(data.code==200){
                        layer.msg("登录成功!");
                        window.location.href="/index";
                        form.render();
                    }else if(data.code==0){
                        layer.msg("登录失败!");
                        form.render();
                    }
                }
                ,error:function (data){
                    layer.msg("服务器崩了!");
                }
            })

    })
})
