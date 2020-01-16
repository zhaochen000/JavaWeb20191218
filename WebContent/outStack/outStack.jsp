<%@page import="com.woniu.beans.PageBeans"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
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

    <form action="<%=request.getContextPath()%>/outStack/outStack.do" method="post" name="goForm" >
    <div style="width: 800px; margin: 20px auto">
                       出库单编号：<input type="text" name="outStackCode" value="${requestScope.queryMap.sCode}"/>
                       商品名称：<input type="text" name="goodsName" value="${requestScope.queryMap.gName}"/>
       <input type="submit" value="查询"/>
    </div>                 
       
	<div style="width: 800px; margin: 20px auto; text-align: right;">
		<a href="<%=request.getContextPath()%>/outStack/outStackAddUI.do" class="btn btn-info ">增加
		    <span class="glyphicon glyphicon-plus"></span>
        </a>
	</div>
	<table
		class="table table-bordered table-striped table-hover table-condensed"
		style="width: 800px;" align="center">
		<thead>
			<tr class="success">
			    <th>出库单编号</th>
				<th>出库商品</th>
				<th>出库人</th>
				<th>出库数量</th>
				<th>出库时间</th>
				<th>出库状态</th>
				<th>出库备注</th>
				<th>操作事项</th>
			</tr>
		</thead>

		<tbody>
		  
            <c:forEach items="${requestScope.pab.data}" var="out">
			<tr align="center">
                <td>${out.outStack_code}</td>
				<td>${out.goods.goods_name}</td>
				<td>${out.user_id}</td>
				<td>${out.outStack_count}</td>
				<td>${out.outStack_time}</td>
				<td>${out.outStack_status}</td>
				<td>${out.outStack_remarks}</td>
				<td>
				    <c:if test="${out.outStack_status=='未确认'}">
				    
				    <a href="<%=request.getContextPath()%>/outStack/outStackDel.do?outStackId=${out.outStack_id}" class="btn btn-danger btn-xs">删除</a>
					<a href="<%=request.getContextPath()%>/outStack/outStackUpd.do?outStackId=${out.outStack_id}" class="btn btn-info btn-xs">修改</a>
                    <a href="<%=request.getContextPath()%>/outStack/inStackConfirm.do?outStackId=${out.outStack_id}&goodsId=${out.goods_id}&outStackCount=${out.outStack_count}" class="btn btn-info btn-xs">确认</a>
                   
                    </c:if>
                </td>
                
               
			</c:forEach>

			<tr>

				<td colspan="8">
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
		             &nbsp;&nbsp;&nbsp;
		             <select name="pageSize" onchange="goPage(1)">
		             
		                <option value="3">3</option>
		                <option value="4">4</option>
		                <option value="6">6</option>
		                <option value="8">8</option>
		             
		             </select>
		              &nbsp;&nbsp;&nbsp;
		                                               共有${requestScope.pab.totalCount}条
		              &nbsp;&nbsp;&nbsp;
		                                                共有${requestScope.pab.pages}页                     
		               &nbsp;&nbsp;&nbsp;           
		                                                当前页是第 ${requestScope.pab.currentPage}页
		           
		            </td>

			</tr>
		
		</tbody>

	</table>
	     <input type="hidden" name="cutPage" value="${requestScope.pab.currentPage}"/>
	</form>

	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script type="text/javascript"> 
    
       function goPage(cutpageValue){
    	   //获得隐藏域中的值
    	   goForm.cutPage.value  = cutpageValue;
    	   //让表单提交
    	   goForm.submit();
    	   
       }
         
       $("[name=pageSize] option[value=${requestScope.pab.pageSize}]").attr("selected","selected");

    </script>

      
</body>
</html>