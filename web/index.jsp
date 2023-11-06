<%--
  Created by IntelliJ IDEA.
  User: Huawei
  Date: 2023/11/5
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
    <%--通过jsp:forward完成对首页的重定向，使得用户输入域名直接可以跳转到对应输出页面--%>
    <jsp:forward page="/client/servlet?action=page"></jsp:forward>
</body>
</html>
