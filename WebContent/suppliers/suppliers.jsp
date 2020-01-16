<%@page import="com.woniu.beans.Suppliers"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
</head>
<body>

	<div style="text-align: right; width: 600px; margin: 20px auto">
		<a class="btn btn-success"
			href="<%=request.getContextPath()%>/suppliers/suppliersAdd.jsp">增加</a>
	</div>
	<table
		class="table table-bordered table-striped table-hover table-condensed"
		style="width: 600px; margin: 20px auto">
		<thead>

			<tr>
				<th>供应商编号</th>
				<th>供应商名称</th>
				<th>供应商联系方式</th>
				<th>操作</th>

			</tr>
		</thead>
		<tbody>
			<%
             List<Suppliers> suppList=(List<Suppliers>)request.getAttribute("suppList");
             for(Suppliers sup:suppList){
          %>
			<tr>
				<td><%=sup.getSuppliers_code()%></td>
				<td><%=sup.getSuppliers_name()%></td>
				<td><%=sup.getSuppliers_phone()%></td>

				<td>
				<a href="<%=request.getContextPath()%>/suppliers/suppliersUpd.do?suppliersId=<%=sup.getSuppliers_id()%>"
					class="btn btn-info btn-xs">修改</a> 
				<a href="<%=request.getContextPath()%>/suppliers/suppliersDel.do?suppliersId=<%=sup.getSuppliers_id()%>"
				    class="btn btn-danger btn-xs">删除</a>
				</td>
			</tr>
		  <%
             }
          %>
		</tbody>
	</table>

	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</body>
</html>