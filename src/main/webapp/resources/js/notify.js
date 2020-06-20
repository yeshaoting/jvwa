/**
 * 提示通知插件
 */

window.Notify = window.Notify || {

    success: function (text, layout, timeout) {
        var type = "success";
        this.notify(text, type, layout, timeout);
    },

    error: function (text, layout) {
        var type = "error";
        this.notify(text, type, layout, false);
    },

    info: function (text, layout, timeout) {
        var type = "information";
        timeout = timeout ? timeout : 3000;
        this.notify(text, type, layout, timeout);
    },

    warn: function (text, layout, timeout) {
        var type = "warning";
        timeout = timeout ? timeout : 4000;
        this.notify(text, type, layout, timeout);
    },

    notify: function (text, type, layout, timeout) {
        if (!timeout && timeout != false) {
            timeout = 2000;
        }

        new Noty({
            text: text ? text : "无通知消息内容!",
            type: type ? type : 'alert',
            layout: layout ? layout : 'topCenter',
            timeout: timeout ? timeout : false,
            theme: 'metroui',
            progressBar: true,
            maxVisible: 5,
        }).show();

    }
};
