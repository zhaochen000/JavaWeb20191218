<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link  href="<%=request.getContextPath()%>/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
</head>
<body>

	<form class="form-horizontal" role="form"
		style="width: 500px; margin: 10px auto;" action="<%=request.getContextPath()%>/goods/goodsAdd.do" method="post">
		<div class="form-group">
			<label for="goodsCode" class="col-sm-3 control-label">商品编号:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="goodsCode"
					placeholder="请输入商品编号" name="goodsCode">
			</div>
		</div>
		<div class="form-group">
			<label for="goodsName" class="col-sm-3 control-label">商品名称:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="goodsName"
					placeholder="请输入商品名称" name="goodsName">
			</div>
		</div>
		<div class="form-group">
			<label for="goodsKinds" class="col-sm-3 control-label">商品类别:</label>
			<div class="col-sm-9">
					
				<select name="goodsKinds" class="form-control">
					<option>请选择</option>
					<c:forEach items="${requestScope.typesList}" var="t">
						<option value="${t.types_id}">${t.types_name}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label for="goodsSupplier" class="col-sm-3 control-label">商品生产商:</label>
			<div class="col-sm-9">
				<select name="goodsSupplier" class="form-control">
					<option>请选择</option>
					<c:forEach items="${requestScope.suppliersList}" var="s">
						<option value="${s.suppliers_id}">${s.suppliers_name}</option>
					</c:forEach>
				</select>
			</div>
		</div>

       <div class="form-group">
			<label for="goodsPrice" class="col-sm-3 control-label">商品价格:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="goodsPrice"
					placeholder="请输入商品价格" name="goodsPrice">
			</div>
		</div>

        <div class="form-group">
			<label for="goodsCount" class="col-sm-3 control-label">商品库存量:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="goodsCount"
					placeholder="请输入商品库存量" name="goodsCount">
			</div>
		</div>
		<div class="form-group">
			<label for="goodsLevel" class="col-sm-3 control-label">质量等级:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="goodsLevel"
					placeholder="请输入商品质量等级" name="goodsLevel">
			</div>
		</div>
		
		<div class="form-group">
			<label for="goodsSpec" class="col-sm-3 control-label">型号规格:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="goodsSpec"
					placeholder="请输入商品规格型号" name="goodsSpec">
			</div>
		</div>
		
		<div class="form-group">
			<label for="goodsRemarks" class="col-sm-3 control-label">备注信息:</label>
			<div class="col-sm-9">
				<textarea rows="" cols="" name="goodsRemarks" class="col-sm-12"></textarea>
			</div>
		</div>
		
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-3 control-label"></label>
			<div class="col-sm-9">
				<input type="submit" class="btn btn-info col-sm-12" value="保存" />
			</div>
		</div>
	</form>

	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

</body>
</html>