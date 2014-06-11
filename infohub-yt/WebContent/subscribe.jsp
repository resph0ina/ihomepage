<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

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
	
	
	<%
	if (request.getParameter("rAction") != null) {
		String uid=request.getSession().getAttribute("id").toString();
		if (request.getParameter("infoc").compareTo("yes") == 0) stb.setOrder(1,uid,null,null,"yes");
		if (request.getParameter("infoc").compareTo("no") == 0) stb.setOrder(1,uid,null,null,"no");
		if (request.getParameter("cstc").compareTo("yes") == 0) stb.setOrder(2,uid,null,null,"yes");
		if (request.getParameter("cstc").compareTo("no") == 0) stb.setOrder(2,uid,null,null,"no");
		if (request.getParameter("renrenc").compareTo("yes") == 0) stb.setOrder(4,uid,request.getParameter("renrenna").toString(),request.getParameter("renrenpa").toString(),"yes");
		if (request.getParameter("renrenc").compareTo("no") == 0) stb.setOrder(4,uid,request.getParameter("renrenna").toString(),request.getParameter("renrenpa").toString(),"no");
			
	
	%>
	<script language=JavaScript>
		alert("修改成功");
	</script> 
	<%
	}
	%>
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
	
	<% 
	Boolean yon;
	String uid=null;
	if (request.getSession().getAttribute("username") != null) {
		uid=request.getSession().getAttribute("id").toString();
		yon=stb.isOrder(4,uid);
	}
		
	%> 
	
	
	
	
	
	<div class="container">
		<div class="hero-unit">
			<h2>订阅表单</h2>
			<% if (request.getSession().getAttribute("username") == null) {%>
			<h2>Please Log In First!</h2>
			<% } %>
			<% if (request.getSession().getAttribute("username") != null) { 
			%>
			<form class="navbar-form" method="post" action="subscribe.jsp?rAction=change">
				<table>
					<tr><td>Info公告</td>
					<td><input type="radio" name="infoc" value="yes" <%if (stb.isOrder(1,uid)) {%>checked="checked"<% } %>/>yes</td>
					<td><input type="radio" name="infoc" value="no" <%if (!stb.isOrder(1,uid)) {%>checked="checked"<% } %>/>no</td>
					<td><input type="submit" class="btn btn-primary btn-small" value="提交"></td></tr>
					<tr><td>系内公告</td>
					<td><input type="radio" name="cstc" value="yes" <%if (stb.isOrder(2,uid)) {%>checked="checked"<% } %>/>yes</td>
					<td><input type="radio" name="cstc" value="no" <%if (!stb.isOrder(2,uid)) {%>checked="checked"<% } %>/>no</td>
					<td><input type="submit" class="btn btn-primary btn-small" value="提交"></td></tr>
					<tr><td>人人信息</td>
					<td><input type="radio" name="renrenc" value="yes" <%if (stb.isOrder(4,uid)) {%>checked="checked"<% } %> />yes</td>
					<td><input type="radio" name="renrenc" value="no" <%if (!stb.isOrder(4,uid)) {%>checked="checked"<% } %> />no</td>
					<td><input type="submit" class="btn btn-primary btn-small" value="提交"></td></tr>
						
						<tr><td>请输入</td><td>用户名</td><td><input  type="text" value="<%=stb.getRrAccount(uid).getUsername() %>" name="renrenna"></td></tr>
						<tr><td></td><td>密码</td><td><input  type="password" value="<%=stb.getRrAccount(uid).getPassword() %>" name="renrenpa"></td></tr>	
						
				</table>
			</form>
			
			<br> </br>
			<%}%>
		</div>

		<footer>
			<p>&copy; Su Ning and Yan Yukang, Dept. CST, Tsinghua Univ., 2013</p>
		</footer>

	</div>
	
	
</body>
</html>
