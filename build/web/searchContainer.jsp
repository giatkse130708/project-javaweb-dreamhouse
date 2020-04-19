<%-- 
    Document   : header
    Created on : Dec 11, 2019, 5:24:57 PM
    Author     : Kha Gia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
            <div class="left_box">
                <div class="top_left_box"> </div>
                <div class="center_left_box">
                    <div class="box_title"><span>Find</span> your place:</div>
                    <form action="SearchItemController">
                    <div class="form">
                        <div class="form_row">
                            <label class="left">City: </label>
                            <input type="text" class="form_input" name="city" value="${requestScope.CITY}"/>
                        </div>
                        <div class="form_row">
                            <label class="left">Price: </label>
                            <input type="number" class="form_input" value="${requestScope.MIN_PRICE}" name="minPrice"/>
                        </div>
                        <div class="form_row">
                            <label class="left">To: </label>
                            <input type="number" class="form_input" value="${requestScope.MAX_PRICE}" name="maxPrice"/>
                        </div>
                        <div class="form_row">
                            <label class="left_long">Looking for to:</label>
                        </div>
                        <div style="text-align: left; margin-left: 90px">
                            <%
                                String optionOneChecked = "";
                                String optionTwoChecked = "";
                                String optionThreeChecked = "";
                                String optionFourChecked = "";
                                String optionFiveChecked = "";
                                String category = (String) request.getAttribute("CATEGORY");
                                if(category != null){
                                    switch(category){
                                        case "Biệt thự":
                                            optionOneChecked = "checked";
                                            break;
                                        case "Chung cư":
                                            optionTwoChecked = "checked";
                                            break;
                                        case "Nhà xưởng, nhà kho":
                                            optionThreeChecked = "checked";
                                            break;
                                        case "Khu nghỉ dưỡng":
                                            optionFourChecked = "checked";
                                            break;
                                        default:
                                            optionFiveChecked = "checked";
                                            break;
                                    }
                                } else{
                                    optionFiveChecked = "checked";
                                }
                            %>
                            <input type="radio" name="category" value="Biệt thự" <%=optionOneChecked%>>Biệt thự </br>
                            <input type="radio" name="category" value="Chung cư" <%=optionTwoChecked%>>Chung cư </br>
                            <input type="radio" name="category" value="Nhà xưởng, nhà kho" <%=optionThreeChecked%>>Nhà xưởng, nhà kho </br>
                            <input type="radio" name="category" value="Khu nghỉ dưỡng" <%=optionFourChecked%>>Khu nghỉ dưỡng </br>
                            <input type="radio" name="category" value="Tất cả" <%=optionFiveChecked%>>Tất cả </br>
                        </div>
                        <h4 style="color: red">${requestScope.ERROR_MESSAGE}</h4>
                        <div style="float:right; padding:10px 25px 0 0;">
                            <input type="image" src="images/find.gif" />
                        </div>
                    </div>
                    </form>
                </div>
                <div class="bottom_left_box"> </div>
                
                <!--Begin Join our newsletter-->
                <div class="left_box" style="margin-top: 10px">
                <div class="top_left_box"> </div>
                <div class="center_left_box">
                    <div class="box_title"><span>Join</span> our newsletter:</div>
                    <div class="form">
                        <div class="form_row">
                            <label class="left">Email: </label>
                            <input type="text" class="form_input" />
                        </div>
                        <div style="float:right; padding:10px 25px 0 0;">
                            <input type="image" src="images/join.gif" />
                        </div>
                    </div>
                </div>
                <div class="bottom_left_box"> </div>
            </div>
            <!--End Join our newsletter-->  
            
            <!--Begin Contact information-->
            <div class="left_box">
                <div class="top_left_box"> </div>
                <div class="center_left_box">
                    <div class="box_title"><span>Contact</span> information:</div>
                    <div class="form">
                        <div class="form_row"> <img src="images/contact_envelope.gif" width="50" height="47" border="0" class="img_right" alt="" />
                            <div class="contact_information"> Email: contact@buyhouse.com<br />
                                Telephone: 0234 789 90<br />
                                Mobile: 234 345 234534<br />
                                Fax: 34534 3456 3456(54)<br />
                                <br />
                                <span>www.buyhouse.com</span> </div>
                        </div>
                    </div>
                </div>
                <div class="bottom_left_box"> </div>
            </div>
            <!--End Contact information-->
            
            </div>

    </body>
</html>
