<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>BUYHOUSE.COM - Login</title>
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
        <li><a href="contact.html">Website</a></li>
      </ul>
    </div>
  </div>
  <div id="main_content">
    <div class="admin_login">
      <div class="left_box">
        <div class="top_left_box"> </div>
        <div class="center_left_box">
          <div class="box_title"><span>Login</span></div>
          
          <form action="LoginController" method="POST">
          <div class="form">
            <div class="form_row">
              <label class="left">Username: </label>
              <input type="text" class="form_input" name="userID" required/>
            </div>
            <div class="form_row">
              <label class="left">Password: </label>
              <input type="password" class="form_input" name="password" required/>
            </div>
            <div style="float:right; padding:10px 25px 0 0;">
                <input type="submit" name="btnAction" value="Login"/>
            </div>
          </div>
          </form>
          <h3 style="color: red">${requestScope.ERROR_MESSAGE}</h3>
        </div>      
        

        <div class="bottom_left_box"> </div>
        <h4 style="float: right"><a href="signupAccount.jsp">Sign up for an account</a></h4>
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
