function getPlace(zip) {
    //代码通过测试window.XMLHttpRequest可以确定是否支持XMLHttpRequest,如果测试结果是null(求值为false)，则可以肯定是IE5或IE6，并创建XMLHTTP对象
    if (window.XMLHttpRequest()) {
        var xhr = new XMLHttpRequest();
    }
    else {
        var xhr = new ActiveXObject("Microsoft.XMLHTTP")
    }

    //未命名的函数称为闭包，readyState=4意味着响应已完成,status=200说明成功完成请求
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var result = xhr.responseText;
            var place = result.split(',');
            if (document.getElementById("city").value == "") {
                document.getElementById("city").value = place[0];
            }
            if (document.getElementById("state").value == "") {
                document.getElementById("state").value = place[1];
            }
        }
        xhr.open("GET", "getCityState.php?zip=" + zip);
        xhr.send(null);
    }
}

function getPlace1(zip) {
    dojo.io.bind({
        //URL的值是向其发送请求的服务器的URL
        url: "getCityState.php?zip=" + zip,
        //load属性是使用服务器返回的数据作为请求结果的函数
        //对于load和error函数来说，使用直接定义的匿名函数
        load: function (type, data, evt) {
            var place = data.split(',');
            if (dojo.byId("city").value = "") {
                dojo.byId("city").value = place[0];
            }
            if (dojo.byId("state").value = "") {
                dojo.byId("state").value = place[1];
            }

        },
        //error属性的值是在处理请求过程中出现错误时调用的函数
        error: function (type, data, evt) {
            alert("Error in request,return data: " + data);
        },
        method: "GET",
        //mimetype是返回数据的MIME类型
        mimetype: "text/plain"

    });
}