$(function () {
    getlist();
    function getlist() {
        var url = "/o2o/shop/getshoplist"
        $.getJSON(url,function (data) {
            $('#user-name').text(data.user.name)
            var shopListHtml = "";
            data.shopList.map(function (value) {
                shopListHtml += '<div class="row"> <div class="col-40">'+value.shopName
                    +'</div><div class="col-40">'+ shopstatus(value.enableStatus)+'</div><div class="col-20">'
                    + shopoperation(value.enableStatus, value.shopId) +'</div></div>'
            })
            $('.shop-wrap').html(shopListHtml);
        })
    }

    function shopstatus(status) {
        if(status == -1){
            return "店铺非法"
        }else if(status == 0){
            return "审核中"
        }else {
            return "审核通过"
        }
    }

    function shopoperation(status, id) {
        if(status == 1){
          return '<a href="/o2o/shop/shopmanage?shopId='+ id +'">进入</a>';
        }else{
            return ""
        }
    }
})