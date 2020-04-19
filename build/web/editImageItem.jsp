<%@page import="giatk.dtos.ItemDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Update Image</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="style.css" />
        <link rel="stylesheet" type="text/css" href="admin.css" />
    </head>
    <body>
        <div id="main_container">
            <%@include file="adminHeader.jsp" %>
            <div id="main_content">
                <div id="admin_header">
                    <div class="admin_editoffer_title">Edit Picture</div>
                    <div class="right_buttons">
                        
                        <%
                            int itemID = ((ItemDTO) session.getAttribute("CURRENT_ITEM")).getID();
                        %>
                        
                        
                        
                        <div class="right_button"><a href="AdminController?itemID=<%=itemID%>&action=Edit">Back</a></div>
                    </div>
                </div>
                <div id="admin_header_border"></div>
                <form action="UpdateImageController" method="POST" enctype="multipart/form-data">
                    <div class="add_tab">
                        <div class="form_contact"></>
                            
                            <div class="adminform_row_contact">
                                <label class="adminleft">Picture: </label>
                                <input type="file" class="form_input_contact" name="picture1"/>
                            </div>
                            <div class="adminform_row_contact">
                                <label class="adminleft">Thumbnail 1: </label>
                                <input type="file" class="form_input_contact" name="picture2"/>
                            </div>
                            <div class="adminform_row_contact">
                                <label class="adminleft">Thumbnail 2: </label>
                                <input type="file" class="form_input_contact" name="picture3"/>
                            </div>
                            <div class="adminform_row_contact" style="color: red">
                                ${requestScope.ERROR_MESSAGE}
                            </div>
                              
                            <input type="submit" name="upfile" value="Save"/>
                        </div>
                    </div>
                </form>
                <div class="admin_footer_help"> Admin help section here Admin help section here Admin help section here Admin help section here Admin help section here </div>
            </div>
            <!-- end of main_content -->
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
        <div align=center>This template  downloaded form <a href='#'>free website templates</a></div></body>
</html>
