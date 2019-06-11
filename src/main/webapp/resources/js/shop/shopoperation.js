$(document).ready(function () {
    var initUrl = '/o2o/shopadmin/getshopinitinfo';
    var registerShopUrl = '/o2o/shopadmin/management';
    var shopCategoryHtml = ""
    var areaCategoryHtml = ""
    $.getJSON(initUrl, function (data) {
        data.shopCategoryList.map(function (item) {
            shopCategoryHtml += '<option value=' + item.shopCategoryId+ '>' + item.shopCategoryName + '</option>'
        })

        data.areaCategoryList.map(function (item) {
            areaCategoryHtml += '<option value=' + item.areaId + '>' + item.areaName + '</option>'
        })

        $('#shopCategory').html(shopCategoryHtml);
        $('#shopArea').html(areaCategoryHtml);
    })

    $('#submit').click(function () {
        var formData = {
            "shopName" : $('#shopName').val(),
            "phone" : $('#phone').val(),
            "email" : $('#email').val(),
            "shopCategoryId" : $('#shopCategory').val(),
            "areaId" : $('#shopArea').val()
        }

        $.ajax({
            url:registerShopUrl,
            data: formData,
            async:true,
            type:'post',
            success:function (data) {
                alert('test')
            },
        })
    })
})








