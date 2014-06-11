<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<jsp:useBean id="regcheckb" scope="page" class="b.RegCheckB" />
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
	
	
	<script language ="JavaScript">
	<!--
		function checkEmpty(){
		if (regist.username.value == ""){
			alert("用户名不能为空");
			return false;
		}
		if (regist.password.value == ""){
			alert("密码不能为空");
			return false;
		}
		if (regist.password.value != regist.confirm.value){
			alert("两次输入密码不同");
			return false;
		}
		}
	-->
	</script>
	
	<div class="container">
		<div class="hero-unit">
			<h2>Register</h2>
			
			<form class="navbar-form" name="regist" action="reg.jsp?rAction=check" method="post" onsubmit="return checkEmpty();">
				<table width="400" align="center">
					<tr><td>用户名</td><td><input class="span2" type="text" placeholder="Username" name="username"></td></tr>
					<tr><td>密码</td><td><input class="span2" type="password" placeholder="Password" name="password"></td></tr>
					<tr><td>确认密码</td><td><input class="span2" type="password" placeholder="Confirm" name="confirm"></td></tr>
					<tr><td>邮箱</td><td><input class="span2" type="text" placeholder="Email" name="email"></td></tr>
					<tr><td></td><td><button type="submit" class="btn btn-primary btn-large">Register</button></td></tr>
				</table>
			</form>
			
			<br> </br>
		</div>

		<hr>

		<footer>
			<p>&copy; Su Ning and Yan Yukang, Dept. CST, Tsinghua Univ., 2013</p>
		</footer>

	</div>
	<%
	request.setCharacterEncoding("utf-8");
	if (request.getParameter("rAction") != null)
	if (request.getParameter("rAction").compareTo("check") == 0){
		String gid=regcheckb.getID(request.getParameter("username"),request.getParameter("password"),request.getParameter("email"));
		if (gid!=null){
			session.putValue("username", request.getParameter("username"));
			session.putValue("id", gid);
			response.sendRedirect("subscribe.jsp");
		}
		else  {%>
		 <script language=JavaScript>
 			alert("用户名存在!");
		 </script> 
		<%
		}
	}
	%>
</body>

</html>