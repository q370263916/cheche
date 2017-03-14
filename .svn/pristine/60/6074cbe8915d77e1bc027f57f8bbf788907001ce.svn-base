<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员充值记录管理</title>
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
		<li class="active"><a href="${ctx}/recharge/cCMUserRecharge/">会员充值记录列表</a></li>
<%--
		<shiro:hasPermission name="recharge:cCMUserRecharge:edit"><li><a href="${ctx}/recharge/cCMUserRecharge/form">会员充值记录添加</a></li></shiro:hasPermission>
--%>
	</ul>
	<form:form id="searchForm" modelAttribute="CCMUserRecharge" action="${ctx}/recharge/cCMUserRecharge/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员编码：</label>
				<form:input path="userCode" htmlEscape="false" maxlength="36" class="input-medium"/>
			</li>
			<li><label>充值凭证号：</label>
				<form:input path="inBankOrdernum" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>会员编码</th>
				<th>资金收入时间</th>
				<th>收入金额</th>
				<th>充值支付方式</th>
				<th>充值凭证号</th>
				<th>修改时间</th>
<%--
				<shiro:hasPermission name="recharge:cCMUserRecharge:edit"><th>操作</th></shiro:hasPermission>
--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cCMUserRecharge">
			<tr>
				<td><a href="${ctx}/recharge/cCMUserRecharge/form?id=${cCMUserRecharge.id}">
					${cCMUserRecharge.userCode}
				</a></td>
				<td>
					<fmt:formatDate value="${cCMUserRecharge.inMoneyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cCMUserRecharge.inMoney}
				</td>
				<td>
					${cCMUserRecharge.inMoneyType}
				</td>
				<td>
					${cCMUserRecharge.inBankOrdernum}
				</td>
				<td>
					<fmt:formatDate value="${cCMUserRecharge.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<shiro:hasPermission name="recharge:cCMUserRecharge:edit"><td>
    				<a href="${ctx}/recharge/cCMUserRecharge/form?id=${cCMUserRecharge.id}">修改</a>
					<a href="${ctx}/recharge/cCMUserRecharge/delete?id=${cCMUserRecharge.id}" onclick="return confirmx('确认要删除该会员充值记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>