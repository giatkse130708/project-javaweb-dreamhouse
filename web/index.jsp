<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>BUYHOUSE.COM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="style.css" />
        <link rel="stylesheet" type="text/css" href="itemStyle.css" />
    </head>
    <body>
        <div id="main_container">
            <%@include file="header.jsp" %>
            <div id="main_content">
                <aside>
                    <%@include file="searchContainer.jsp" %>
                </aside>
                <div style="width: 710px; float:right; height: auto;">
                    <h3>Welcome, ${sessionScope.USER.username}!</h3>
                    
                    <c:forEach var="item" varStatus="counter" items="${requestScope.LIST_ITEM}">
                        
                        <div class="itemGiatk">
                     
                                <a href="GetDetailItemController?itemID=${item.ID}"><h3>${item.name}</h3></a>
                                <a href="GetDetailItemController?itemID=${item.ID}"><img src="${item.linkImage}"/></a>
                                <p>

                                    Giá: ${item.price} VND </br>
                                    Diện tích: ${item.square} m2 </br>
                                    Địa chỉ: ${item.address} </br></br>
                                    Mô tả: ${item.shortDescription}</br>
                                </p>
                              
                        </div>
                                
                    </c:forEach>

                    <!-- end of main_content -->
                </div>
                        <%@include file="footer.jsp" %> 
                <!-- end of main_container -->
                </body>
                </html>
