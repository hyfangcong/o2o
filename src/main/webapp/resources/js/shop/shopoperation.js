$(document).ready(function () {
    var initUrl = '/o2o/shop/getshopinitinfo';
    var registerShopUrl = '/o2o/shop/doregister';
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
        var formData = new FormData();
        var shop = {
            "shopName" : $('#shopName').val(),
            "phone" : $('#phone').val(),
            "email" : $('#email').val(),
            "shopCategoryId" : $('#shopCategory').val(),
            "areaId" : $('#shopArea').val()
        }

        formData.append("shopStr", JSON.stringify(shop))
        formData.append("shopImg", $('#smallImage')[0].files[0])

        $.ajax({
            type:'post',
            url:registerShopUrl,
            data: formData,
            dataType:'json',
            contentType:false,
            processData:false,
            success:function (data) {
                if(false){
                    alert('success')
                }else{
                    alert(data.msg)
                }
            }
        })

        // $.post(registerShopUrl, formData, function(data){
        //     alert(data.msg)
        // })
    })
})








