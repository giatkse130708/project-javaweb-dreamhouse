<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>${requestScope.CURRENT_ITEM.name}</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
        <div id="main_container">
            <%@include file="header.jsp" %>
            <div id="main_content">
                <aside>
                    <%@include file="searchContainer.jsp" %>
                </aside>
                <div class="column4">
                    <div class="title">${requestScope.CURRENT_ITEM.name} </div>
                </div>
                <!-- end of column four -->
                <div class="column2" style="background-color:#f3f5f6; margin-left:5px;">
                    <div class="big_pic"><img src="${requestScope.FIRST_IMAGE_LINK}" width="282" height="212" alt="" class="img_big_pic" /></div>
                    <div class="pictures_thumbs">
                        <h3>Pictures available:</h3>
                        <c:forEach var="linkImage" items="${requestScope.CURRENT_IMAGE_LIST_ITEM}">
                            <a href="#"><img src="${linkImage}" width="104" height="78" border="0" alt="" class="img_thumb" /></a>
                        </c:forEach>

                    </div>
                </div>
                <!-- end of column two -->
                <div class="column3">
                    <div class="title2">Description</div>
                    <div class="main_text_box">
                        <pre style="white-space: pre-line; text-align: justify;"> ${requestScope.CURRENT_ITEM.description} </pre>
                    </div>
                    <div class="title2">Details</div>
                    <div class="details_list">
                        <ul>
                            <li><span>Price:</span> ${requestScope.CURRENT_ITEM.price} VND </li>
                            <li><span>Address:</span> ${requestScope.CURRENT_ITEM.address} </li>
                            <li><span>Square:</span> ${requestScope.CURRENT_ITEM.square} m2</li>
                            <li><span>Category:</span> ${requestScope.CURRENT_ITEM.category}</li>
                            <li><span>Surface (extra):</span> 120 mp2</li>
                            <li><span>Construction year (extra):</span> 1998</li>
                            <li><span>Other utilities (extra):</span> garage, water pump , back yard..</li>
                        </ul>
                    </div>
                    <div style="float:left;">
                        <div class="button"><a href="PutItemInCart?itemID=${requestScope.CURRENT_ITEM.ID}">Put in my cart</a></div>
                        <h3>${requestScope.MESSAGE}</h3>
                    </div>
                    <!-- end of column three -->
                </div>

            </div>
            <!-- end of main_content -->
            <%@include file="footer.jsp" %>
        </div>
        </div>
        <!-- end of main_container -->

</html>
