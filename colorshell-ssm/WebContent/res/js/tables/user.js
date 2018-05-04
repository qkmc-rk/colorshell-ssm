$(document).ready(function(){
	refreshUserTable();
	
	//监听bootstrap table选择，好改变按钮的状态
	$('#usertable').on('check.bs.table',function (row,elem) {
		openUserBtn();
		
		//当选中某个值时，将update modal中的值进行填充
		var rows = $('#usertable').bootstrapTable('getSelections');
        var uelems = $("#userupdateinfo").children('input');
        
        $(uelems[0]).val($(rows).last()[0].id);
        $(uelems[1]).val($(rows).last()[0].password);
        $(uelems[2]).val($(rows).last()[0].neckname);
        
	});
	$('#usertable').on('uncheck.bs.table', function (row,elem) {
        closeUserBtn();
        
        //当不选中某个行时，将update modal中的数据清空
        var uelems = $("#userupdateinfo").children('input');
        $(uelems[0]).val("");
        $(uelems[1]).val("");
        $(uelems[2]).val("");
    });
});


function refreshUserTable(){
	var ls = window.localStorage;
	//1.ajax获取数据
	$.ajax({
		type:"get",
		url:"../admin/user/all",
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
			$('#usertable').bootstrapTable('load', data);
		},
		error:function(){
			$.jGrowl("访问错误");
		}
	});
}

//增加用户
function adduser(){
	var addinfo = $('#useraddinfo').children('input');
	var addinfo2 = $('#useraddinfo').children('select');
	//获取modal输入框中的内容
	var mail = addinfo[0].value;
    var password = addinfo[1].value;
    var neckname = addinfo[2].value;
	//发送ajax请求到后台，增加设备
    $.ajax({
        url:"../admin/user/reg",
        type:'POST',
        dataType:'json',
        data:{
        	mail: mail,
        	password: password,
        	neckname: neckname,
            token: window.localStorage.getItem("token")
        },
        async:true,
        success:function (datas) {
            console.log(datas);
            if(datas.code == 0){
                $("#adduser-modal").modal('hide');
                //刷新表格
                refreshUserTable();
                $.jGrowl(datas.data);
            }else{
            	$.jGrowl(datas.data);
            }
           
        }
    });

}

function deleteuser(){
	//获取选中的一行
	var rows = $('#usertable').bootstrapTable('getSelections');
	var id = $(rows).last()[0].id;
	
	//使用ajax删除
    $.ajax({
        url:'../admin/user/' + id,
        type:'get',
        dataType:'json',
        data:{
            token:window.localStorage.getItem("token")
        },
        async:'true',
        success:function (datas) {
        	if(datas.code == 0){
                closeUserBtn();
                refreshUserTable();
                $("#deleteuser-modal").modal('hide');
                $.jGrowl(datas.data);
        	}else{
        		$.jGrowl(datas.data);
        	}
        	
        }
    });
}


//修改内容
function updateUser(){
	//获取值
	var updateinfo = $('#userupdateinfo').children('input');
	
	var id = updateinfo[0].value;
    var password =updateinfo[1].value;
    var neckname = updateinfo[2].value;
    
    var updateinfo2 = $('#userupdateinfo').children('select');
    var role = updateinfo2[0].value;
    
	//发送请求
    
	//处理结果
    $.ajax({
        url:"../admin/user/" + id,
        type:'post',
        dataType:'json',
        data:{
        	password: password,
        	neckname: neckname,
        	role: role,
            token: window.localStorage.getItem("token")
        },
        async:true,
        success:function (datas) {
        	if(datas.code == 0){
                closeUserBtn();
                $("#updateuser-modal").modal('hide');
                refreshUserTable();
                $.jGrowl(datas.data);
        	}else{
        		 $.jGrowl(datas.data);
        	}
        }
    });
}



function closeUserBtn() {
    $("button[name='deleteuser']").attr('disabled', 'disabled');
    $("button[name='updateuser']").attr('disabled', 'disabled');
}
function openUserBtn() {
    $("button[name='deleteuser']").removeAttr('disabled');
    $("button[name='updateuser']").removeAttr('disabled');
}