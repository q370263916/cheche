<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理管理</title>
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
		<li class="active"><a href="${ctx}/sp/cCMSp/">商品管理列表</a></li>
		<shiro:hasPermission name="sp:cCMSp:edit"><li><a href="${ctx}/sp/cCMSp/form">商品管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="CCMSp" action="${ctx}/sp/cCMSp/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品编码：</label>
				<form:input path="spCode" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>商品名称：</label>
				<form:input path="spName" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品编码</th>
				<th>商品名称</th>
				<th>商品类别id</th>
				<th>折扣</th>
				<th>库存数量</th>
				<th>价格</th>
				<th>公司编码</th>
				<th>创建时间</th>
				<th>修改时间</th>
				<th>商品的图标</th>
				<th>消费后的积分</th>
				<th>红包分值</th>
				<th>商户名称</th>
				<shiro:hasPermission name="sp:cCMSp:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cCMSp">
			<tr>
				<td><a href="${ctx}/sp/cCMSp/form?id=${cCMSp.id}">
					${cCMSp.spCode}
				</a></td>
				<td>
					${cCMSp.spName}
				</td>
				<td>
					${cCMSp.spTypeCode}
				</td>
				<td>
					${cCMSp.zhekou}
				</td>
				<td>
					${cCMSp.kuCun}
				</td>
				<td>
					${cCMSp.price}
				</td>
				<td>
					${cCMSp.shopCode}
				</td>
				<td>
					<fmt:formatDate value="${cCMSp.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${cCMSp.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cCMSp.spImg}
				</td>
				<td>
					${cCMSp.jifen}
				</td>
				<td>
					${cCMSp.redbagVal}
				</td>
				<td>
					${cCMSp.shopName}
				</td>
				<shiro:hasPermission name="sp:cCMSp:edit"><td>
    				<a href="${ctx}/sp/cCMSp/form?id=${cCMSp.id}">修改</a>
					<a href="${ctx}/sp/cCMSp/delete?id=${cCMSp.id}" onclick="return confirmx('确认要删除该商品管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>