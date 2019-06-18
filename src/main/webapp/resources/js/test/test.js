$(function () {
    var shopId = getQueryString("shopId");
    var isEdit = shopId?true:false;
    var initUrl = '/o2o/shop/getshopinitinfo';
    var registerShopUrl = '/o2o/shop/doregister';
    var shopInfoByIdUrl = '/o2o/shop/getshopbyid';
    var editShopUrl = '/o2o/shop/doedit';
    var shopCategoryHtml = ""
    var areaCategoryHtml = ""

    if(isEdit){
        getshopinfobyid()
    }else{
        getshopinitinfo()
    }

    function getshopinfobyid(){
        $.getJSON(shopInfoByIdUrl, {"shopId" : shopId}, function(data){
            if(!data.success){
                //todo
                //跳转到错误页面
                //return
            }
            var shop = data.shop;
            $('#shopName').val(shop.shopName)
            $('#phone').val(shop.phone)
            $('#email').val(shop.email)
            shopCategoryHtml = '<option value=' + shop.shopCategory.shopCategoryId + '>' + shop.shopCategory.shopCategoryName+
                '</option>'
            $('#shopCategory').html(shopCategoryHtml)
            $('#shopCategory').attr('disabled', 'disabled')

            data.areaList.map(function (item) {
                if(shop.area.areaId == item.areaId){
                    areaCategoryHtml += '<option selected = selected value =' +item.areaId+ '>' + item.areaName+ '</option>'
                }else{
                    areaCategoryHtml += '<option value =' +item.areaId+ '>' + item.areaName+ '</option>'
                }
            })
            $('#shopArea').html(areaCategoryHtml)

        })
    }

    function getshopinitinfo(){
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
    }

    $('#submit').click(function () {
        $('.item-content').map(function (index,value) {
            var phone = $(value).find('.phone').val()
            if(phone){
                alert(phone)
            }
        })
    })

    // $('#submit').click(function () {
    //     var formData = new FormData();
    //     var shop = {
    //         "shopName" : $('#shopName').val(),
    //         "phone" : $('#phone').val(),
    //         "email" : $('#email').val(),
    //         "shopCategoryId" : $('#shopCategory').val(),
    //         "areaId" : $('#shopArea').val()
    //     }
    //
    //     var verifyCode = $('#kaptcha').val()
    //     if(!verifyCode){
    //         alert("请输入验证码")
    //         return
    //     }
    //     if(isEdit){
    //         shop['shopId'] = shopId
    //     }
    //     formData.append("verifyCode", verifyCode)
    //     formData.append("shopStr", JSON.stringify(shop))
    //     formData.append("shopImg", $('#smallImage')[0].files[0])
    //
    //     $.ajax({
    //         type:'post',
    //         url:(isEdit? editShopUrl : registerShopUrl),
    //         data: formData,
    //         dataType:'json',
    //         contentType:false,
    //         processData:false,
    //         success:function (data) {
    //             if(false){
    //                 alert('success')
    //             }else{
    //                 alert(data.msg)
    //             }
    //         }
    //     })

        // $.post(registerShopUrl, formData, function(data){
        //     alert(data.msg)
        // })
    // })
})










