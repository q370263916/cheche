<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户权限管理管理</title>
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
		<li class="active"><a href="${ctx}/shoprole/cCMShopRole/">商户权限管理列表</a></li>
		<shiro:hasPermission name="shoprole:cCMShopRole:edit"><li><a href="${ctx}/shoprole/cCMShopRole/form">商户权限管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="CCMShopRole" action="${ctx}/shoprole/cCMShopRole/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>公司编码：</label>
				<form:input path="shopCode" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>公司名称：</label>
				<form:input path="shopName" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>电话号：</label>
				<form:input path="tel" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>公司编码</th>
				<th>公司名称</th>
				<th>第三方用户名</th>
				<%--<th>第三方用户密码</th>--%>
				<th>真实姓名</th>
				<th>电话号</th>
				<th>创建时间</th>
				<%--<th>修改时间</th>--%>
				<%--<th>类别编码</th>--%>
				<shiro:hasPermission name="shoprole:cCMShopRole:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cCMShopRole">
			<tr>
				<td><a href="${ctx}/shoprole/cCMShopRole/form?id=${cCMShopRole.id}">
					${cCMShopRole.shopCode}
				</a></td>
				<td>
					${cCMShopRole.shopName}
				</td>
				<td>
					${cCMShopRole.username}
				</td>
				<%--<td>
					${cCMShopRole.password}
				</td>--%>
				<td>
					${cCMShopRole.realname}
				</td>
				<td>
					${cCMShopRole.tel}
				</td>
				<td>
					<fmt:formatDate value="${cCMShopRole.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<td>
					<fmt:formatDate value="${cCMShopRole.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>--%>
				<%--<td>
					${cCMShopRole.shopType}
				</td>--%>
				<shiro:hasPermission name="shoprole:cCMShopRole:edit"><td>
    				<a href="${ctx}/shoprole/cCMShopRole/form?id=${cCMShopRole.id}">修改</a>
					<a href="${ctx}/shoprole/cCMShopRole/delete?id=${cCMShopRole.id}" onclick="return confirmx('确认要删除该商户权限管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>