<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理管理</title>
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
		<li class="active"><a href="${ctx}/user/cCMUser/">会员管理列表</a></li>
		<shiro:hasPermission name="user:cCMUser:edit"><li><a href="${ctx}/user/cCMUser/form">会员管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="CCMUser" action="${ctx}/user/cCMUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员编码：</label>
				<form:input path="userCode" htmlEscape="false" maxlength="36" class="input-medium"/>
			</li>
			<li><label>帐户名：</label>
				<form:input path="accountName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="phone" htmlEscape="false" maxlength="16" class="input-medium"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="realName" htmlEscape="false" maxlength="40" class="input-medium"/>
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
				<th>帐户名</th>
				<th>手机号</th>
				<th>真实姓名</th>
				<th>账户余额</th>
				<th>用户二维码路径</th>
				<th>创建时间</th>
				<th>修改时间</th>
				<th>身份证号</th>
				<th>积分</th>
				<th>消费总额</th>
				<th>性别</th>
				<th>头像路径</th>
				<th>红包总额</th>
				<th>可提现红包总额</th>
				<th>红包总数</th>
				<th>会员状态</th>
				<th>推荐码</th>
				<shiro:hasPermission name="user:cCMUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cCMUser">
			<tr>
				<td><a href="${ctx}/user/cCMUser/form?id=${cCMUser.id}">
					${cCMUser.userCode}
				</a></td>
				<td>
					${cCMUser.accountName}
				</td>
				<td>
					${cCMUser.phone}
				</td>
				<td>
					${cCMUser.realName}
				</td>
				<td>
					${cCMUser.money}
				</td>
				<td>
					${cCMUser.qrCode}
				</td>
				<td>
					<fmt:formatDate value="${cCMUser.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${cCMUser.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${cCMUser.card}
				</td>
				<td>
					${cCMUser.jifen}
				</td>
				<td>
					${cCMUser.zje}
				</td>
				<td>
					<c:if test="${cCMUser.sex==0}">男</c:if>
					<c:if test="${cCMUser.sex==1}">女</c:if>
				</td>
				<td>
					${cCMUser.headImg}
				</td>
				<td>
					${cCMUser.redBegZje}
				</td>
				<td>
					${cCMUser.canTxZje}
				</td>
				<td>
					${cCMUser.regBagCount}
				</td>
				<td>
					${cCMUser.status}
				</td>
				<td>
					${cCMUser.tjm}
				</td>
				<shiro:hasPermission name="user:cCMUser:edit"><td>
    				<a href="${ctx}/user/cCMUser/form?id=${cCMUser.id}">修改</a>
					<a href="${ctx}/user/cCMUser/delete?id=${cCMUser.id}" onclick="return confirmx('确认要删除该会员管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>