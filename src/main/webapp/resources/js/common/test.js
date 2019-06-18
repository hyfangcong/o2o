$('#submit').click(function () {
    var shop = {}
    shop.shopName = $('#shopName').val();
    shop.phone = $('#phone').val();
    shop.email = $('#email').val();
    shop.shopCategoryId = $('#shopCategory').find('option').not(function () {
        return !this.selected;
    });
    shop.areaId = $('#shopArea').find('option').not(function () {
        return !this.selected;
    });

    $.post(registerShopUrl, JSON.stringify(shop), function (data) {
        if(data.success){
            alert("注册成功")
        }else{
            alert("注册失败")
        }
    })
})