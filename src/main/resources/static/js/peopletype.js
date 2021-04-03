layui.use(['element', 'form', 'layedit', 'laydate', 'upload', 'colorpicker', 'layer', 'jquery'], function () {
    var form = layui.form,
        element = layui.element,
        layedit = layui.layedit,
        laydate = layui.laydate,
        upload = layui.upload,
        colorpicker = layui.colorpicker;
    var layer = layui.layer;
    var $ = layui.jquery;
    layer.ready(function () {
        loadAlltype();
    })
    form.on('submit(execute)', function () {

        var ptName = $('#name').val();
        var ptSort = $('#sort').val();

        if (ptName == "") {
            layer.msg("请输入人员类型", {icon: 2, time: 1000});
            return false;
        }

        $.ajax({
            type: 'post'
            , url: 'addPeopleType'
            , dataType: 'json'
            , data: {
                'ptName': ptName,
                'ptSort': ptSort
            }
            , success: function (data) {
                console.log(data);

                if (data.code == 200) {
                    layer.msg("注册成功!");

                    window.location.href = "/article-list";
                } else if (data.code == 0) {
                    layer.msg("注册失败!");
                }

            }
            , error: function (data) {
                layer.msg("服务器崩了!");
            }
        })

    })
    //点击编辑

    //编辑
    form.on('submit(edit)', function () {
        var ptId = $('#pid').val();
        var ptName = $('#name').val();
        var ptSort = $('#sort').val();

        if (ptName == "") {
            layer.msg("请输入修改后的人员类型", {icon: 2, time: 1000});
            return false;
        }
        $.ajax({
            type: 'post'
            , url: 'editPeopleType'
            , dataType: 'json'
            , data: {
                'ptId': ptId,
                'ptName': ptName,
                'ptSort': ptSort
            }
            , success: function (data) {
                console.log(data);

                if (data.code == 200) {
                    layer.msg("修改成功!");

                    window.location.href = "/article-list";
                } else if (data.code == 0) {
                    layer.msg("修改失败!");
                }

            }
            , error: function (data) {
                layer.msg("服务器崩了!");
            }
        })


    })
    //删除


});

function loadAlltype() {
    layui.use('jquery', function (mid) {
        var $ = layui.jquery;
        console.log("sadasd");
        $.ajax({
            type: 'post',
            url: 'getAllPeopleType',
            dataType: 'json',

            success: function (data) {
                console.log(data);
                var str = "";
                var idd = 1;
                var url="delPeopleType";

                for (var i = 0; i < data.pt.length; i++) {
                    var iidd = data.pt[i].ptId;
                    str += "<tr>" +
                        "<td>" + idd + "</td>" +
                        "<td>" + data.pt[i].ptId + "</td>" +
                        "<td>" + data.pt[i].ptName + "</td>" +
                        "<td>" + data.pt[i].ptSort + "</td>" +
                        "<td>" +
                        "<input type='checkbox' name='switch' lay-skin='switch' data-url='' value='1' data-id='1' title='状态' lay-text='开启|禁止' checked='checked'>" +
                        "</td>" +
                        " <td>" +
                        " <a class='layui-btn layui-btn-sm layui-btn-normal' title='编辑' onclick=\"execute_open('编辑人员类型', 'article_category_operation_edit', 1000, 400," + iidd + ")\" ><i class='layui-icon layui-icon-edit'></i>编辑</a>" +
                        " <a class='layui-btn layui-btn-sm layui-btn-danger' title='删除' onclick=\"execute_del(this," + iidd + ",'delPeopleType')\" ><i class='layui-icon layui-icon-delete'></i>删除</a>" +
                        " </td>" +
                        " </tr>";
                    idd++;


                }


                $('#message-list').html(str);
            }
        })
    })
}

//获取url的参数
function getRequestParameter() {

    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        var strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}

