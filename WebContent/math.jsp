<%@page import="java.util.ArrayList"%>
<%@page import="com.woniu.beans.Users"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
 <% 
  int a= 20;
  int b =25;
  request.setAttribute("a", a);
  request.setAttribute("b", b);
  %>
  
  <% 
   List<Users> li = new ArrayList<Users>();
   li.add(new Users("tom1","123"));
   li.add(new Users("tom2","123"));
   li.add(new Users("tom3","123"));
   li.add(new Users("tom4","123"));
   
   Users users = new Users();
   request.setAttribute("liList", li);
  
  %>
  
  
  <c:forEach items="${requestScope.liList}" var="u" step="1" varStatus="vs">
     ${u}
     ${u.user_name}---${u.user_pwd}--${vs.count}--${vs.index}<br/>
  </c:forEach>
  
  <c:forEach begin="1" end="10" var="i" step="2">
      ${i}
  </c:forEach>

	<c:choose>
		<c:when test="${requestScope.a >requestScope.b}">
           <br/>${requestScope.a}
		</c:when>

		<c:otherwise>
            ${requestScope.b}
		</c:otherwise>
	</c:choose>
	
  ${pageContext.request.contextPath}
  <%=request.getContextPath()%>
  ${requestScope.contextPath}
 <br/>
  <%=request.getServletPath()%>
   ${pageContext.request.servletPath}
  

</body>
</html>