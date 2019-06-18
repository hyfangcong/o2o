$(function () {
    var shopId = getQueryString("shopId")
    getProductCategoryList();
    function getProductCategoryList() {
        var url = "/o2o/shop/getproductcategorylist"
        var html = ""
        $.getJSON(url,{"shopId":shopId},function (data) {
            if(!data.success){
                //todo
                //跳转到错误页面
                alert(data.msg)
            }else{
                data.productCategoryList.map(function (value) {
                    html += '<div class="row row-product-category now">' +
                        '<div class="col-33">'+ value.productCategoryName+'</div>' +
                        '<div class="col-33">'+ value.priority + '</div>' +
                        '<div class="col-33"> <a class="button delete" href="#" data-id="'+value.productCategoryId+'">删除</a></div></div>'
                })
            }
            $('.category-wrap').html(html);
        })
    }

    $('#new').click(function () {
        var tempHtml = '<div class="row row-product-category temp">' +
            '<div class="col-33"> <input class="category-input category-name" type="text" placeholder="类别名称"></div>' +
            '<div class="col-33"> <input class="category-input priority" type="text" placeholder="优先级"></div>' +
            '<div class="col-33"> <a class="button delete" href="#">删除</a></div></div>'
        $('.category-wrap').append(tempHtml)
    })


    $('.category-wrap').on('click', '.row-product-category.now .delete',
        function (e) {
            var url = "/o2o/shop/removeproductcategory"
            var target = e.currentTarget;
            $.confirm('确定删除么?', function () {
                $.ajax({
                    url: url,
                    type: 'POST',
                    data: {
                        productCategoryId: target.dataset.id
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            alert('删除成功！');
                            getProductCategoryList();
                        } else {
                            alert('删除失败！');
                        }
                    }
                });
            });
        });

    $('.category-wrap').on('click', '.row.row-product-category.temp .delete',
        function (e) {
            console.log($(this).parent().parent());
            $(this).parent().parent().remove();

        });

    $('#submit').click(function () {
        var url = "/o2o/shop/addproductcategories"
        var productCategoryList = [];
        $('.temp').map(function (index, item) {
            var tempObj = {}
            tempObj.productCategoryName = $(item).find('.category-name').val()
            tempObj.priority = $(item).find('.priority').val()
            tempObj.shopId = shopId
            if(tempObj.productCategoryName && tempObj.priority){
                productCategoryList.push(tempObj)
            }
        })

        $.ajax({
            type:"post",

            data:JSON.stringify(productCategoryList),
            url:url,
            contentType:"application/json",
            success:function(data){
                if(data.success){
                    alert("提交成功")
                    getProductCategoryList()
                }else(
                    alert("提交失败")
                )
            }
        })
    })

})