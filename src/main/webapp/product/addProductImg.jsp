<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="entity.ProductImg" %>

<%
    ProductImg productImg = (ProductImg) request.getAttribute("productImg");
%>

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>產品圖片新增</title>

    <style>
        table#table-1 {
            background-color: #CCCCFF;
            border: 2px solid black;
            text-align: center;
        }
        table#table-1 h4 {
            color: red;
            display: block;
            margin-bottom: 1px;
        }
        h4 {
            color: blue;
            display: inline;
        }
    </style>

    <style>
        table {
            width: 450px;
            background-color: white;
            margin-top: 1px;
            margin-bottom: 1px;
        }
        table, th, td {
            border: 0px solid #CCCCFF;
        }
        th, td {
            padding: 1px;
        }
    </style>

</head>
<body bgcolor='white'>

    <table id="table-1">
        <tr><td>
            <h3>產品圖片新增</h3></td><td>
            <h4><a href="<%=request.getContextPath()%>/product/select_page.jsp">回首頁</a></h4>
        </td></tr>
    </table>

    <h3>圖片新增:</h3>

    <%-- 錯誤表列 --%>
    <c:if test="${not empty errorMsgs}">
        <font style="color:red">請修正以下錯誤:</font>
        <ul>
            <c:forEach var="message" items="${errorMsgs}">
                <li style="color:red">${message}</li>
            </c:forEach>
        </ul>
    </c:if>

    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product.do" enctype="multipart/form-data" name="form1">
        <table>

            <tr>
                <td>產品編號:</td>
                <td><input type="TEXT" name="productNo" value="<%= (productImg==null)? "product no" : productImg.getProductNo()%>" size="45"/></td>
            </tr>
            <tr>
                <td>產品圖片:</td>
                <td><input type="file" name="productImg"></td>
            </tr>

        </table>
        <br>
        <input type="hidden" name="action" value="insert">
        <input type="submit" value="送出新增">
    </FORM>

</body>
</html>