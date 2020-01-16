<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" type="text/css" rel="styleSheet" />
<style type="text/css">
#form-info {
	width: 500px;
	margin: 120px auto;
}
</style>
</head>
<body>

	<%
		Cookie cs[] = request.getCookies();
		String userName = "";
		if (cs!= null) {

			for (Cookie c : cs) {
				if (c.getName().equals("uname")) {
					userName = c.getValue();

				}
				out.print(c.getName() + ":" + c.getValue());

			}

		}
	%>
	
	
	<div class="container-fluid">
		<div class="row" style="background-color: brown; color: blue; height: 100px">
			<div class="col-md-12">
				<h1 align="center">仓库管理系统</h1>
			</div>
		</div>

		<div class="row" style="height: 500px">
			<div class="col-md-12"
				style="background-image: url(img/11111.jpg); background-size: 100% 100%; height: 500px">
				<form class="form-horizontal" action="login.do" method="post"id="form-info">
					<div class="form-group">
						<label class="col-md-4 col-sm-4 control-label">用户 名：</label>
						<div class="col-md-8 ">
							<input type="text" class="form-control" required="required"
								name="userName" placeholder="请输入用户名"  value="<%=userName%>"/>
             
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-4 col-sm-4 control-label">登录密码：</label>
						<div class="col-md-8 ">
							<input type="password" class="form-control" required="required"
								name="passWord" placeholder="请输入密码" />

						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label"></label>
						<div class="col-md-6">

							<input type="submit" class="btn btn-info" value="登录" /> 
							<input type="reset" class="btn btn-info" value="取消" />

						</div>
					</div>
				</form>
			</div>
		</div>

		<div class="row" style="background-color: gray; height: 80px">
			<div class="col-md-12"
				style="text-align: center; line-height: 80px; color: red">
				&copy;版权信息归xx所有|@@|2019-12
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>