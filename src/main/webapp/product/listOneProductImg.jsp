<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.product.*"%>
<%@ page import="entity.ProductImg" %>

<%
    ProductImg productImg = (ProductImg) request.getAttribute("productImg");
%>

<html>
<head>
    <title>product img</title>

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
            width: 600px;
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
<body bgcolor='white'>

    <table id="table-1">
        <tr><td>
            <h3>product img</h3>
            <h4><a href="<%=request.getContextPath()%>/product/select_page.jsp">回首頁</a></h4>
        </td></tr>
    </table>

    <table>
        <tr>
            <th>圖片編號</th>
            <th>產品編號</th>
            <th>圖片</th>
        </tr>
        <tr>
            <td><%=productImg.getProductImgNo()%></td>
            <td><%=productImg.getProductNo()%></td>
            <td><img src="<%=request.getContextPath()%>/ImgReader?productImgNo=${productImg.productImgNo}"></td>
        </tr>
    </table>

</body>
</html>