<%@ page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<!-- Basic Page Needs
    ================================================== -->
<meta charset="utf-8">
<!--[if IE]><meta http-equiv="x-ua-compatible" content="IE=9" /><![endif]-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>About Page</title>
<meta name="description" content="">
<meta name="author" content="">

<!-- Favicons
    ================================================== -->
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">

<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"  href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">

<!-- Slider
    ================================================== -->
<link href="css/owl.carousel.css" rel="stylesheet" media="screen">
<link href="css/owl.theme.css" rel="stylesheet" media="screen">
<link href="css/animate.css" rel="stylesheet" media="screen">

<!-- Stylesheet
    ================================================== -->
<link rel="stylesheet" type="text/css"  href="style.css">
<link href='https://fonts.googleapis.com/css?family=PT+Serif:400,400i,700|Montserrat:100,200,300,300i,400,500,600,700,800,900' rel='stylesheet' type='text/css'>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="page">

<!-- Navigation
    ==========================================-->
<nav id="top-menu" class="navbar navbar-default navbar-fixed-top">
  <div class="container"> 
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="navbar-brand" href="index.html"><img src="img/logo-top.png" class="img-responsive"><span>Grit</span></a> </div>
    
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"> 
      
      <!--nav icon--> 
      <a id="nav-icon"> <span></span> <span></span> <span></span> </a> 
      <!--nav icon end-->
      
      <ul id="nav-top" class="nav navbar-nav navbar-right">
        <li><a href="index.html" class="page-scroll">홈으로</a></li>
        <li><a href="/MVCBoard2/Board.do?action=write" class="page-scroll">글쓰기</a></li>
        <li><a href="/MVCBoard2/Board.do?action=list" class="page-scroll">글목록</a></li>
      </ul>
    </div>
    <!-- /.navbar-collapse --> 
  </div>
  <!-- /.container-fluid --> 
</nav>

<!-- banner Page
    ==========================================-->
<div id="page-banner" style="background-image: url(img/photo-typo.jpg);">
  <div class="content  wow fdeInUp">
    <div class="container ">
      <h1>작성된 글 목록 </h1>
    </div>
  </div>
</div>

<!--page body-->

<div id="page-body">
  <div class="container">
    <div class="row wow fdeInUp"> 
      <!--blog posts container-->
      <div class="col-md-12 page-block">
		<table>
			<c:forEach var="board" items="${lists}">
				<tr>
					<td>${board.name}</td>
					<td><a
						href='<c:url value="/MVCBoard2/Board.do?action=view&bbsno=${board.bbsno}&page=${page}"/>'>${board.subject}</a>
					</td>
					<td>${board.readcount}</td>
				</tr>
			</c:forEach>
			<tr>
				<td>&nbsp;</td>
				<td>
					<%
						Integer tempTotalPageCount = (Integer) pageContext.getAttribute("totalPageCount",
								PageContext.REQUEST_SCOPE);
						if (tempTotalPageCount == null) {
							tempTotalPageCount = new Integer(1);
						}
						Integer tempNowpage = (Integer) pageContext.getAttribute("page", PageContext.REQUEST_SCOPE);
						if (tempNowpage == null) {
							tempNowpage = new Integer(1);
						}
						int totalPageCount = tempTotalPageCount.intValue();
						int nowPage = tempNowpage.intValue();
						int totalPageBlock = (int) Math.ceil(totalPageCount / 10.0);
						int nowPageBlock = (int) Math.ceil(nowPage / 10.0);
						int startPage = (nowPageBlock - 1) * 10 + 1;
						int endPage = 0;
						String contextName = pageContext.getServletContext().getServletContextName();
						if (contextName == null || contextName.trim().equals("")) {
							contextName = "";
						} else {
							contextName = "/" + contextName;
						}
						if (totalPageCount > nowPageBlock * 10) {
							endPage = nowPageBlock * 10;
						} else {
							endPage = totalPageCount;
						}
						try {
							if (nowPageBlock > 1) {
								out.print("<a href=\"" + contextName + "/MVCBoard2/Board.do?page=" + (startPage - 1) + "&action=list\">");
								out.print("◀</a>");
							}
							for (int i = startPage; i <= endPage; i++) {
								out.print(" [");
								out.print("<a href=\"" + contextName + "/MVCBoard2/Board.do?page=" + (i) + "&action=list\">");
								out.print(i);
								out.print("</a>] ");
							}
							if (nowPageBlock < totalPageBlock) {
								out.print("<a href=\"" + contextName + "/MVCBoard2/Board.do?page=" + (endPage + 1) + "&action=list\">");
								out.print("▶</a>");
							}
						} catch (IOException ioe) {
							throw new JspException(ioe);
						} finally {
						}
					%>
				</td>
				<td>&nbsp;</td>
			</tr>
		</table>
      </div>
      <!--blog posts container-->
      <div class="clearfix"></div>
    </div>
  </div>
</div>
<footer id="bottom-footer">
  <div class="container">
    <div class="row wow fdeInUp">
      <div class="col-md-4 col-sm-4 col-xs-12"> 
        <!--copyright-->
        <p class="copyright">© 2018 <a href="https://devfloat.net/">Grit</a>. All rights reserved</p>
        <!--/copyright--> 
      </div>
      <!--bottom nav-->
      <div class="col-md-4 col-sm-4 col-xs-12">
        <nav class="bottom-nav">
          <ul>
            <li><a href="#">FAQ</a></li>
            <li><a href="#">Privacy</a></li>
            <li><a href="#">Blog</a></li>
            <li><a href="#">Pricing</a></li>
          </ul>
        </nav>
      </div>
      <!--/bottom nav--> 
      
      <!--powered by-->
      <div class="col-md-4 col-sm-4 col-xs-12">
        <ul class="social-link">
          <li><a href="#"><i class="fa fa-twitter"></i></a></li>
          <li><a href="#"><i class="fa fa-facebook"></i></a></li>
          <li><a href="#"><i class="fa fa-instagram"></i></a></li>
          <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
        </ul>
      </div>
      <!--/powered by--> 
      
    </div>
  </div>
</footer>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
<!-- Include all compiled plugins (below), or include individual files as needed --> 
<script type="text/javascript" src="js/bootstrap.js"></script> 
<script type="text/javascript" src="js/SmoothScroll.js"></script> 
<script type="text/javascript" src="js/jquery.isotope.js"></script> 
<script src="js/owl.carousel.js"></script> 
<script src="js/jquery.waypoints.min.js"></script> 
<!-- Javascripts
    ================================================== --> 
<script type="text/javascript" src="js/main.js"></script> 
<script src="js/wow.min.js"></script> 
<script>
    jQuery(document).ready(function( $ ) {
        $('.counter').counterUp({
            delay: 10,
            time: 1000
        });
    });
</script> 
<script>
new WOW().init();
</script>
</body>
</html>