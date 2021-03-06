<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>BUYHOUSE.COM - Sign up for an account</title>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css" href="style.css" />
<link rel="stylesheet" type="text/css" href="admin.css" />
</head>
<body>
<div id="main_container">
  <div id="header">
    <div id="logo"> <a href="#"><img src="images/logo.gif" width="147" height="78" alt="" border="0" /></a> </div>
    <div class="banner_adds"></div>
    <div class="menu">
      <ul>
        <li><a href="#">Admin Home</a></li>
        <li><a href="#">Manage Offers</a></li>
        <li><a href="login.jsp">Login</a></li>
        <li><a href="#">Newsletter</a></li>
        <li><a href="#">Manage Customers</a></li>
        <li><a href="#">Website</a></li>
      </ul>
    </div>
  </div>
  <div id="main_content">
    <div class="admin_login">
      <div class="left_box">
        <div class="top_left_box"> </div>
        <div class="center_left_box">
          <div class="box_title"><span>Sign up for an account</span></div>
          
          <form action="SignUpController" method="POST">
              <div class="form">
            <div class="form_row">
              <label class="left">Username: </label>
              <input type="text" class="form_input" name="userID" value="${requestScope.userID}"required/>
            </div>
            <div class="form_row">
              <label class="left">Password: </label>
              <input type="password" class="form_input" name="password" value="${requestScope.password}" required/>
            </div>
            <div class="form_row">
              <label class="left">Confirm password: </label>
              <input type="password" class="form_input" name="rePassword" value="${requestScope.rePassword}" required/>
            </div>
            <div class="form_row">
              <label class="left">Full name: </label>
              <input type="text" class="form_input" name="fullName" value="${requestScope.fullName}" required/>
            </div>
            <div class="form_row">
              <label class="left">Phone number: </label>
              <input type="number" class="form_input" name="phoneNumber" value="${requestScope.phoneNumber}" required/>
            </div>
            <div class="form_row">
              <label class="left">Email: </label>
              <input type="email" class="form_input" name="email" value="${requestScope.email}" required/>
            </div>
            <div class="form_row">
              <label class="left">Identify Card: </label>
              <input type="number" class="form_input" name="identifyCard" value="${requestScope.identifyCard}"required/>
            </div>
            <div style="float:right; padding:10px 25px 0 0;">
                <input type="submit" name="btnAction" value="Sign up"/>
            </div>
          </div>
          </form>
          <h3 style="color: red">${requestScope.ERROR_MESSAGE}</h3>
          <h3 style="color: green">${requestScope.SUCCESSFUL_MESSAGE}</h3>
        </div>
        <div class="bottom_left_box"> </div>
      </div>
    </div>
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
