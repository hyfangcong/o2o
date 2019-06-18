function changeVerifyCode(img) {
    img.src = "../kaptcha?" + Math.random() * 100
}

/**
 * 获取url中的参数
 * @param name url中的参数的名字
 * @returns {*}
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}