<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户信息管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/shopinfo/cCMShopInfo/">商户信息管理列表</a></li>
		<li class="active"><a href="${ctx}/shopinfo/cCMShopInfo/form?id=${cCMShopInfo.id}">商户信息管理<shiro:hasPermission name="shopinfo:cCMShopInfo:edit">${not empty cCMShopInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="shopinfo:cCMShopInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="CCMShopInfo" action="${ctx}/shopinfo/cCMShopInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">公司编码：</label>
			<div class="controls">
				<form:input path="shopCode" htmlEscape="false" maxlength="6" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司名称：</label>
			<div class="controls">
				<form:input path="shopName" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">详细地址：</label>
			<div class="controls">
				<form:input path="shopAddress" htmlEscape="false" maxlength="120" class="input-xlarge "/>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cCMShopInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">修改时间：</label>
			<div class="controls">
				<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cCMShopInfo.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">营业执照编号：</label>
			<div class="controls">
				<form:input path="bussinessLicence" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">税务登记证号：</label>
			<div class="controls">
				<form:input path="taxRegistrationCertificate" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人电话：</label>
			<div class="controls">
				<form:input path="tel" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="120" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类别编码：</label>
			<div class="controls">
				<%--<form:input path="shopType" htmlEscape="false" maxlength="5" class="input-xlarge "/>--%>
				<%--<form:select path="shopType" class="required input-xlarge">
					<form:options items="${shopTypeList}" itemLabel="typeName" itemValue="typeCode" htmlEscape="false"/>
				</form:select>--%>
<<<<<<< .mine
				<%--<form:checkboxes path="shopType" items="${shopTypeMap}"></form:checkboxes>--%>
				<form:checkboxes path="shopType" items="${shopTypeList}" itemLabel="typeName" itemValue="typeCode"></form:checkboxes>
||||||| .r119
				<form:checkboxes path="shopType" items="${shopTypeMap}"></form:checkboxes>
				<%--<form:checkboxes path="shopType" items="${shopTypeList}" itemLabel="typeName" itemValue="typeCode"></form:checkboxes>--%>
=======
				<%--<form:checkboxes path="shopType" items="${shopTypeMap}"></form:checkboxes>--%>
                <%--<form:checkboxes path="shopType" items="${shopTypeList}" itemLabel="typeName" itemValue="typeCode"></form:checkboxes>--%>
                    <c:forEach items="${shopTypeList}" var="list" varStatus="i">
                        <input type="checkbox" name="shopType" value="${list.typeCode}"
                        <c:forEach items="${shopTypeList2}" var="list2">
                            <c:if test="${list.typeCode==list2}"> checked="checked" </c:if>
                        </c:forEach>
                         />
                        ${list.typeName}
                    </c:forEach>

>>>>>>> .r143
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商户余额：</label>
			<div class="controls">
				<form:input path="money" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">营业执照图片字段：</label>
			<div class="controls">
				<form:input path="bussinessLicencePic" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">大图标：</label>
			<div class="controls">
				<form:input path="spImg" htmlEscape="false" maxlength="300" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">小图标：</label>
			<div class="controls">
				<form:input path="spIcon" htmlEscape="false" maxlength="300" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="shopinfo:cCMShopInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>