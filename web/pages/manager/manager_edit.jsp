<%@ page import="org.apache.tomcat.util.compat.Jre21Compat" %><%--
  Created by IntelliJ IDEA.
  User: Huawei
  Date: 2023/11/1
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit</title>
</head>
<body>
    <form action="/project1/manager/servlet" method="get">
        <%--通过隐藏域完成servlet区分--%>
        <input type="hidden" name="action" value="${empty requestScope.edit_bookitem ? "add" : "update"}"/>
        <%--通过隐藏域完成对添加或修改后页面位置的跳转--%>
        <input type="hidden" name="pageNo" value="${requestScope.pageNo}"/>
        <input type="hidden" name="id" value="${requestScope.edit_bookitem.id}"/>
        <tr>
            <td>
                <input name="name" type="text" value="${edit_bookitem.name}"/>
            </td>
            <td>
                <input name="price" type="text" value="${edit_bookitem.price}"/>
            </td>
            <td>
                <input name="author" type="text" value="${edit_bookitem.author}"/>
            </td>
            <td>
                <input name="sales" type="text" value="${edit_bookitem.sales}"/>
            </td>
            <td>
                <input name="stock" type="text" value="${edit_bookitem.stock}"/>
            </td>
            <td>
                <button type="submit">提交</button>
            </td>
        </tr>
    </form>
</body>
</html>
