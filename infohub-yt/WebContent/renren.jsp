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
						<li class="active"><a href="index.jsp">��ҳ</a></li>
						<li><a href="subscribe.jsp">���ı�</a></li>
						
						<% if (request.getSession().getAttribute("username")==null) { %>
							<li><a href="notlogsummary.jsp">�ۺ�</a></li>
							<li><a href="notlogjiuijing.jsp">ϵ�ڹ���</a></li>
							<li><a href="notloginfo.jsp">Info����</a></li>
						<% } else {
							String uid=request.getSession().getAttribute("id").toString(); %>
							<li><a href="zonghe.jsp">�ۺ�</a></li>
							<% if (stb.isOrder(1,uid)) %> <li><a href="info.jsp">Info����</a></li>
							<% if (stb.isOrder(2,uid)) %> <li><a href="JiuJing.jsp">ϵ�ڹ���</a></li>
							
							<% if (stb.isOrder(4,uid)) %> <li><a href="renren.jsp">������Ϣ</a></li>
						<%} %>
					</ul>
					<% if (request.getSession().getAttribute("username") != null) out.write("<!--"); %>
					<form name="login" class="navbar-form pull-right" action="index.jsp?iAction=check" method="post" onsubmit="return checkEmpty();">
						<input class="span2" type="text" placeholder="Username" name="username">
						<input class="span2" type="password" placeholder="Password" name="password">
						<button type="submit" class="btn">��¼</button>
						<a button type="button" class="btn" href="reg.jsp">ע��</button></a>
					</form>		
					<% if (request.getSession().getAttribute("username") != null) out.write("-->"); %>
					<% if (request.getSession().getAttribute("username") == null) out.write("<!--"); %>
					<form class="navbar-form pull-right" action="index.jsp?iAction=logout" method="post">
						<span style="color: white;">
						<% if (request.getSession().getAttribute("username") != null)
							out.write("Hello!"+request.getSession().getAttribute("username").toString()+"  ");%>
						</span>
						<button type="submit" class="btn">�ǳ�</button>
					</form>
					<% if (request.getSession().getAttribute("username") == null) out.write("-->"); %>
				</div>
			</div>
		</div>
	</div>
	
	<%
		//ResultSet rs = null;
		//rs = data.getRenrenData();
		String id=request.getSession().getAttribute("id").toString();
		RrFeed[] rF = data.checkPass(id);
		
		
	%>
	
	<div class="container">
		<div class="hero-unit">
			<h2>������Ϣ</h2>
			<form class="navbar-form" >
				<table id="tb" class="datatb" cellspacing="0" width="100%" rules=all border frame=box>
				 <tr>
				 <td width="20%" align=center>����</td><td width="55%" align=center>����</td><td width="15%" align=center>ʱ��</td><td width="5%"></td><td width="5%" ></td>
				 </tr>
				<%if (rF==null)  {%> <td>Wrong Log In For RenRen, Please Check Your Account!</td> <%} 
					else 
					{	
						
						String old=null;
						String type=null;
						
						//while(rs.next())
						for (int i=0;i<rF.length;i++)
						{	
							old=rF[i].getType();
							if (old.equals("SHARE_VIDEO")) old="������Ƶ";
							if (old.equals("UPDATE_STATUS")) old="����״̬";
							if (old.equals("PUBLISH_BLOG")) old="������־";
							if (old.equals("PUBLISH_ONE_PHOTO")) old="�ϴ�������Ƭ";
							if (old.equals("SHARE_PHOTO")) old="������Ƭ";
							if (old.equals("SHARE_ALBUM")) old="�������";
							if (old.equals("PUBLISH_MORE_PHOTO")) old="�ϴ�������Ƭ";
							if (old.equals("SHARE_LINK")) old="��������";
							if (old.equals("SHARE_BLOG")) old="������־";
							
				%>
				
					<tr>
					<td align=center><%= old    %></td>
					<td><%= rF[i].getSource()+":"+rF[i].getContent() %></td>
					<td align=center><%= rF[i].getTime()    %></td>
					<td align=center><a button type="button" class="btn btn-primary btn-small" href="ShareRr.jsp?rank=<%=((Integer)i).toString()%>">ת��</button></td>
					 <td align=center><a button type="button" class="btn btn-primary btn-small" href="CommentRr.jsp?rank=<%=((Integer)i).toString()%>">����</button></td>
					</tr>
				<%
						}
					}
				%>
					
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