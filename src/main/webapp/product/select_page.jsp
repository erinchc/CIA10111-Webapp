<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>ezban product: Home</title>

    <style>
        table#table-1 {
            width: 450px;
            background-color: #CCCCFF;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 3px ridge Gray;
            height: 80px;
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

</head>
<body bgcolor='white'>

    <table id="table-1">
        <tr><td><h3>ezban product: Home</h3></td></tr>
    </table>

    <p>This is the Home page for ezban product images.</p>
    <h3>資料查詢:</h3>

    <%-- 錯誤表列 --%>
    <c:if test="${not empty errorMsgs}">
        <font style="color:red">請修正以下錯誤:</font>
        <ul>
            <c:forEach var="message" items="${errorMsgs}">
                <li style="color:red">${message}</li>
            </c:forEach>
        </ul>
    </c:if>

    <ul>
        <li><a href='listAllProductImg.jsp'>List</a> all images.  <br><br></li>

        <li>
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product.do" >
                <b>輸入產品圖片編號:</b>
                <input type="text" name="productImgNo">
                <input type="hidden" name="action" value="getOne_For_Display">
                <input type="submit" value="送出">
            </FORM>
        </li>

        <jsp:useBean id="imgSvc" scope="page" class="com.product.model.ProductImgService" />

        <li>
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product.do" >
                <b>選擇產品圖片編號:</b>
                <select size="1" name="productImgNo">
                    <c:forEach var="productImg" items="${imgSvc.all}" >
                    <option value="${productImg.productImgNo}">${productImg.productImgNo}
                        </c:forEach>
                </select>
                <input type="hidden" name="action" value="getOne_For_Display">
                <input type="submit" value="送出">
            </FORM>
        </li>
    </ul>


    <h3>產品圖片管理</h3>

    <ul>
        <li><a href='<%=request.getContextPath()%>/product/addProductImg.jsp'>Add</a> a new Img.</li>
    </ul>

</body>
</html>