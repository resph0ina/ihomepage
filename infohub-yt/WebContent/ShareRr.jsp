<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    <%@ page import="java.sql.*" %>
    <%@ page import="rrapi.*" 	 %>
    <%@ page import="com.renren.api.*" 	 %>
    <jsp:useBean id="data" scope="page" class="b.RenrenB" />
    <jsp:useBean id="stb" scope="page" class="b.SubscribeToB" />
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
	<%
	if (request.getParameter("submit") != null) {
		String c=request.getParameter("reply").toString();
		int rk=Integer.parseInt(request.getParameter("rank"));
		System.out.println("rank="+request.getParameter("rank"));
		System.out.println("c="+request.getParameter("reply"));
		data.addShare(rk,c);
	
	%>
	<script language=JavaScript>
		alert("评论成功");
	</script> 
	<%
	}
	%>
	
	
	
	<%
		//ResultSet rs = null;
		//rs = data.getRenrenData();
		//String id=request.getSession().getAttribute("id").toString();
		//RrFeed[] rF = data.checkPass(id);
		System.out.println("rank="+request.getParameter("rank"));
		//RrComment[] rc= data.getComment(Integer.parseInt(request.getParameter("rank")));
		
		
	%>
	
	<div class="container">
		<div class="hero-unit">
			<h2>转发人人信息</h2>
			
			<form class="navbar-form" name="regist" action="ShareRr.jsp?rank=<%=request.getParameter("rank") %>" method="post">
				<table width="400">
			<form>
				
					<tr>
					<td><input type="text" name="reply" charset="UTF-8"></td>
					</tr>
					<tr>
					<td><input type="submit" value="提交" name="submit"></td>
					</tr>
					
			</form>
			
					
				</table>
			</form>
			
			<br> </br>
		</div>

		
		<hr>

		<footer>
			<p>&copy; null, Dept. CST, Tsinghua Univ., 2013</p>
		</footer>

	</div>
	
</body>
</html>