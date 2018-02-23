layui.config({
    base: '/rule/ui/src/js/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    sceneUtil: 'decision', //如果 mymod.js 是在根目录，也可以不用设定别名
    common:'common',
});
layui.use(['table', 'jquery', 'laydate', 'form','laytpl'], function () {
    var $ = layui.jquery;
    var form = layui.form;

    form.on('submit(save_auto)', function (data) {
        console.log("save_auto");
        console.log(data);
        $.ajax({
            cache: true,
            type: "POST",
            url: '/rule/service/actProcRelease/scene/variable/init/auto',
            data: data.field,// 你的formid
            async: false,
            success: function (data) {
                layer.msg(data.msg);
            }
        });
        return false;
    });
    form.on('submit(verfication)', function (data) {
        console.log(data);
        layer.load();
        $.ajax({
            cache: true,
            type: "POST",
            url: '/rule/service/verification/createSingleVerficationTask',
            data: data.field,// 你的formid
            async: false,
            timeout: 60000,
            error : function(request) {
                layer.closeAll('loading');
                layer.msg("网络异常!");
            },
            success: function (data) {
                layer.closeAll('loading');
                layer.msg(data.msg);
            }
        });
        return false;
    });

});