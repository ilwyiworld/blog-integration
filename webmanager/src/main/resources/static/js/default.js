/****
 * 公用引入js
 */
var global = {
    setCookie: function (name, value) {
        // 写cookies
        var Days = 30;
        var exp = new Date();
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
        document.cookie = name + "=" + escape(value) + ";path=/;expires=" + exp.toGMTString();
    },
    getCookie: function (name) {
        // 读取cookies
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
        if (arr = document.cookie.match(reg)) return unescape(arr[2]);
        else return null;
    },
    delCookie: function (name) {
        // 删除cookies
        var exp = new Date();
        exp.setTime(exp.getTime() - 10000);
        var cval = global.getCookie(name);
        if (cval != null) document.cookie = name + "=" + cval + ";path=/;expires=" + exp.toGMTString();
    }
};

global.appRoot ="/"; //global.getCookie("contextPath");
global.loginUser = global.getCookie("loginUser");
global.code = {};
global.code.success = 1;
global.code.error = 0;
global.code.notLogin = -2;

/** 获取地址栏指定参数 * */
function getUrlParam(key){
	var url = window.location.search, param = "";  
	if (url !=="" && url.indexOf("?") > -1) {
		var strs = url.substr(1).split("&");
		for (i = 0; i < strs.length; i++) {
			var arr = strs[i].split("=");
			if(key == arr[0]){
				param = arr[1];
			}
		}
	}
	return param;
}

function parseNull(str){
	if(typeof(str) == "undefined" || "undefined" == str || null == str || "null" == str){
		return "";
	}
	return str;
}

//下载磁盘文件
function downloadFile(fullPath){
	var $ = layui.jquery;
	var url = global.appRoot+"/UtilServlet";
    /**
     * 使用form表单来发送请求
     */
    var form=$("<form>");//定义一个form表单
    form.attr("style","display:none");
    form.attr("target","");
    form.attr("method","get");  //请求类型
    form.attr("action",url);   //请求地址
    $("body").append(form);	//将表单放置在web中
    /*
     * input标签主要用来传递请求所需的参数：
     * 1.name属性是传递请求所需的参数名.
     * 2.value属性是传递请求所需的参数值.
     * 3.当为get类型时，请求所需的参数用input标签来传递，直接写在URL后面是无效的。
     * 4.当为post类型时，queryString参数直接写在URL后面，formData参数则用input标签传递
     * 5.有多少数据则使用多少input标签	
     */
    var input1=$("<input>");
    input1.attr("type","hidden");
    input1.attr("name","fullPath");
    input1.attr("value",fullPath);
    
    var input2=$("<input>");
    input2.attr("type","hidden");
    input2.attr("name","method");
    input2.attr("value","downloadFile");
    form.append(input1);
    form.append(input2);
    form.submit();			//表单提交
}

/** String 的format方法，自C#中来 * */
String.prototype.format = function(args) {
    var result = this;
    if (args.length > 0) {    
        if (args.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                if(args[key]!=undefined){
                    var reg = new RegExp("({" + key + "})", "g");
                    result = result.replace(reg, args[key]);
                }
            }
        }
        else {
            for (var i = 0; i < args.length; i++) {
                if (args[i] != undefined) {
					var reg= new RegExp("({)" + i + "(})", "g");
                    result = result.replace(reg, args[i]);
                }
            }
        }
    }
    return result;
};

/** String 的startWith方法 * */
String.prototype.startWith=function(str){  
    if(str == null || str == "" || this == null || this == undefined || this.length == 0 || str.length > this.length )  
      return false;  
    if(this.substr(0,str.length)==str)  
      return true;  
    else  
      return false;  
    return true;  
};

/** 对Date的扩展，将 Date 转化为指定格式的String * */
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "H+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt))
    	fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    	if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

getFormArray = function(frmID) {
	var frmID = document.getElementById(frmID);
	var i, queryString = {};
	var item;
	// for each form's object
	var itemValue; // store each form object's value
	var itemName;

	for (i = 0; i < frmID.length; i++) {
		item = frmID[i]; // get form's each object
		if (item.id != '') {
			if (item.type == 'select') {
				itemValue = item.selectedIndex == -1 ? ""
						: item.options[item.selectedIndex].value;
			} else if (item.type == 'checkbox' || item.type == 'radio') {
				if (item.checked == false) {
					continue;
				}
				itemValue += item.value;

			} else if (item.type == 'button' || item.type == 'submit'
					|| item.type == 'reset' || item.type == 'image') {
				continue;
			} else {
				itemValue = item.value;
			}
			itemValue = encodeURIComponent(itemValue);
			itemName = item.id;
			queryString[itemName] = itemValue;
		}
	}
	return queryString;
}

//将form中的值转换为键值对。使用name
function getFormJson(frm) {
    var o = {};
    var a = $(frm).serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });

    return o;
}

// 日期加减
function addDate(date,days){ 
    var d = new Date(date); 
    d.setDate(d.getDate() + days); 
    var m = d.getMonth() + 1;
    if (m.length != 2) {
    	m = '0' + m;
    } 
    var day = d.getDate().toString();
    if (day.length != 2) {
    	day = '0' + day;
    }
    return d.getFullYear() + '-' + m + '-' + day; 
}