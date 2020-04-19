<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Manager Offer</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="style.css" />
        <link rel="stylesheet" type="text/css" href="admin.css" />
    </head>
    <body>
        <div id="main_container">
            <div id="main_content">
                <%@include file="adminHeader.jsp" %>
                <div id="admin_header">
                    <div class="admin_index_title">Manage Offers</div>
                    <div class="right_buttons">
                        <div class="right_button"><a href="addNewItem.jsp">Add new offer</a></div>
                    </div>
                </div>
                <div id="admin_header_border"></div>
                <div id="admin_search_tab">
                    <label class="search" style="padding-top:3px;">Search an offer: </label>
                    <label class="search">
                        <input type="text" name="search" class="search_input" />
                    </label>
                    <label class="search"><a href="#"><img src="images/adminicons/search.png" alt="" border="0" /></a> </label>
                </div>
                <div class="table_grid">
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                            <th style="width:20px;"><a href="#" class="pink">ID</a></th>
                            <th style="width:50px;"><a href="#" class="pink">Picture</a></th>
                            <th style="width:auto;"><a href="#" class="pink">Title</a></th>
                            <th style="width:auto;"><a href="#" class="pink">Description</a></th>
                            <th style="width:100px;"><a href="#" class="pink">Price</a></th>
                            <th style="width:50px;"><a href="#" class="pink">Edit</a></th>
                            <th style="width:50px;"><a href="#" class="pink">Delete</a></th>
                        </tr>
                        <c:forEach var="item" varStatus="counter" items="${requestScope.LIST_ITEM}">
                            <c:if test="${counter.count % 2 == 0}">
                                <tr class="even">
                            </c:if>
                            <c:if test="${counter.count % 2 == 1}">
                                <tr class="odd">
                            </c:if>
                                <td>${item.ID}</td>
                                <td><a href="GetDetailItemController?itemID=${item.ID}"><img alt="" src="${item.linkImage}" width="53" height="39" border="0" /></a></td>
                                <td>${item.name}</td>
                                <td>${item.shortDescription}</td>
                                <td><strong><fmt:formatNumber value="${item.price}" type="number" maxFractionDigits="3" />VND</strong></td>
                                <td><a href="AdminController?itemID=${item.ID}&action=Edit"><img alt="" src="images/adminicons/edit.png" width="22" height="22" border="0" /></a></td>
                                <td><a href="AdminController?itemID=${item.ID}&action=Delete"><img alt="" src="images/adminicons/delete.png" width="24" height="24" border="0" /></a></td>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
                <div class="pagination"> <span class="disabled">prev</span> <span class="current">1</span> <a href="#">2</a> <a href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">6</a> <a href="#">7</a>...<a href="#">199</a> <a href="#">200</a> <a href="#">next</a> </div>
                <div class="admin_footer_help"> Admin help section here Admin help section here Admin help section here Admin help section here Admin help section here </div>
            </div>
            <!-- end of main_content -->
            <!-- I don't include footer because strange err -->
            <div id="footer">
                <div id="copyright">
                    <div style="float:left; padding:3px;"><a href="#"><img src="images/footer_logo.gif" width="42" height="35" alt="" border="0" /></a></div>
                    <div style="float:left; padding:14px 10px 10px 10px;"> Company name.&copy; All Rights Reserved 2008 - By <a href="http://csscreme.com" style="color:#772c17;">csscreme</a></div>
                </div>
                <ul class="footer_menu">
                    <li><a href="#" class="nav_footer"> Home </a></li>
                    <li><a href="#" class="nav_footer"> Selling Homes </a></li>
                    <li><a href="#" class="nav_footer"> Buying Homes </a></li>
                    <li><a href="#" class="nav_footer"> Renting Homes</a></li>
                    <li><a href="#" class="nav_footer"> RSS </a></li>
                    <li><a href="#" class="nav_footer"> Contact </a></li>
                </ul>
            </div>
        </div>
        <!-- end of main_container -->
    </body>
</html>
