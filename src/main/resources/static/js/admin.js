layui.use(['element', 'form', 'layedit', 'laydate', 'upload', 'colorpicker','layer','jquery'], function() {
	var form = layui.form,
		element = layui.element,
		layedit = layui.layedit,
		laydate = layui.laydate,
		upload = layui.upload,
		colorpicker = layui.colorpicker;
	var layer = layui.layer;
	var $ = layui.jquery;

	layer.ready(function () {
	  loadAllManager();
	  loadType();
	  loadManagerTypes();
	  form.render();
	});
	//TODOSong:这里代码拼接可能有问题 无法显示
	form.on('submit(getManagers)', function () {
		//发起ajax请求
		layui.use('jquery', function () {
			var $ = layui.jquery;
			$.ajax({
				type: "post",
				url: "getManagers",
				dataType: "json",
				success: function (datas) {
					console.log(datas)
					//字符串的拼接
					var str = "";
					for ( i = 0; i < datas.count; i++) {
						//str +="<option>"+ datas.data[i].atName+"</option>"
						str+="<tr><td>"+datas.data[i].mngId+
							"</td><td>"+datas.data[i].mngName+
							"</td><td>"+datas.data[i].atName+
							"</td><td>"+datas.data[i].mngNumber+
							"</td><td>"+datas.data[i].atPower+
							"</td><td>" +
							"<a class='layui-btn layui-btn-sm layui-btn-normal' title='编辑' onclick='execute_open('编辑管理员', 'admin_operation.html?id=1', 1000, 700)' href='javascript:;'><i class='layui-icon layui-icon-edit'></i>编辑</a>\n" +
							"<a class='layui-btn layui-btn-sm layui-btn-danger' title='删除' onclick='execute_del(this, 1, '')' href='javascript:;'><i class='layui-icon layui-icon-delete'></i>删除</a>\n" +
							"</td></tr>"
					}
					console.log(str);
					$('#manageInfo').html(str);
					form.render();
				}
			})
		})
	});

	form.on('submit(getTypes)', function () {
		//发起ajax请求
		layui.use('jquery', function () {
			var $ = layui.jquery;
			$.ajax({
				type: "post",
				url: "getType",
				dataType: "json",
				success: function (datas) {
					console.log(datas)
					//字符串的拼接
					var str = "";
					for ( i = 0; i < datas.count; i++) {
						//str +="<option>"+ datas.data[i].atName+"</option>"
						str +="<option value='"+datas.data[i].atId+ "'>"+ datas.data[i].atName+"</option>"
					}
					// data.forEach(function (item) {
					//     str += "<li><a href='javascript:void(0);' id='blogTypes'>" + item.data.typeName + "</a></li>"
					// })
					$('#system_role_id').html(str);
					form.render()
				}
			})
		})
	});





		/**日期选择**/
		/**开始日期**/
		laydate.render({elem: '#start_time', type: 'datetime'});
		/**结束日期**/
		laydate.render({elem: '#end_time', type: 'datetime'});

		/**颜色选择**/
		colorpicker.render({
			elem: '#font-color',
			color: '#1c97f5',
			done: function (color) {
				$('#font-color-input').val(color);
			}
		});

		/**
		 * 操作管理--数据操作
		 * @param String url 请求路径
		 * @param json data.field 提交的json数据
		 * @return json code 0:操作成功；1:value 返回操作后的状态
		 */

		form.on('submit(execute1)', function () {
			var atPower = $('#atPower').val();
			var atName = $('#atName').val();
			var atSort = $('#atSort').val();
			var atEdit = $('#atEdit').val();
			var atAdd = $('#atAdd').val();
				$.ajax({
					type: 'post'
					, url: 'addAtType'
					, dataType: 'json'
					, data: {
						'atPower': atPower,
						'atName': atName,
						'atSort': atSort,
						'atEdit': atEdit,
						'atAdd': atAdd
					}
					, success: function (data) {
						//注册成功则msg==200
						if (data.code == 200) {
							layer.msg("添加成功!");
							window.location.href = "/auth_operation";
						}
					}
					, error: function (data) {
						layer.msg("服务器崩了!");
					}
				})

		});

	form.on('submit(execute)', function () {
		var mngNumber = $('#username').val();
		var mngPwd = $('#password').val();
		var mngPwd2 = $('#passwords').val();
		var mngType = $('#system_role_id').val();
		var mngName = $('#name').val();
		var mngMobile = $('#mobile').val();
		if (mngNumber == "") {
			layer.msg("请输入登录账号", {icon: 2, time: 1000});
			return false;
		}
		if (mngPwd.length < 6) {
			layer.msg("登录密码最少6位数", {icon: 2, time: 1000});
			return false;
		}
		if (mngMobile.length != 11) {
			layer.msg("电话号码有误", {icon: 2, time: 1000});
			return false;
		}
		layer.msg("调用成功!");
		if (mngPwd == mngPwd2) {
			$.ajax({
				type: 'post'
				, url: 'register'
				, dataType: 'json'
				, data: {
					'mngNumber': mngNumber,
					'mngPwd': mngPwd,
					'mngType': mngType,
					'mngName': mngName,
					'mngMobile': mngMobile
				}
				, success: function (data) {
					//注册成功则msg==200
					if (data.code == 200) {
						layer.msg("注册成功!");
						window.location.href = "/admin-operation";
					} else if (data.code == 0) {
						layer.msg("注册失败!");
					}
				}
				, error: function (data) {
					layer.msg("服务器崩了!");
				}
			})
		} else {
			layer.msg("两次密码不一致!");
		}
	});

	//TODOSong:显示管理员信息



		/**
		 * 操作管理--数据状态
		 * @param object switch 当前
		 * @param int id 当前操作对象ID
		 * @param int status 当前操作对象状态
		 * @return json code 0:操作成功；1:value 返回操作后的状态
		 */
		form.on('switch', function (switchs) {
			layer.confirm('确认要修改吗？', function (index) {
				layer.load();
				var id = $(switchs.elem).data('id');
				var url = $(this).data('url');
				var status = switchs.value;
				$.ajax({
					url: '' + url + '',
					type: 'Post',
					data: {'id': id, 'status': status},
					dataType: 'json',
					success: function (data) {
						layer.closeAll('loading');
						if (data.code == 0) {
							$(switchs.elem).attr('value', data.data);
							layer.msg(data.message, {icon: 1, time: 1000});
							return false;
						} else {
							layer.msg(data.message, {icon: 2, time: 1000});
							return false;
						}
					},
					error: function (e) {
						layer.closeAll('loading');
						layer.msg(e.responseText, {icon: 2, time: 1000});
					}
				});
				return false;
			});
		});

		/**
		 * 操作管理--文件上传
		 * @param upload_pictures 需要上传事件ID
		 * @param place 文件存放位置
		 * @param layui_progress 进度条事件
		 * @param images_url 返回路径赋值图片ID
		 * @return json code 0:操作成功；1:操作失败；route 返回的图片路径
		 */
		upload.render({
			elem: '#upload_pictures',
			url: '',
			data: {
				file_place: function () {
					return $(this).data('place');
				}
			},
			multiple: true,
			progress: function (n) {
				var percent = n + '%';
				element.progress('layui_progress', percent);
			},
			before: function (obj) {
				layer.load();
			},
			done: function (data) {
				layer.closeAll('loading');
				if (data.code == 0) {
					layer.msg(data.message, {icon: 1, time: 1000});
					$('#images_url').attr('src', data.route);
					var id_name = $(this)[0]['elem'][0]['attributes'][2]['value'];
					$('#' + id_name).val(data.route);
					return false;
				} else {
					layer.msg(data.message, {icon: 2, time: 1000});
					return false;
				}
			},
			error: function (e) {
				layer.closeAll('loading');
				layer.msg(e.responseText, {icon: 2, time: 1000});
			}
		});

	});

	/**
	 * 操作管理--打开界面
	 * @param String title 界面标题
	 * @param String url 访问路径
	 * @param int width 打开宽度
	 * @param int height 打开高度
	 */
	function execute_open(title, url, width, height, id) {
		if (id != null) {
			url = url + "?id=" + id;
		}
		layer.open({
			type: 2,
			title: '' + title + '',
			shadeClose: true,
			shade: 0.8,
			area: ['' + width + 'px', '' + height + 'px'],
			content: url,
		});

	}

	/**
	 * 操作管理--数据删除
	 * @param object obj 当前操作对象
	 * @param int id 操作ID
	 * @param String url 访问路径
	 * @return json code 0:操作成功；1:操作失败
	 */
	function execute_del(obj, id, url) {
		layer.confirm('确认要删除吗？', function (index) {
			layer.load();
			$.ajax({
				url: '' + url + '',
				type: 'Post',
				data: {'id': id},
				dataType: 'json',
				success: function (data) {
					layer.closeAll('loading');
					if (data.code == 0) {
						$(obj).parents("tr").remove();
						layer.msg(data.message, {icon: 1, time: 1000});
						return false;
					} else {
						layer.msg(data.message, {icon: 2, time: 1000});
						return false;
					}
				},
				error: function (e) {
					layer.closeAll('loading');
					layer.msg(e.responseText, {icon: 2, time: 1000});
				}
			});
		});


	}
function loadManagerTypes(){
	//发起ajax请求
	layui.use('jquery', function () {
		var $ = layui.jquery;
		$.ajax({
			type: "post",
			url: "getManagerTypes",
			dataType: "json",
			success: function (datas) {
				console.log(datas)
				//字符串的拼接
				var str = "";
				for ( i = 0; i < datas.count; i++) {
					//str +="<option>"+ datas.data[i].atName+"</option>"
					str+="<tr><td>"+datas.data[i].atId+
						"</td><td>"+datas.data[i].atName+
						"</td><td>"+datas.data[i].atPower+
						"</td><td>"+datas.data[i].atSort+
						"</td><td>"+datas.data[i].atAdd+
						"</td><td>"+datas.data[i].atEdit+
						"</td><td>" +
						"<a class='layui-btn layui-btn-sm layui-btn-normal' title='编辑' onclick='execute_open('编辑管理员', 'admin_operation.html?id=1', 1000, 700)' href='javascript:;'><i class='layui-icon layui-icon-edit'></i>编辑</a>\n" +
						"<a class='layui-btn layui-btn-sm layui-btn-danger' title='删除' onclick='execute_del(this, 1, '')' href='javascript:;'><i class='layui-icon layui-icon-delete'></i>删除</a>\n" +
						"</td></tr>"
				}
				console.log(str);
				$('#manageInfo1').html(str);
			}
		})
	})
}
	function loadAllManager(){
		//发起ajax请求
		layui.use('jquery', function () {
			var $ = layui.jquery;
			$.ajax({
				type: "post",
				url: "getManagers",
				dataType: "json",
				success: function (datas) {
					console.log(datas)
					//字符串的拼接
					var str = "";
					for ( i = 0; i < datas.count; i++) {
						//str +="<option>"+ datas.data[i].atName+"</option>"
						str+="<tr><td>"+datas.data[i].mngId+
							"</td><td>"+datas.data[i].mngName+
							"</td><td>"+datas.data[i].atName+
							"</td><td>"+datas.data[i].mngNumber+
							"</td><td>"+datas.data[i].atPower+
							"</td><td>" +
							"<a class='layui-btn layui-btn-sm layui-btn-normal' title='编辑' onclick='execute_open('编辑管理员', 'admin_operation.html?id=1', 1000, 700)' href='javascript:;'><i class='layui-icon layui-icon-edit'></i>编辑</a>\n" +
							"<a class='layui-btn layui-btn-sm layui-btn-danger' title='删除' onclick='execute_del(this, 1, '')' href='javascript:;'><i class='layui-icon layui-icon-delete'></i>删除</a>\n" +
							"</td></tr>"
					}
					console.log(str);
					$('#manageInfo').html(str);
					//form.render();
				}
			})
		})
	}
function loadType() {
	//发起ajax请求
	layui.use('jquery', function () {
		var $ = layui.jquery;
		$.ajax({
			type: "post",
			url: "getType",
			dataType: "json",
			success: function (datas) {
				console.log(datas)
				//字符串的拼接
				var str = "";
				for ( i = 0; i < datas.count; i++) {
					//str +="<option>"+ datas.data[i].atName+"</option>"
					str +="<option value='"+datas.data[i].atId+ "'>"+ datas.data[i].atName+"</option>"
				}
				// data.forEach(function (item) {
				//     str += "<li><a href='javascript:void(0);' id='blogTypes'>" + item.data.typeName + "</a></li>"
				// })
				$('#system_role_id').html(str);
				//form.render()
			}
		})
	})
}

	/**选项卡切换**/
	var nav_id = $('.layui-this').data('nav');
	$('#layui_nav_left_' + nav_id).show();

	$('.layui-layout-left').find('.layui-nav-item').click(function () {
		var nav_id = $(this).data('nav');
		$('#layui_nav_left_' + nav_id).show().siblings().hide();
	});

	$('.layui-nav-child').find('dd').find('a').click(function () {
		var url = $(this).data('url');
		$('.layui-iframe').attr('src', url);
	});

