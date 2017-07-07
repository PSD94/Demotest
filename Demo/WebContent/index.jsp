<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>tweets</title>
</head>
<body>
Hello Prasad

<a href="HelloTweetServlet">Hello Tweet</a><BR>

<% out.println("Starting Tweet Application");
 response.setIntHeader("Refresh", 5);

%>
<a href="TweetStatusServlet" >Tweet Status</a>

</body>
</html>