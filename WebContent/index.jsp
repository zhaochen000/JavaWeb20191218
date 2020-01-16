<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<style type="text/css">
#top {
	height: 100px;
	text-align: center;
	background-color: white;
}

#left {
	height: 500px;
	border: 1px solid black;
}

#right {
	height: 500px;
	border: 1px solid black;
}

#bottom {
	text-align: center;
	line-height: 50px;
	height: 50px;
	background-color: twhite;
}
</style>
</head>
<body>

	<div class="container-fluid">
		<div class="row" style="background-color:red; color: black; height: 100px" >
			<div class="col-md-12" id="top">
				<h1>仓储管理系统</h1>
				  <h6 align="right">${sessionScope.uname}已经登录
				  <a href="exit.do">安全退出</a>
				  </h6>
			</div>
		</div>

		<div class="row">
			<div class="col-md-3 col-sm-3 col-xs-3" id="left">
				<div class="panel-group" id="accordion">
					<!--面板组  -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">系统管理</a>
							</h4>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in">
							<div class="panel-body">
								<ul>
									<li><a href="#">用户管理</a></li>
									<li><a href="#">修改密码</a></li>
									<li><a href="#">权限管理</a></li>
								</ul>
							</div>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">数据管理 </a>
							</h4>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse in">
							<div class="panel-body">
								<ul>
									<li><a href="<%=request.getContextPath()%>/goods/goods.do" target="showInfo">商品管理</a></li>
									<li><a href="<%=request.getContextPath()%>/types/types.do" target="showInfo">商品类别管理</a></li>
									<li><a href="<%=request.getContextPath()%>/suppliers/suppliers.do" target="showInfo">供应商管理</a></li>
								</ul>
							</div>
						</div>
					</div>
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">仓库管理 </a>
							</h4>
						</div>
						<div id="collapseThree" class="panel-collapse collapse in">
							<div class="panel-body">
								<ul>
									
									<li><a href="#">入库管理</a></li>
									<li><a href="<%=request.getContextPath()%>/outStack/outStack.do" target="showInfo">出库管理</a></li>
								</ul>
							</div>
						</div>
					</div>
					
				</div>
			</div>

			<div class="col-md-9 col-sm-9 col-xs-9" id="right">
			   <iframe name="showInfo" width="100%" height="100%"></iframe> 
			</div>

		</div>

		<div class="row" id="bottom" style="background-color:black; height: 80px">
			<div class="col-md-12" style="text-align: center; line-height: 80px; color: red">
			&copy;版权信息归xx所有 当前在线人数：${applicationScope.online}
			</div>
		</div>

	</div>
	<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>