<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<jsp:useBean id="cCMSpType"  class="com.chechemotel.back.sptype.entity.CCMSpType" scope="request" ></jsp:useBean>
<html>
<head>
	<title>商品类型管理</title>
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
		<li class="active"><a href="${ctx}/sptype/cCMSpType/">商品类型列表</a></li>
		<shiro:hasPermission name="sptype:cCMSpType:edit"><li><a href="${ctx}/sptype/cCMSpType/form">商品类型添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="CCMSpType" action="${ctx}/sptype/cCMSpType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类别名称：</label>
				<form:input path="spTypeName" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>类别编码：</label>
				<form:input path="spTypeCode" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>类别级别：</label>
				<form:input path="spTypeLevel" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>上级代码：</label>
				<form:input path="spTypeParent" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>所属商户：</label>
				<form:input path="shopName" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品类别名称</th>
				<th>商品类别编码</th>
				<th>商品类别级别</th>
				<th>上级类别代码</th>
				<th>创建时间</th>
				<th>所属商户</th>
				<shiro:hasPermission name="sptype:cCMSpType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cCMSpType">
			<tr>
				<td><a href="${ctx}/sptype/cCMSpType/form?id=${cCMSpType.id}">
					${cCMSpType.spTypeName}
				</a></td>
				<td>
					${cCMSpType.spTypeCode}
				</td>
				<td>
					${cCMSpType.spTypeLevel}
				</td>
				<td>
					${cCMSpType.spTypeParent}
				</td>
				<td>
					<fmt:formatDate value="${cCMSpType.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
					<td>
					${cCMSpType.shopName}
				</td>
				<shiro:hasPermission name="sptype:cCMSpType:edit"><td>
    				<a href="${ctx}/sptype/cCMSpType/form?id=${cCMSpType.id}">修改</a>
					<a href="${ctx}/sptype/cCMSpType/delete?id=${cCMSpType.id}" onclick="return confirmx('确认要删除该商品类型吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>