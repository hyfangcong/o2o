$(function() {
    var shopId = getQueryString("shopId");
    var listUrl = '/o2o/shop/getproductlistbyshopid?shopId='
        + shopId;
    var changeStatusUrl = '/o2o/shop/changeproductstatus';

    function getList() {
        $.getJSON(listUrl, function(data) {
            if (data.success) {
                var productList = data.productList;
                var tempHtml = '';
                productList.map(function(item, index) {
                    var textOp = "下架";
                    var contraryStatus = 0;
                    if (item.enableStatus == 0) {
                        textOp = "上架";
                        contraryStatus = 1;
                    } else {
                        contraryStatus = 0;
                    }
                    tempHtml += '' + '<div class="row row-product">'
                        + '<div class="col-40">'
                        + item.productName
                        + '</div>'
                        + '<div class="col-20">'
                        + item.priority
                        + '</div>'
                        + '<div class="col-40">'
                        + '<a href="#" class="edit" data-id="'
                        + item.productId
                        + '" data-status="'
                        + item.enableStatus
                        + '">编辑</a>'
                        + '<a href="#" class="delete" data-id="'
                        + item.productId
                        + '" data-status="'
                        + contraryStatus
                        + '">'
                        + textOp
                        + '</a>'
                        + '<a href="#" class="preview" data-id="'
                        + item.productId
                        + '" data-status="'
                        + item.enableStatus
                        + '">预览</a>'
                        + '</div>'
                        + '</div>';
                });
                $('.product-wrap').html(tempHtml);
            }
        });
    }

    getList();

    function deleteItem(id, enableStatus) {
        var product = {};
        product.productId = id;
        product.enableStatus = enableStatus;
        $.confirm('确定么?', function() {
            $.ajax({
                url : changeStatusUrl,
                type : 'POST',
                data : JSON.stringify(product),
                contentType:"application/json",
                dataType : 'json',
                success : function(data) {
                    if (data.success) {
                        $.toast('操作成功！');
                        getList();
                    } else {
                        $.toast('操作失败！');
                    }
                }
            });
        });
    }

    $('.product-wrap')
        .on(
            'click',
            'a',
            function(e) {
                var target = $(e.currentTarget);
                if (target.hasClass('edit')) {
                    window.location.href = '/o2o/shop/productedit?productId='
                        + e.currentTarget.dataset.id + '&shopId='+shopId;
                } else if (target.hasClass('delete')) {
                    deleteItem(e.currentTarget.dataset.id,
                        e.currentTarget.dataset.status);
                } else if (target.hasClass('preview')) {
                    window.location.href = '/myo2o/dfronten/productdetail?productId='
                        + e.currentTarget.dataset.id;
                }
            });

    $('#new').click(function() {
        window.location.href = '/o2o/shop/productedit?shopId='+shopId;
    });
    $('#back').click(function() {
        window.location.href = '/o2o/shop/shopmanage?shopId='+shopId;
    });
});