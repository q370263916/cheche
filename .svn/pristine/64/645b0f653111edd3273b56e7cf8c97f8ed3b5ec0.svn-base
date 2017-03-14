<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单流水管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/orderstream/cCMOrderStream/">订单流水列表</a></li>
<%--
		<shiro:hasPermission name="orderstream:cCMOrderStream:edit"><li><a href="${ctx}/orderstream/cCMOrderStream/form">订单流水添加</a></li></shiro:hasPermission>
--%>
	</ul>
	<%--<form:form id="searchForm" modelAttribute="CCMOrderStream" action="${ctx}/orderstream/cCMOrderStream/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>--%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单号</th>
				<th>下单时间</th>
				<th>商品类别</th>
				<th>商品编码</th>
				<th>商品名称</th>
				<th>订单总价</th>
				<th>状态</th>
				<th>修改时间</th>
				<th>商户code</th>
				<th>录入时间戳</th>
				<th>会员编码</th>
				<th>帐户名</th>
				<%--<shiro:hasPermission name="orderstream:cCMOrderStream:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cCMOrderStream">
			<tr>
				<td><a href="${ctx}/orderstream/cCMOrderStream/form?id=${cCMOrderStream.id}">
					${cCMOrderStream.orderCode}
				</a></td>
				<td>
					<fmt:formatDate value="${cCMOrderStream.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cCMOrderStream.spTypeCode}
				</td>
				<td>
					${cCMOrderStream.spCode}
				</td>
				<td>
					${cCMOrderStream.soName}
				</td>
				<td>
					${cCMOrderStream.ddZj}
				</td>
				<td>
					${cCMOrderStream.status}
				</td>
				<td>
					<fmt:formatDate value="${cCMOrderStream.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cCMOrderStream.shopCode}
				</td>
				<td>
					${cCMOrderStream.currentTimeStamp}
				</td>
				<td>
					${cCMOrderStream.userCode}
				</td>
				<td>
					${cCMOrderStream.accountName}
				</td>
				<%--<shiro:hasPermission name="orderstream:cCMOrderStream:edit"><td>
    				<a href="${ctx}/orderstream/cCMOrderStream/form?id=${cCMOrderStream.id}">修改</a>
					<a href="${ctx}/orderstream/cCMOrderStream/delete?id=${cCMOrderStream.id}" onclick="return confirmx('确认要删除该订单流水吗？', this.href)">删除</a>
				</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>