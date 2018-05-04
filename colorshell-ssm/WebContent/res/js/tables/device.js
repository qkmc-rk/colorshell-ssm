$(document).ready(function(){
	refreshDeviceTable();
	
	//监听bootstrap table选择，好改变按钮的状态
	$('#devicetable').on('check.bs.table',function (row,elem) {
		openDeviceBtn();
		
		var rows = $('#devicetable').bootstrapTable('getSelections');
        var uelems = $("#deviceupdateinfo").children('input');
        
        $(uelems[0]).val($(rows).last()[0].id);
        $(uelems[1]).val($(rows).last()[0].mac);
        $(uelems[2]).val($(rows).last()[0].token);
        
	});
	$('#devicetable').on('uncheck.bs.table', function (row,elem) {
        closeDeviceBtn();
        
        var uelems = $("#deviceupdateinfo").children('input');
        
        $(uelems[0]).val("");
        $(uelems[1]).val("");
        $(uelems[2]).val("");
    });
});

function refreshDeviceTable(){
	var ls = window.localStorage;
	//1.ajax获取数据
	$.ajax({
		type:"get",
		url:"../admin/device/all",
		async:true,
		data:{
			'token':ls.getItem("token"),
		},
		//根据返回值
		success:function(datas){
			var datas = JSON.parse(datas);
			var data = datas.data;
			console.log(data);
			//2.将数据格式进行转换
			//3.将数据放到表中
			$('#devicetable').bootstrapTable('load', data);
		},
		error:function(){
			$.jGrowl("访问错误");
		}
	});
}

//增加用户
function adddevice(){
	var addinfo = $('#deviceaddinfo').children('input');
	var addinfo2 = $('#deviceaddinfo').children('select');
	//获取modal输入框中的内容
	var mac = addinfo[0].value;
    var type = addinfo2[0].value;
	//发送ajax请求到后台，增加设备
    $.ajax({
        url:"../admin/device/reg",
        type:'POST',
        dataType:'json',
        data:{
        	mac: mac,
        	type: type,
            token: window.localStorage.getItem("token")
        },
        async:true,
        success:function (datas) {
            console.log(datas);
            if(datas.code == 0){
                $("#adddevice-modal").modal('hide');
                //刷新表格
                refreshDeviceTable();
                $.jGrowl(datas.data);
            }else{
            	$.jGrowl(datas.data);
            }
           
        }
    });

}

function deletedevice(){
	//获取选中的一行
	var rows = $('#devicetable').bootstrapTable('getSelections');
	var id = $(rows).last()[0].id;
	
	//使用ajax删除
    $.ajax({
        url:'../admin/device/' + id,
        type:'get',
        dataType:'json',
        data:{
        	/*_method:'delete',*/
            token:window.localStorage.getItem("token")
        },
        async:'true',
        success:function (datas) {
        	if(datas.code == 0){
                closeDeviceBtn();
                refreshDeviceTable();
                $("#deletedevice-modal").modal('hide');
                $.jGrowl(datas.data);
        	}else{
        		$.jGrowl(datas.data);
        	}
        	
        }
    });
}

//修改内容
function updateDevice(){
	//获取值
	var updateinfo = $('#deviceupdateinfo').children('input');
	
	var id = updateinfo[0].value;
    var mac =updateinfo[1].value;
    //var token = updateinfo[2].value;
    
    var updateinfo2 = $('#deviceupdateinfo').children('select');
    var type = updateinfo2[0].value;
    
	//发送请求
    
	//处理结果
    $.ajax({
        url:"../admin/device/" + id,
        type:'post',
        dataType:'json',
        data:{
        	mac: mac,
        	//token: token,
        	type: type,
            token: window.localStorage.getItem("token")
        },
        async:true,
        success:function (datas) {
        	if(datas.code == 0){
                closeDeviceBtn();
                $("#updatedevice-modal").modal('hide');
                refreshDeviceTable();
                $.jGrowl("修改成功");
        	}else{
        		$.jGrowl(datas.data);
        	}
        }
    });
}




function closeDeviceBtn() {
    $("button[name='deletedevice']").attr('disabled', 'disabled');
    $("button[name='updatedevice']").attr('disabled', 'disabled');
}
function openDeviceBtn() {
    $("button[name='deletedevice']").removeAttr('disabled');
    $("button[name='updatedevice']").removeAttr('disabled');
}


//生成token的函数
function generateToken(){
	$('#token').val($.md5(randomString(10)));
}

//生成随机字符串
function randomString(len){
	len = len || 32;
	var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
	var maxPos = $chars.length;
	var pwd = '';
	for (i = 0; i < len; i++) {
		pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
	}
	return pwd;
}