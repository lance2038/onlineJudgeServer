/**
 *
 * @param url 访问地址
 * @param params 参数
 * @param successfuc 成功回调函数
 * @param errorful 失败回调函数
 * @param isasync 是否异步
 */
function ajaxJsonRequest(url, params, successfuc, errorful, isasync) {
    var async = true;
    if (null != isasync || "" != isasync) {
        async = isasync;
    }
    var param = {};
    if (params != undefined) {
        param = params;
    }
    if (null == successfuc) {
        successfuc = "defaultfuc";
    }
    if (null == errorful) {
        errorful = "defaultfuc";
    }
    jQuery.ajax
    ({
        async: async,
        type: "POST",
        url: url,
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(param),
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("__REQUEST_TYPE", "AJAX_REQUEST");
        },
        success: eval(successfuc),
        error: eval(errorful)
    });
}

function defaultfuc(data) {
    console.log(data)
}

