layui.define(['element', 'layer', 'form'], function (exports) {
    var form = layui.form;
    var $ = layui.$;
    //自定义验证
    form.verify({
        passWord: [/^[\S]{6,12}$/, '密码必须6到12位'],
        account: function (value) {
            if (value.length <= 0 || value.length > 10) {
                return "账号必须1到10位"
            }
            var reg = /^[a-zA-Z0-9]*$/;
            if (!reg.test(value)) {
                return "账号只能为英文或数字";
            }
        },
    });

    //监听登陆提交
    form.on('submit(login)', function (formdata) {
        $.ajax({
            url: "/login",
            type: "post",
            cache: false,
            dataType: "json",
            data : formdata.field,
            success:function(info){
                if(info.result=="0"){
                    layer.alert("用户名或密码不正确");
                    $('#loginForm')[0].reset();			//清除表单数据
                }else{
                    window.location.href="/templates/main.html";
                }
            },
        });
        return false;
    });
    //检测键盘按下
    $('body').keydown(function (e) {
        if (e.keyCode == 13) {  //Enter键
            if ($('#layer-login').length <= 0) {
                login();
            } else {
                $('button[lay-filter=login]').click();
            }
        }
    });

    $('.enter').on('click', login);

    function login() {
        var addBoxIndex = -1;
        if (addBoxIndex !== -1)
            return;
        //本表单通过ajax加载 --以模板的形式，当然你也可以直接写在页面上读取
        $.get('templates/login.html',null,function(form) {
            layer.open({
                id: 'layer-login',
                type: 1,
                title: false,
                shade: 0.4,
                shadeClose: true,
                area: ['480px', '270px'],
                closeBtn: 0,
                anim: 1,
                skin: 'pm-layer-login',
                content: form,
                success : function(layero,index) {
                    layui.form.render('checkbox');
                }
            });
        });
    }
    exports('index', {});
});

