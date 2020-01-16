<%@page import="com.woniu.beans.PageBeans"%>
<%@page import="java.util.List"%>
<%@page import="com.woniu.beans.Goods"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" type="text/css" rel="styleSheet" />
</head>
<body>
    <form action="<%=request.getContextPath()%>/goods/goods.do" method="post" name="goForm" >
    <div style="width: 900px; margin: 20px auto">
                       商品名称：<input type="text" name="goodsName" value="${requestScope.gqb.goods_name}"/>
                       商品编号：<input type="text" name="goodsCode" value="${requestScope.gqb.goods_code}"/>
       <input type="submit" value="查询"/>
    </div>                 
       
	<div style="width: 900px; margin: 20px auto; text-align: right;">
		<a href="<%=request.getContextPath()%>/goods/goodsAddUI.do" class="btn btn-info ">增加
		    <span class="glyphicon glyphicon-plus"></span>
        </a>
	</div>
	<table
		class="table table-bordered table-striped table-hover table-condensed"
		style="width: 900px;" align="center">
		<thead>
			<tr class="success">
			    <th>商品编号</th>
				<th>商品名称</th>
				<th>商品类别</th>
				<th>商品生产商</th>
				<th>出厂日期</th>
				<th>商品价格</th>
				<th>商品库存量</th>
				<th>质量等级</th>
				<th>规格型号</th>
				<th>备注信息</th>
				<th>操作事项</th>
			</tr>
		</thead>

		<tbody>
		  
            <c:forEach items="${requestScope.pab.data}" var="g">
			<tr align="center">
                <td>${g.goods_code}</td>
				<td>${g.goods_name}</td>
				<td>${g.types.types_name}</td>
				<td>${g.suppliers.suppliers_name}</td>
				<td>${g.goods_date}</td>
				<td>${g.goods_price}</td>
				<td>${g.goods_count}</td>
				<td>${g.goods_level}</td>
				<td>${g.goods_spec}</td>
				<td>${g.goods_remarks}</td>
				<td>
				    <a href="<%=request.getContextPath()%>/goods/goodsDelete.do?goodsId=${g.goods_id}" class="btn btn-danger btn-xs">删除</a>
					<a href="<%=request.getContextPath()%>/goods/goodsUpdate.do?goodsId=${g.goods_id}" class="btn btn-info btn-xs">修改</a>
                </td>
			</tr>

			</c:forEach>

			<tr>

				<td colspan="11">
				   <a href="javascript:goPage(1)">首页</a> 
				   <a href="javascript:goPage(${requestScope.pab.currentPage-1})">上一页</a> 
				   <!--循环得到第几页  -->
			       <c:forEach begin="1" end="${requestScope.pab.pages}" var="i">
		  			    
		  			    <c:choose>
		  			        <c:when test="${i==requestScope.pab.currentPage}">		
		  			      		${i}
		  			      	</c:when>
		  			      	
		  			      	<c:otherwise>
		  			      	    <a href="javascript:goPage(${i})">${i}</a>	
		  			        </c:otherwise>
		  			      			
		  			     </c:choose>
		  			     		
		  			</c:forEach>
		              
		              <a href="javascript:goPage(${requestScope.pab.currentPage+1})">下一页</a>
		              <a href="javascript:goPage(${requestScope.pab.pages})">尾页</a>
		             
		              <input type="number" onchange="goPage(1)" name="pageSize" value="${requestScope.pab.pageSize}" style="width:100px"/> 
		                                               共有${requestScope.pab.totalCount}条
		                                                共有${requestScope.pab.pages}页                                
		                                                当前页是第 ${requestScope.pab.currentPage}页
		            </td>

			</tr>
			
		</tbody>

	</table>
	    <input type="hidden" name="currPage" value="${requestScope.pab.currentPage}"/>
	    
	</form>

	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

	
	<script type="text/javascript">
	   //提交表单
	   function goPage(currPageValue){
		    //将当前页的信息设置到隐藏域中
  		   goForm.currPage.value=currPageValue;
		   goForm.submit();
	   }
	
	</script>

</body>
</html>