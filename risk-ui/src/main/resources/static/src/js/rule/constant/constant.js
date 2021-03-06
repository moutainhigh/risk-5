
///////////////////////////////////////////////////////////////////////
var preUrl = "/rule/service/constantInfo/";
var layer,entityTable,itemTable,table,active,itemActive;
var conId ,conKey,conType,topIndex,insertOrUpdate='insert';
layui.config({
    base: '/rule/ui/src/js/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    myutil: 'common' //如果 mymod.js 是在根目录，也可以不用设定别名
});
layui.use(['table','form','myutil'], function(){
    /**
     * 设置表单值
     * @param el
     * @param data
     */
    function setFromValues(el, data) {
        for (var p in data) {
            el.find(":input[name='" + p + "']").val(data[p]);
        }
    }
    layer = layui.layer;
    table = layui.table;
    entityTable = layui.table;
    itemTable = layui.table;
    var app = layui.app,
        $ = layui.jquery
    ,form = layui.form;

    var common = layui.myutil;
    //查询构造
    common.business.init("",$("#business_ser"),"businessId_ser");

    //第一个实例
    entityTable.render({
        elem: '#demo'
        ,height: 550
        ,cellMinWidth: 80
        ,url: preUrl + 'page' //数据接口
        // data:[{"conId":1,"entityName":"测试规则","entityDesc":"测试规则引擎","entityIdentify":"testrule","pkgName":"com.sky.testrule","creUserId":1,"creTime":1500522092000,"isEffect":1,"remark":null}]
        ,page: true //开启分页
        ,id:'demos'
        ,cols: [[ //表头
            //{field: 'conId',  event: 'setItem',title: 'ID',sort: true, fixed: 'left'}
             {field: 'conKey',  event: 'setItem',title: '类别'}
            ,{field: 'conName', event: 'setItem', title: '名称'}
            ,{field: 'conCode', event: 'setItem', title: 'code'}
           // ,{field: 'isEffect',  event: 'setItem',title: '状态', sort: true,templet: '#checkboxTpl', unresize: true,fixed: 'right'}
            ,{field: 'conId', title: '操作', fixed: 'right',align:'center', toolbar: '#bar'}
        ]]
    });
    //重载
    //这里以搜索为例
     active = {
        reload: function(){
            //var demoReload = $('#demoReload');

            //执行重载
            table.reload('demos', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    key: $('#entityName_ser').val(),
                    businessId:$("#businessId_ser").val(),
                }
            });
        }
    };

    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    //监听锁定操作
    form.on('checkbox(lockDemo)', function(obj){
        layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
    });
    //监听单元格编辑
    /*entityTable.on('edit(entityTable)', function(obj){
        var value = obj.value //得到修改后的值
            ,data = obj.data //得到所在行所有键值
            ,field = obj.field; //得到字段
        layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
    });*/
    //监听工具条
    entityTable.on('tool(entityTable)', function(obj){
        var data = obj.data;
        console.log(obj);
        if(obj.event === 'detail'){
            layer.msg('ID：'+ data.conId + ' 的查看操作');
        } else if(obj.event === 'del'){
            layer.confirm('是否删除？', function(index){
                $.get(preUrl+'delete?id='+data.conId,function (data) {
                    if(data.code < 0){
                        layer.msg('删除失败，该数据正在被其他数据引用', {icon: 5});
                        layer.close(index);
                    }else{
                        layer.msg("删除成功！");
                        obj.del();
                        itemActive.reload();
                        layer.close(index);
                    }
                },'json');

            });
        } else if(obj.event === 'edit'){
          //  layer.alert('编辑行：<br>'+ JSON.stringify(data))
            edit(data.conId);
        }else if(obj.event === 'setItem'){
            //选择实体对象的id
        	conKey=data.conKey;
        	conType=data.conType;
//            conId = data.conId;
            itemActive.reload(data.conId);
        }
    });


    //第一个实例
    itemTable.render({
        elem: '#itemTable'
        ,height: 550
        ,cellMinWidth: 80
        ,url: '/rule/service/constantItemInfo/getAll/' //数据接口
        // data:[{"conId":1,"entityName":"测试规则","entityDesc":"测试规则引擎","entityIdentify":"testrule","pkgName":"com.sky.testrule","creUserId":1,"creTime":1500522092000,"isEffect":1,"remark":null}]
         ,page: false
        ,id:'itemT'
        ,cols: [[ //表头
            {field: 'conId', title: 'ID',  sort: true, fixed: 'left'}
            ,{field: 'conName', title: '名称'}
            ,{field: 'conCode', title: '标识'}
            ,{field: 'conId', title: '操作', fixed: 'right',align:'center', toolbar: '#item_bar'}
        ]]
    });
    //监听工具条
    itemTable.on('tool(itemTable)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
            layer.msg('ID：'+ data.id + ' 的查看操作');
        } else if(obj.event === 'del2'){
            layer.confirm('是否删除？', function(index){
                $.get(preUrl+'deleteItemConstant?id='+data.conId,function (data) {
                    if(data.code < 0){
                        layer.msg('删除失败，该数据正在被其他数据引用', {icon: 5});
                        layer.close(index);
                    }else{
                        layer.msg("删除成功！");
                        obj.del();
                        layer.close(index);
                    }
                },'json');

            });
        } else if(obj.event === 'edit2'){
            //  layer.alert('编辑行：<br>'+ JSON.stringify(data))
            editItem(data.conId);
        }
    });
    //重载
    //这里以搜索为例
    itemActive = {
        reload: function(conId){
            //var demoReload = $('#demoReload');
            //执行重载
            table.reload('itemT', {
                // page: {
                //     curr: 1 //重新从第 1 页开始
                // }
                where: {
                	conType: conType,
                	conKey: conKey
                }
            });
        }
    };
    //新增
    $("#entity_btn_add").on('click',function () {
        insertOrUpdate='insert';
        $.get('/rule/ui/rule/constant/edit', null, function (form) {
            topIndex =  layer.open({
                type :1,
                title : '新增分类',
                maxmin : true,
                shadeClose : false, // 点击遮罩关闭层
                area : [ '550px', '360px' ],
                content :  form,
                btnAlign: 'c',
                btn: ['保存', '取消'],
                success: function (layero, index) {
                   // setFromValues(layero, result);
                    common.business.init('',$("#businessDiv"));
                }
                ,yes: function (index) {
                    //layedit.sync(editIndex);
                    //触发表单的提交事件
                    $('form.layui-form').find('button[lay-filter=formDemo]').click();
                  //  layer.close(index);
                },
            });
        });
    });
    function  edit(id) {
        insertOrUpdate='update';
        $.get(preUrl+"getInfoById?id="+id,function (data) {
            var result = data.data;
            $.get('/rule/ui/rule/constant/edit', null, function (form) {
                topIndex = layer.open({
                    type :1,
                    title : '修改',
                    maxmin : true,
                    shadeClose : false, // 点击遮罩关闭层
                    area : [ '550px', '360px' ],
                    content :  form,
                    btn: ['保存', '取消'],
                    btnAlign: 'c',
                    success: function (layero, index) {
                        console.log(layero);
                        setFromValues(layero, result);
                        common.business.init(result.businessId,$("#businessDiv"));
                    }
                    ,yes: function (index) {
                        //触发表单的提交事件
                        $('form.layui-form').find('button[lay-filter=formDemo]').click();
                        // layer.close(index);
                    }
                });
            });
        },'json')
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //新增
    $("#entity_item_btn_add").on('click',function () {
        if(conKey == undefined || conKey == ''){
            layer.msg("必须选择一个数据对象哦");
            return ;
        }

        $.get('/rule/ui/rule/constant/itemEdit', null, function (form) {
            topIndex =   layer.open({
                type :1,
                title : '新增',
                maxmin : true,
                shadeClose : false, // 点击遮罩关闭层
                area : [ '550px', '560px' ],
                content :  form,
                btnAlign: 'c',
                btn: ['保存', '取消'],
                success: function (layero, index) {
                    var result = {"conId":conId,"conKey":conKey};
                    setFromValues(layero, result);
                }
                ,yes: function (index) {
                    //layedit.sync(editIndex);
                    //触发表单的提交事件
                    $('form.layui-form').find('button[lay-filter=formDemo]').click();
                   // layer.close(index);
                },
            });
        });
    });
    function  editItem(id) {
        $.get(preUrl+"getInfoById?id="+id,function (data) {
            var result = data.data;
            $.get('/rule/ui/rule/constant/itemEdit', null, function (form) {
                topIndex =  layer.open({
                    type :1,
                    title : '修改',
                    maxmin : true,
                    shadeClose : false, // 点击遮罩关闭层
                    area : [ '550px', '560px' ],
                    content :  form,
                    btn: ['保存', '取消'],
                    btnAlign: 'c',
                    success: function (layero, index) {
                        setFromValues(layero, result);
                        var dataType = result.dataType;

                        layero.find("option:contains('"+dataType+"')").attr("selected",true);
                        console.log( layero.find("#dataId"));
                        var form = layui.form;
                        form.render('select');
                    }
                    ,yes: function (index) {
                        //layedit.sync(editIndex);
                        //触发表单的提交事件
                        $('form.layui-form').find('button[lay-filter=formDemo]').click();
                       // layer.close(index);
                    },
                });
            });
        },'json')
    }
});
