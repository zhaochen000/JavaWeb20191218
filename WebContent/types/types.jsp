<%@page import="java.util.List"%>
<%@page import="com.woniu.beans.Goods"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="zc" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" type="text/css" rel="styleSheet" />
</head>
<body>
    
   
    <form action="types.do" method="post" >
	<div style="width: 600px; margin: 20px auto; text-align: right;">
		<a href="<%=request.getContextPath()%>/types/typesAdd.jsp" class="btn btn-info ">增加
		    <span class="glyphicon glyphicon-plus"></span>
        </a>
	</div>
	
	<table
		class="table table-bordered table-striped table-hover table-condensed"
		style="width: 600px;  margin: 20px auto">
		<thead>
			<tr class="success">
				<th>商品类型Id</th>
				<th>商品类型编号</th>
				<th>商品类型名称</th>
				<th>操作事项</th>
			</tr>
		</thead>

		<tbody>
		     
            <zc:forEach items="${requestScope.typesList}" var="g">
			<tr align="center">
			
				<td>${g.types_id}</td>
				<td>${g.types_code}</td>
				<td>${g.types_name}</td>
				<td>
				    <a href="<%=request.getContextPath()%>/types/typesDel.do?typesId=${g.types_id}" class="btn btn-danger btn-xs">删除</a>
					<a href="<%=request.getContextPath()%>/types/typesUpd.do?typesId=${g.types_id}" class="btn btn-info btn-xs">修改</a>
                </td>
			</tr>

			</zc:forEach>
 		
		</tbody>

    </table>
   
    </form>
    
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

</body>
</html>