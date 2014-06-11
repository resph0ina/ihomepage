<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<jsp:useBean id="checktob" scope="page" class="b.CheckToB" />

<jsp:useBean id="stb" scope="page" class="b.SubscribeToB" />

<!DOCTYPE html>
<head>
    
    <title>InfoHub</title>
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.0/css/bootstrap-combined.min.css" rel="stylesheet">
    <style type='text/css'>
      body {
        background-color: #CCC;
      }
      
    </style>
      <script language="javascript" type="text/javascript">
 	</script>
    
</head>


<body>
	
	
	
	<script language ="JavaScript">
	<!--
		function checkEmpty(){
		if (login.username.value == ""){
			alert("用户名不能为空");
			return false;
		}
		if (login.password.value == ""){
			alert("密码不能为空");
			return false;
		}
		}
	-->
	</script>
	
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="brand" href="index.jsp">InfoHub</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active"><a href="index.jsp">主页</a></li>
						<li><a href="subscribe.jsp">订阅表单</a></li>
						
						<% if (request.getSession().getAttribute("username")==null) { %>
							<li><a href="notlogsummary.jsp">综合</a></li>
							<li><a href="notlogjiuijing.jsp">系内公告</a></li>
							<li><a href="notloginfo.jsp">Info公告</a></li>
						<% } else {
							String uid=request.getSession().getAttribute("id").toString(); %>
							<li><a href="zonghe.jsp">综合</a></li>
							<% if (stb.isOrder(1,uid)) %> <li><a href="info.jsp">Info公告</a></li>
							<% if (stb.isOrder(2,uid)) %> <li><a href="JiuJing.jsp">系内公告</a></li>
					
							<% if (stb.isOrder(4,uid)) %> <li><a href="renren.jsp">人人信息</a></li>
						<%} %>
					</ul>
					<% if (request.getSession().getAttribute("username") != null) out.write("<!--"); %>
					<form name="login" class="navbar-form pull-right" action="index.jsp?iAction=check" method="post" onsubmit="return checkEmpty();">
						<input class="span2" type="text" placeholder="Username" name="username">
						<input class="span2" type="password" placeholder="Password" name="password">
						<button type="submit" class="btn">登录</button>
						<a button type="button" class="btn" href="reg.jsp">注册</button></a>
					</form>		
					<% if (request.getSession().getAttribute("username") != null) out.write("-->"); %>
					<% if (request.getSession().getAttribute("username") == null) out.write("<!--"); %>
					<form class="navbar-form pull-right" action="index.jsp?iAction=logout" method="post">
						<span style="color: white;">
						<% if (request.getSession().getAttribute("username") != null)
							out.write("Hello!"+request.getSession().getAttribute("username").toString()+"  ");%>
						</span>
						<button type="submit" class="btn">登出</button>
					</form>
					<% if (request.getSession().getAttribute("username") == null) out.write("-->"); %>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<div class="container">
		<div class="hero-unit">
			<% if (request.getSession().getAttribute("username") != null) out.write("<!--"); %>
			<h1>Welcome to InfoHub!</h1>	
			<p>Don't hesitate! Register online and join us. You'll have fun here!</p>
			<p>
				<a class="btn btn-primary btn-large" href="reg.jsp">Register now! &raquo;</a>
			</p>
			<% if (request.getSession().getAttribute("username") != null) out.write("-->"); %>
			<% if (request.getSession().getAttribute("username") == null) out.write("<!--"); %>
			<h1>Welcome!</h1>	
			<% if (request.getSession().getAttribute("username") == null) out.write("-->"); %>
		</div>

		<footer>
			<p>&copy; Su Ning and Yan Yukang, Dept. CST, Tsinghua Univ., 2013</p>
		</footer>

	</div>
	<%
	request.setCharacterEncoding("utf-8");
	String result = null;
	if (request.getParameter("iAction") != null)
	if (request.getParameter("iAction").compareTo("check") == 0){
		result = checktob.getID(request.getParameter("username"), request.getParameter("password"));
		if (result.compareTo("用户名不存在") == 0) {	
			%>
			<script language=JavaScript>
				alert("用户名不存在");
			</script> <%
		}
		else if (result.compareTo("密码错误") == 0) {
			%>
			<script language=JavaScript>
		 		alert("密码错误");
			</script><% 
		}
		else {
			session.putValue("id", result);
			session.putValue("username", request.getParameter("username"));
			response.sendRedirect("index.jsp");
		}
	}
	else if (request.getParameter("iAction").compareTo("logout") == 0){
		session.putValue("username", null);
		response.sendRedirect("index.jsp");
	}
	%>
	
</body>
</html>
