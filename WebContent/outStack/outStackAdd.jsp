<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	type="text/css" rel="stylesheet" />

</head>
<body>
	<form class="form-horizontal" role="form"
		style="width: 550px; margin: 10px auto;"
		action="<%=request.getContextPath()%>/outStack/outStackAdd.do" method="post">
		<div class="form-group">
			<label for="outStackCode" class="col-sm-3 control-label">出库单编号:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="outStackCode"
					placeholder="请输入入库单号" name="outStackCode">
			</div>
		</div>
		<div class="form-group">
			<label for="goodsId" class="col-sm-3 control-label">出库商品:</label>
			<div class="col-sm-9">
			
				 <select name="goodsId" class="form-control">
					<option>请选择</option>
					
					<c:forEach items="${requestScope.goodsList}" var="g">
						<option value="${g.goods_id}">${g.goods_name}</option>
					</c:forEach>
			    </select>
			    
			</div>
		</div>

		<div class="form-group">
			<label for="outStackCount" class="col-sm-3 control-label">出库数量:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="outStackCount"
					placeholder="请输入出库数量" name="outStackCount">
			</div>
		</div>

		<div class="form-group">
			<label for="outStackRemarks" class="col-sm-3 control-label">出库备注:</label>
			<div class="col-sm-9">
				<textarea rows="" cols="" name="outStackRemarks" class="col-sm-12"></textarea>	
			</div>
		</div>

		<div class="form-group">
			<label for="inputEmail3" class="col-sm-3 control-label"></label>
			<div class="col-sm-9">
				<input type="submit" class="btn btn-info col-sm-12" value="保存" />
			</div>
		</div>
	</form>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</body>
</html>