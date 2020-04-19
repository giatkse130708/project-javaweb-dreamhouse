<%@page import="giatk.dtos.ItemDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Update detail</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="style.css" />
        <link rel="stylesheet" type="text/css" href="admin.css" />
    </head>
    <body>
        <div id="main_container">
            <%@include file="adminHeader.jsp" %>
            <div id="main_content">
                <div id="admin_header">
                    <div class="admin_editoffer_title">Edit offer</div>
                    <div class="right_buttons">
                        <div class="right_button"><a href="AdminController">Back</a></div>
                    </div>
                </div>
                <div id="admin_header_border"></div>
                <form action="UpdateItemController">
                    <div class="add_tab">
                        <div class="form_contact">
                            <div class="adminform_row_contact">
                                <label class="adminleft">Title: </label>
                                <input type="text" class="form_input_contact" style="width: 450px" name="title" value="${sessionScope.CURRENT_ITEM.name}" required/>
                            </div>
                            <div class="adminform_row_contact">
                                <label class="adminleft">Price: </label>
                                <input type="text" class="form_input_contact" style="width: 450px" name="price" value="${sessionScope.CURRENT_ITEM.price}" required/>
                            </div>
                            <div class="adminform_row_contact">
                                <label class="adminleft">Square: </label>
                                <input type="text" class="form_input_contact" style="width: 450px" name="square" value="${sessionScope.CURRENT_ITEM.square}" required/>
                            </div>
                            <div class="adminform_row_contact">
                                <label class="adminleft">Address: </label>
                                <input type="text" class="form_input_contact" style="width: 450px" name="address" value="${sessionScope.CURRENT_ITEM.address}" required/>
                            </div>
                            <div class="adminform_row_contact">
                                <label class="adminleft">Category: </label>
                                <select class="form_select" name="category" style="width: 450px">
                                    <%
                                        String category = ((ItemDTO) session.getAttribute("CURRENT_ITEM")).getCategory();
                                        int itemID = ((ItemDTO) session.getAttribute("CURRENT_ITEM")).getID();
                                        String optionOneSelected = "";
                                        String optionTwoSelected = "";
                                        String optionThreeSelected = "";
                                        String optionFourSelected = "";
                                        switch (category) {
                                            case "Biệt thự":
                                                optionOneSelected = "selected";
                                                break;
                                            case "Chung cư":
                                                optionTwoSelected = "selected";
                                                break;
                                            case "Nhà xưởng, nhà kho":
                                                optionThreeSelected = "selected";
                                                break;
                                            case "Khu nghỉ dưỡng":
                                                optionFourSelected = "selected";
                                                break;
                                        }
                                    %>
                                    <option <%=optionOneSelected%>>Biệt thự</option>
                                    <option <%=optionTwoSelected%>>Chung cư</option>
                                    <option <%=optionThreeSelected%>>Nhà xưởng, nhà kho</option>
                                    <option <%=optionFourSelected%>>Khu nghỉ dưỡng</option>
                                </select>
                            </div>
                            <div class="adminform_row_contact">
                                <label class="adminleft">Description: </label>
                                <textarea name="description" rows="" cols="" style="width: 600px; height: 300px" >${sessionScope.CURRENT_ITEM.description}</textarea>
                            </div>
                            <div class="adminform_row_contact">
                                <a href="editImageItem.jsp">Update Pictures</a>
                            </div>
                            <div class="adminform_row_contact">
                                <h3 style="color: green; font-weight: bold;">${requestScope.MESSAGE}</h3>
                            </div>

                            <input type="hidden" name="itemID" value="<%=itemID%>"/>

                            <input type="submit" name="upfile" value="Save"/>
                        </div>
                    </div>
                </form>
                            <div class="admin_footer_help" style="color: red"> ${requestScope.ERROR_MESSAGE} </div>
            </div>
            <!-- end of main_content -->
            <%@include file="footer.jsp" %>
        </div>
        <!-- end of main_container -->
    </body>
</html>
