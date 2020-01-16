<%@page import="com.woniu.beans.Goods"%>
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
  
    <form class="form-horizontal" role="form" style="width: 550px; margin: 10px auto;" action="<%=request.getContextPath()%>/types/typesUpdSave.do" method="post">
        
        <div class="form-group">
			<label for="typesCode" class="col-sm-3 control-label">商品类型编号:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="typesCode"
					placeholder="请输入商品类型" name="typesCode" value="${requestScope.types.types_code}">
			</div>
		</div>

        <div class="form-group">
			<label for="typesName" class="col-sm-3 control-label">商品类型名称:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="typesName"
					placeholder="请输入商品编号" name="typesName" value="${requestScope.types.types_name}">
			</div>
		</div>

		
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-3 control-label"></label>
			<div class="col-sm-9">
				<input type="submit" class="btn btn-info col-sm-12" value="保存" />
			</div>
		</div>
		
 	   <input type="hidden" name="typesId" value="${requestScope.types.types_id}"/>
	</form>

	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>


</body>
</html>