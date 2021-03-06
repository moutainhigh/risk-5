layui.config({
    base: '/rule/ui/src/js/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    myutil: 'common' //如果 mymod.js 是在根目录，也可以不用设定别名
});
layui.use(['table', 'jquery', 'element', 'laytpl','myutil'], function () {
    var $ = layui.jquery;
    var element = layui.element;
    var laytpl = layui.laytpl;
    var procInstId = $("#procInstId_hidden_input", parent.document).val();
    var type = $("#type_hidden_input", parent.document).val();
    var taskId = $("#taskId_hidden_input", parent.document).val();
    layer.load();
    $.ajax({
        cache : true,
        type : "GET",
        url : pathConfig.ruleServicePath+'verification/queryModelLogResult',
        data:{
            "procInstId":procInstId,
            "type":type,
            "taskId":taskId
        },
        timeout : 6000, //超时时间设置，单位毫秒
        async : false,
        error : function(request) {
            layer.msg("查询失败！");
        },
        success : function(data) {
            if(data.code == 0){
                var flag = $("#verfication_layui_btn_div_show", parent.document).val();
                var hitRules = data.data.hitRules;
                var ruleHtml = "模型执行结果："+data.data.msg+" <br>";
                ruleHtml += " <br>";
                if(data.data.modelMsg != null && data.data.modelMsg != ""){
                    ruleHtml += "模型执行信息如下："+" <br>";
                    ruleHtml += data.data.modelMsg+" <br>";
                    ruleHtml += " <br>";
                }
                if(hitRules != null && hitRules.length >0){
                    ruleHtml += "命中如下规则："+" <br>";
                    for(var i=0;i<hitRules.length;i++){
                        ruleHtml += "["+(i+1)+"]: "+hitRules[i].ruleDesc+"(命中次数："+hitRules[i].count+")"+" <br>";
                    }
                }
                $("#modelResult").html(ruleHtml);
                layer.closeAll('loading');
            }
            if(data.code == 1){
                layer.msg("查询失败！");
            }
            layer.closeAll('loading');
        }
    });
});
