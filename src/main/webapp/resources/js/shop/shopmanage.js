$(function () {
    var shopId = getQueryString("shopId")
    $('#shopInfo').attr("href","/o2o/shop/edit?shopId=" + shopId)
    $('#productCategoryManage').attr("href","/o2o/shop/productcategorymanage?shopId=" + shopId)
    $('#productManage').attr("href","/o2o/shop/productmanage?shopId=" + shopId)
})