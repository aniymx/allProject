<%--
  Created by IntelliJ IDEA.
  User: YaYa
  Date: 2018/10/24
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script src="js/jquery-3.3.1.js"></script>
    <script>
        $(function () {

            /*

                        $.get("findProvinceServlet", {}, function (data) {
                           // alert(data)
                            var province = $("#province");
                            for (var i = 1;i<data.length;i++){
                                var option = "<option name='"+i+"'>"+this.name+"</option>";
                                province.append(option);
                            }
                         /!*   data.each(function () {
                                var option = "<option name='"+this.id+"'>"+this.name+"</option>";
                                //var option = "<option name='"+this.id+"'>"+this.name+"</option>";
                                province.append(option);

                            })*!/


                        })
            */

            $.get("findProvinceServlet", {}, function (data) {
                //[{"id":1,"name":"北京"},{"id":2,"name":"上海"},{"id":3,"name":"广州"},{"id":4,"name":"陕西"}]

                //1.获取select
                var province = $("#province");
                //2.遍历json数组
                $(data).each(function () {
                    //3.创建<option>
                    var option = "<option value='" + this.id + "'>" + this.name + "</option>";

                    //4.调用select的append追加option
                    province.append(option);
                });


            });
            $("#province").change(function () {

                var id = $("#province").val();
               $.get("findCityServlet",{"id":id},function (data) {

                  var city =  $("#city");
                  city.empty();
                   $(data).each(function () {

                       var option = "<option value='" + this.cid + "'>" + this.cname + "</option>";
                       city.append(option);
                   })


               },"json")


            })
        });

        $(function () {


        })
    </script>
</head>
<body>
<select id="province">
    <option>--请选择省份--</option>

</select>
<select id="city">--请选择省份--</select>
</body>
</html>
