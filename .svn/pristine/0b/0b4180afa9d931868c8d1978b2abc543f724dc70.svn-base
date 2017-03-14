<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户信息管理管理</title>
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
		<li class="active"><a href="${ctx}/shopinfo/cCMShopInfo/">商户信息管理列表</a></li>
		<shiro:hasPermission name="shopinfo:cCMShopInfo:edit"><li><a href="${ctx}/shopinfo/cCMShopInfo/form">商户信息管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="CCMShopInfo" action="${ctx}/shopinfo/cCMShopInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>公司名称：</label>
				<form:input path="shopName" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>联系人姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>联系人电话：</label>
				<form:input path="tel" htmlEscape="false" maxlength="16" class="input-medium"/>
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
				<th>详细地址</th>
				<th>创建时间</th>
				<%--<th>修改时间</th>--%>
				<th>营业执照编号</th>
				<th>税务登记证号</th>
				<th>联系人姓名</th>
				<th>联系人电话</th>
				<th>备注</th>
				<%--<th>类别编码</th>--%>
				<th>商户余额</th>
				<%--<th>营业执照图片字段</th>--%>
				<shiro:hasPermission name="shopinfo:cCMShopInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cCMShopInfo">
			<tr>
				<td><a href="${ctx}/shopinfo/cCMShopInfo/form?id=${cCMShopInfo.id}">
					${cCMShopInfo.shopCode}
				</a></td>
				<td>
					${cCMShopInfo.shopName}
				</td>
				<td>
					${cCMShopInfo.shopAddress}
				</td>
				<td>
					<fmt:formatDate value="${cCMShopInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<td>
					<fmt:formatDate value="${cCMShopInfo.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>--%>
				<td>
					${cCMShopInfo.bussinessLicence}
				</td>
				<td>
					${cCMShopInfo.taxRegistrationCertificate}
				</td>
				<td>
					${cCMShopInfo.name}
				</td>
				<td>
					${cCMShopInfo.tel}
				</td>
				<td>
					${cCMShopInfo.content}
				</td>
				<%--<td>
					${cCMShopInfo.shopType}
				</td>--%>
				<td>
					${cCMShopInfo.money}
				</td>
				<%--<td>
					${cCMShopInfo.bussinessLicencePic}
				</td>--%>
				<shiro:hasPermission name="shopinfo:cCMShopInfo:edit"><td>
    				<a href="${ctx}/shopinfo/cCMShopInfo/form?id=${cCMShopInfo.id}">修改</a>
					<a href="${ctx}/shopinfo/cCMShopInfo/delete?id=${cCMShopInfo.id}" onclick="return confirmx('确认要删除该商户信息管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>