<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Huawei
  Date: 2023/11/5
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>client_index</title>
    <script src="${pageContext.request.contextPath}/lib/jquery.js"></script>
    <script type="application/javascript">
        $(function (){
            $("a.DeleteConfirm").click(
                function (){
                    /**
                     * 由于confirm函数可以返回true和false
                     * true：告知浏览器客户选择提交表单
                     * false：告知浏览器客户选择取消提交表单
                     *
                     * js中通过jquery来完成对this(代表被点击判断的元素),parent指代该标签的父标签
                     */
                    return confirm("确定要删除【"+$(this).parent().parent().find("td:eq(1)").text()+"】?");
                }
            )
            /**
             * 通过#SearchPageConfirm完成对单机事件的绑定操作，当用户点击button时完成对对应页数的跳转
             */
            $("#SearchPageConfirm").click(
                function (){
                    // 可以这样写，但是还是会有安全问题，校验过程最好还是放在服务端为好(在服务端page的servlet中进行边界检查)
                    /**
                     var pageNo = $("#SearchPageNo").val()
                     var pageSize = ${requestScope.page.pageTotal}
                     if(pageNo<1 || pageNo>pageSize){
                     confirm("您输入页码有误请重新输入")
                     }else{
                     // 注意location.href可以用来获取访问URL,且href数据可读可写,e.g.:www.baidu.com
                     location.href = "http://localhost:8080/project1/manager/servlet?action=page&pageNo="+pageNo
                     }
                     */
                    var pageNo = $("#SearchPageNo").val()
                    location.href = "http://localhost:8080/project1/client/servlet?action=page&pageNo="+pageNo
                }
            )
        })
    </script>
</head>
<body>
    <div>
        <form action="/project1/client/servlet" method="get">
            <input type="hidden" name="action" value="pageByPrice">
            <%--通过请求中的min和max完成对价格的回显操作--%>
            价格：<input id="min" type="text" name="min" value="${param.min}">元
            <input id="max" type="text" name="max" value="${param.max}">元
            <input type="submit" value="查询">
        </form>
    </div>
    <c:forEach items="${requestScope.page.items}" var="i">
        <div>
            <div>
                <span>书名：</span>
                <span>${i.name}</span>
            </div>
            <div>
                <span>作者：</span>
                <span>${i.author}</span>
            </div>
            <div>
                <span>价格：</span>
                <span>${i.price}</span>
            </div>
            <div>
                <span>销量：</span>
                <span>${i.sales}</span>
            </div>
            <div>
                <span>库存：</span>
                <span>${i.stock}</span>
            </div>
        </div>
        <br/>
    </c:forEach>
    <%--分页标签--%>
    <div>
        <a href="/project1/client/servlet?action=pageByPrice&pageNo=1&min=${requestScope.min}&max=${requestScope.max}">首页</a>
        <%--判断分页是否为第一页,进行效果操作--%>
        <c:if test = "${requestScope.page.pageNo > 1 }">
            <a href="/project1/client/servlet?action=pageByPrice&pageNo=${requestScope.page.pageNo - 1}&min=${requestScope.min}&max=${requestScope.max}">上一页</a>
        </c:if>
        <%--目录的整体效果--%>
        <c:if test="${requestScope.page.pageTotal <= 5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <c:if test="${requestScope.page.pageNo == i}">
                    【${i}】
                </c:if>
                <c:if test="${requestScope.page.pageNo != i}">
                    <a href="/project1/client/servlet?action=pageByPrice&pageNo=${i}&min=${requestScope.min}&max=${requestScope.max}">${i}</a>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${requestScope.page.pageTotal > 5}">
            <c:if test="${requestScope.page.pageNo <= 3}">
                <c:forEach begin="1" end="5" var="i">
                    <c:if test="${requestScope.page.pageNo == i}">
                        【${i}】
                    </c:if>
                    <c:if test="${requestScope.page.pageNo != i}">
                        <a href="/project1/client/servlet?action=pageByPrice&pageNo=${i}&min=${requestScope.min}&max=${requestScope.max}">${i}</a>
                    </c:if>
                </c:forEach>
            </c:if>
            <c:if test="${requestScope.page.pageNo >= requestScope.page.pageTotal-2}">
                <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                    <c:if test="${requestScope.page.pageNo == i}">
                        【${i}】
                    </c:if>
                    <c:if test="${requestScope.page.pageNo != i}">
                        <a href="/project1/client/servlet?action=pageByPrice&pageNo=${i}&min=${requestScope.min}&max=${requestScope.max}">${i}</a>
                    </c:if>
                </c:forEach>
            </c:if>
            <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal-2 and requestScope.page.pageNo > 3}">
                <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
                    <c:if test="${requestScope.page.pageNo == i}">
                        【${i}】
                    </c:if>
                    <c:if test="${requestScope.page.pageNo != i}">
                        <a href="/project1/client/servlet?action=pageByPrice&pageNo=${i}&min=${requestScope.min}&max=${requestScope.max}">${i}</a>
                    </c:if>
                </c:forEach>
            </c:if>
        </c:if>
        <%--判断分页是否为尾页，进行效果操作--%>
        <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
            <a href="/project1/client/servlet?action=pageByPrice&pageNo=${requestScope.page.pageNo + 1}&min=${requestScope.min}&max=${requestScope.max}">下一页</a>
        </c:if>
        <a href="/project1/client/servlet?action=pageByPrice&pageNo=${requestScope.page.pageTotal}&min=${requestScope.min}&max=${requestScope.max}">尾页</a>
        共${requestScope.page.pageTotal}页,${requestScope.page.pageTotalCount}条记录
        到第<input id="SearchPageNo"/>页
        <input id="SearchPageConfirm" type="button" value="确认">
    </div>
</body>
</html>
