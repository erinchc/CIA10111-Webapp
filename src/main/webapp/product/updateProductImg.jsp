<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="entity.ProductImg" %>

<%
    ProductImg productImg = (ProductImg) request.getAttribute("productImg");
%>
<%=productImg==null%>


<!DOCTYPE html>

<html>

<head>
    <meta charset="UTF-8">
    <title>修改資料</title>
</head>


<body>

<h3>---修改產品圖片---</h3>
<br>

<%-- 錯誤訊息列表呈現處 --%>

<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>


<form method="post" action="<%=request.getContextPath()%>/product.do" enctype="multipart/form-data">
    <table>

        <tr>
            <td>圖片編號:</td>
            <td><%=productImg.getProductImgNo()%></td>
        </tr>

        <tr>
            <td>產品編號:</td>
            <td><input type="text" name="productNo" value="<%=productImg.getProductNo()%>"></td>
        </tr>

        <tr>
            <td>產品圖片:</td>
            <td><input type="file" name="productImg"></td>
        </tr>

    </table>
    <br>
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="productImgNo" value="<%=productImg.getProductImgNo()%>">
    <input type="submit" value="送出修改">

</form>


</body>
</html>
