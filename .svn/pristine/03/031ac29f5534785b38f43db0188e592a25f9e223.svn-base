<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户权限管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var shopName = $("#shopCode option:selected").text();
			$("#shopName").val(shopName);
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});

		function setShopNameAndCode(){
			//var shopCode = $("#shopCode option:selected").val();
			var shopName = $("#shopCode option:selected").text();
			//alert(shopCode);
			//alert(shopName);
			$("#shopName").val(shopName);
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/shoprole/cCMShopRole/">商户权限管理列表</a></li>
		<li class="active"><a href="${ctx}/shoprole/cCMShopRole/form?id=${cCMShopRole.id}">商户权限管理<shiro:hasPermission name="shoprole:cCMShopRole:edit">${not empty cCMShopRole.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="shoprole:cCMShopRole:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="CCMShopRole" action="${ctx}/shoprole/cCMShopRole/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<%--<div class="control-group">
			<label class="control-label">公司编码：</label>
			<div class="controls">
				<form:input path="shopCode" htmlEscape="false" maxlength="6" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>--%>
		<%--<div class="control-group">
			<label class="control-label">公司名称：</label>
			<div class="controls">
				<form:input path="shopName" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>--%>
		<input type="hidden" id="shopName" name="shopName" value="${CCMShopRole.shopName}">
		<%--<input type="hidden" name="shopCode" value="${CCMShopRole.shopCode}">--%>
		<div class="control-group">
			<label class="control-label">公司名称：</label>
			<div class="controls">
				<form:select path="shopCode" class="required input-xlarge" onchange="setShopNameAndCode();">
					<form:options items="${shopInfoSelect}" itemLabel="shopName" itemValue="shopCode" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">第三方用户名：</label>
			<div class="controls">
				<form:input path="username" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">第三方用户密码：</label>
			<div class="controls">
				<%--<form:input path="password" htmlEscape="false" maxlength="32" class="input-xlarge required"/>--%>
				<form:password path="password" maxlength="32" showPassword="true" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">真实姓名：</label>
			<div class="controls">
				<form:input path="realname" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电话号：</label>
			<div class="controls">
				<form:input path="tel" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:input path="flag" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>--%>
		<%--<div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cCMShopRole.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">修改时间：</label>
			<div class="controls">
				<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cCMShopRole.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">类别编码：</label>
			<div class="controls">
				<form:input path="shopType" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="shoprole:cCMShopRole:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>