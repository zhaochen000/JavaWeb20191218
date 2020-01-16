<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" type="text/css" rel="stylesheet" />

</head>
<body>
	<form class="form-horizontal" role="form"
		style="width: 400px; margin: 10px auto;" action="<%=request.getContextPath()%>/suppliers/suppliersAdd.do"
		method="post">
		<div class="form-group">
			<label for="suppliersCode" class="col-sm-3 control-label">供应商编号:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="suppliersCode"
					placeholder="请输入供应商编号" name="suppliersCode">
			</div>
		</div>
		<div class="form-group">
			<label for="suppliersName" class="col-sm-3 control-label">供应商名称:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="suppliersName"
					placeholder="请输入供应商名称" name="suppliersName">
			</div>
		</div>

		<div class="form-group">
			<label for="suppliersPhone" class="col-sm-3 control-label">供应商联系方式:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="suppliersPhone"
					placeholder="请输入供应商联系方式" name="suppliersPhone">
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