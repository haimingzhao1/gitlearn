var url;

// 页面加载
$(function () {
    $("#dg").datagrid({
        url: 'list',
        queryParams: {},
        title: "人员列表",
        rownumbers: true,
        fit: true,
        // singleSelect:true,
        toolbar: "#tb",
        collapsible: true,
        pagination: true,
        pageSize: 5,
        pageList: [5, 10],
        columns: [[
            {field: 'personid', title: '人员编号', width: 50, align: 'center', halign: 'center', hidden: 'true'},
            {field: 'personname', title: '人员名称', width: 200, align: 'center', halign: 'center'},
            {field: 'personage', title: '人员年龄', width: 200, align: 'right', halign: 'center'}
        ]]
    });
});

// 【加载全部】按钮押下处理
var loadAll = function () {
    // 查询条件还原为默认状态
    $('#searchcondition').combobox('setValue', 'personname');
    $('#searchcontent').val("");

    // 表格重新加载
    var param = {};
    $("#dg").datagrid('load', param);
};

// 【搜索】按钮押下处理
var queryAction = function () {
    var param = {
        searchcondition: $('#searchcondition').combobox('getValue'),
        searchcontent: $('#searchcontent').val()
    };

    $("#dg").datagrid('load', param);
};

// 重置表单内容
var resetValue = function () {
    $('#fm')[0].reset();
};

// 打开添加对话框
var openAddDialog = function () {
    resetValue();
    $('#dlg').dialog({
        modal: true,
        title: '添加人员信息'
    });
    $('#dlg').dialog("open");
    url = "person";
};

// 新增处理
var saveAction = function () {
    var obj = JSON.stringify({'personname':$("#personname").val(),'personage':$("#personage").val()});
    if ($("#personname").val()!='' && $("#personage").val()!='') {
    $.ajax({
        url:url,
        type:"POST",
        data: obj,
        contentType: 'application/json;charset=utf-8',
        success: function (result) {
            // var result = eval('(' + result + ')');
            if (result.status) {
                $.messager.alert("系统提示", "保存成功！");
                resetValue();
                $("#dlg").dialog("close");
                $("#dg").datagrid("reload");
            } else {
                $.messager.alert("系统提示", "保存失败！");
                return;
            }
        }
    });
    }else{
        alert("请输入用户名或年龄！！")
    }
};

// 关闭对话框
var closeDialog = function () {
    $("#dlg").dialog("close");
    resetValue();
};

// 打开编辑对话框
var openModifyDialog = function () {
    var selectedRows = $("#dg").datagrid("getSelections");

    if (selectedRows.length != 1) {
        $.messager.alert("系统提示", "请选择一条要编辑的数据！");
        return;
    }

    var row = selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle", "编辑人员信息");
    $("#fm").form("load", row);
    url = "person?personid=" + row.personid;
};

// 删除处理
var deleteAction = function () {
    var selectedRows = $("#dg").datagrid("getSelections");

    if (selectedRows.length == 0) {
        $.messager.alert("系统提示", "请选择要删除的数据");
        return;
    }

    $.messager.confirm("系统提示", "您确定要删除这<font color=red>" + selectedRows.length + "</font>条数据吗？", function (r) {
        if (r) {
            var num = [];
            for (var i = 0; i < selectedRows.length; i++) {
                num[i] = selectedRows[i].personid;
            }
            var data = JSON.stringify(num);
            $.ajax({
                url: "person",
                type: "DELETE",
                data: data,
                contentType: 'application/json;charset=utf-8',
                success: function (result) {
                    if (result.status) {
                        $.messager.alert("系统提示", "数据已成功删除！");
                        $("#dg").datagrid("reload");
                    } else {
                        $.messager.alert("系统提示", "数据删除失败，请联系工作人员！");
                    }
                }
            });
        }
    });
};





