<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户类别管理</title>
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
		<li class="active"><a href="${ctx}/shoptype/cCMShopType/">商户类别列表</a></li>
		<shiro:hasPermission name="shoptype:cCMShopType:edit"><li><a href="${ctx}/shoptype/cCMShopType/form">商户类别添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="CCMShopType" action="${ctx}/shoptype/cCMShopType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类别名称：</label>
				<form:input path="typeCode" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>类别编码</th>
				<th>类别名称</th>
				<th>创建时间</th>
				<%--<th>修改时间</th>--%>
				<shiro:hasPermission name="shoptype:cCMShopType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cCMShopType">
			<tr>
				<td><a href="${ctx}/shoptype/cCMShopType/form?id=${cCMShopType.id}">
					${cCMShopType.typeCode}
				</a></td>
				<td>
					${cCMShopType.typeName}
				</td>
				<td>
					<fmt:formatDate value="${cCMShopType.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<td>
					<fmt:formatDate value="${cCMShopType.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>--%>
				<shiro:hasPermission name="shoptype:cCMShopType:edit"><td>
    				<a href="${ctx}/shoptype/cCMShopType/form?id=${cCMShopType.id}">修改</a>
					<a href="${ctx}/shoptype/cCMShopType/delete?id=${cCMShopType.id}" onclick="return confirmx('确认要删除该商户类别吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>