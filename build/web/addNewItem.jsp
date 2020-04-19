<%@page import="giatk.dtos.ItemDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Insert new item</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="style.css" />
        <link rel="stylesheet" type="text/css" href="admin.css" />
    </head>
    <body>
        <div id="main_container">
            <%@include file="adminHeader.jsp" %>
            <div id="main_content">
                <div id="admin_header">
                    <div class="admin_editoffer_title">Add new offer</div>
                    <div class="right_buttons">
                        <div class="right_button"><a href="AdminController">Back</a></div>
                    </div>
                </div>
                <div id="admin_header_border"></div>
                <form action="InsertItemController">
                    <div class="add_tab">
                        <div class="form_contact">
                            <div class="adminform_row_contact">
                                <label class="adminleft">Title: </label>
                                <input type="text" class="form_input_contact" style="width: 450px" name="title" value="${requestScope.title}" required/>
                            </div>
                            <div class="adminform_row_contact">
                                <label class="adminleft">Price: </label>
                                <input type="text" class="form_input_contact" style="width: 450px" name="price" value="${requestScope.price}" required/>
                            </div>
                            <div class="adminform_row_contact">
                                <label class="adminleft">Square: </label>
                                <input type="text" class="form_input_contact" style="width: 450px" name="square" value="${requestScope.square}" required/>
                            </div>
                            <div class="adminform_row_contact">
                                <label class="adminleft">Address: </label>
                                <input type="text" class="form_input_contact" style="width: 450px" name="address" value="${requestScope.address}" required/>
                            </div>
                            <div class="adminform_row_contact">
                                <label class="adminleft">Category: </label>
                                <select class="form_select" style="width: 450px" name="category">
                                    <option>Biệt thự</option>
                                    <option>Chung cư</option>
                                    <option>Nhà xưởng, nhà kho</option>
                                    <option>Khu nghỉ dưỡng</option>
                                </select>
                            </div>
                            <div class="adminform_row_contact">
                                <label class="adminleft">Description: </label>
                                <textarea name="description" rows="" cols="" style="width: 600px; height: 300px">${requestScope.description}</textarea>
                            </div>

                            <input type="submit" name="upfile" value="Save"/>
                        </div>
                    </div>
                </form>
                <div class="admin_footer_help" style="color: red"> ${requestScope.ERROR_MESSAGE}</div>
            </div>
            <!-- end of main_content -->
            <%@include file="footer.jsp" %>
        </div>
        <!-- end of main_container -->
    </body>
</html>
