<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员消费记录</title>
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
		<li class="active"><a href="${ctx}/order/cCMOrder/">会员消费流水列表</a></li>
<%--
		<shiro:hasPermission name="order:cCMOrder:edit"><li><a href="${ctx}/order/cCMOrder/form">订单添加</a></li></shiro:hasPermission>
--%>
	</ul>
	<form:form id="searchForm" modelAttribute="CCMOrder" action="${ctx}/order/cCMOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单号：</label>
				<form:input path="orderCode" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>商户号：</label>
				<form:input path="shopCode" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单号</th>
				<th>下单时间</th>
				<th>订单总价</th>
				<th>商户code</th>
				<th>会员编码</th>
				<th>帐户名</th>
				<%--<shiro:hasPermission name="order:cCMOrder:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cCMUserLog">
			<tr>
				<td>
					${cCMUserLog.orderCode}
				</td>
				<td>
					${cCMUserLog.orderTime}
				</td>
				<td>
					${cCMOrderLog.ssZj}
				</td>
				<td>
					${cCMOrderLog.userCode}
				</td>
				<td>
					${cCMOrderLog.accountName}
				</td>
				<%--<shiro:hasPermission name="order:cCMOrder:edit"><td>
    				<a href="${ctx}/order/cCMOrder/form?id=${cCMOrder.id}">修改</a>
					<a href="${ctx}/order/cCMOrder/delete?id=${cCMOrder.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>