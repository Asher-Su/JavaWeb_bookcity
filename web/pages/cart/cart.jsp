<%--
  Created by IntelliJ IDEA.
  User: Huawei
  Date: 2023/11/17
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>cart</title>
    <script src="../../lib/jquery.js"></script>
    <script type="application/javascript">
        $(function (){
            $("a.DeleteButton").click(function (){
                return confirm("你是否要从购物车中删除【"+$(this).parent().parent().find("td:eq(0)").text()+"】?")
            })
            $("a.deleteAll").click(function (){
                return confirm("是否清空购物车中所有内容?")
            })
            // 通过change函数当改变输入框中内容时调用此函数
            $("#count").change(function (){
                if(confirm("是否更改购物车中【"+$(this).parent().parent().find("td:eq(0)").text()+"】物品数量修改为"+$(this).val()+"?")){
                    // 如果用于点击同意更改数量
                    location.href = "http://localhost:8080/project1/cartServlet?action=updateCount&count="+this.value+"&id="+$(this).attr("book_id")

                }else{
                    // 如果用户不同意更改数量
                    this.value= this.defaultValue // defaultValue表示输出表单项中的默认值
                }
            })
        })
    </script>
</head>
<body>
<c:if test="${not empty sessionScope.cart.items}">
    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <%--完成对应的sessionScope中保存的cart内容中items(map)的遍历--%>
        <c:forEach items="${sessionScope.cart.items}" var="item">
            <tr>
                <%--为value原因是items是Map类型我们只需要取得其中的value就可以了--%>
                <td>${item.value.name}</td>
                <td><input book_id="${item.value.id}" id="count" type="text" value="${item.value.count}"/></td>
                <td>${item.value.price}</td>
                <td>${item.value.totalPrice}</td>
                <td><a class="DeleteButton" href="http://localhost:8080/project1/cartServlet?action=deleteCartItem&Itemid=${item.value.id}">删除</a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty sessionScope.cart.items}">
    <h1>亲，购物车为空！</h1>
</c:if>
    <c:if test="${not empty sessionScope.cart.items}">
        <div>
            购物车中总共有${sessionScope.cart.totalCount};总金额为${sessionScope.cart.totalPrice}
            <a class="deleteAll" href="http://localhost:8080/project1/cartServlet?action=clear">清空购物车</a>
            <a href="http://localhost:8080/project1/orderServlet?action=createOrder">去结账</a>
        </div>
    </c:if>

</body>
</html>
