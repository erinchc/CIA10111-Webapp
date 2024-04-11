<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="entity.ProductImg" %>
<%@ page import="com.product.model.ProductImgService" %>


<%
    ProductImgService imgSvc = new ProductImgService();
    List<ProductImg> list = imgSvc.getAll();
    pageContext.setAttribute("list", list);
%>

<html>
<head>
    <title>All images of Products</title>
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
            width: 800px;
            background-color: white;
            margin-top: 5px;
            margin-bottom: 5px;
        }
        table, th, td {
            border: 1px solid #CCCCFF;
        }
        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>
</head>
<body>
<table id="table-1">
    <tr><td>
        <h3>所有產品圖片</h3>
        <h4><a href="<%=request.getContextPath()%>/product/select_page.jsp">回首頁</a></h4>
    </td></tr>
</table>
<table>
    <tr>
        <th>圖片編號</th>
        <th>產品編號</th>
        <th>產品圖片</th>
        <th>修改</th>
        <th>刪除</th>
    </tr>


    <c:forEach var="productImg" items="${list}">

        <tr>
            <td>${productImg.productImgNo}</td>
            <td>${productImg.productNo}</td>

            <c:if test="${productImg.productImg == null}">
                <td>無圖片</td>
            </c:if>
            <c:if test="${productImg.productImg != null}">
                <td><img src="<%=request.getContextPath()%>/ImgReader?productImgNo=${productImg.productImgNo}"></td>
            </c:if>

            <td>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product.do" style="margin-bottom: 0px;">
                    <input type="submit" value="修改">
                    <input type="hidden" name="productImgNo"  value="${productImg.productImgNo}">
                    <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
            </td>
            <td>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product.do" style="margin-bottom: 0px;">
                    <input type="submit" value="刪除">
                    <input type="hidden" name="productImgNo"  value="${productImg.productImgNo}">
                    <input type="hidden" name="action" value="delete"></FORM>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
