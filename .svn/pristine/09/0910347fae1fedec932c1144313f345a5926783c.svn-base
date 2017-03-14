<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品类型管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#levelparent").hide();
			$("#levelparent2").hide();
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
		
	function	typeparent(obj){
		var type=$(obj).val();
		if(type=='2')
		{
			$("#levelparent").show();
			$("#levelparent2").hide();
		}
		else if(type=='3')
		{
			$("#levelparent2").show();
			$("#levelparent").hide();
		}
		else
		{
			$("#levelparent2").hide();
			$("#levelparent").hide();
		}
			
}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sptype/cCMSpType/">商品类型列表</a></li>
		<li class="active"><a href="${ctx}/sptype/cCMSpType/form?id=${cCMSpType.id}">商品类型<shiro:hasPermission name="sptype:cCMSpType:edit">${not empty cCMSpType.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sptype:cCMSpType:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="CCMSpType" action="${ctx}/sptype/cCMSpType/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">商品类别名称：</label>
			<div class="controls">
				<form:input path="spTypeName" htmlEscape="false" maxlength="10" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品类别级别：</label>
			<div class="controls">
				<select name="spTypeLevel" onchange='typeparent(this)'>
					      <option value="1" <c:if test="${cCMSpType.spTypeLevel=='1'}">selected</c:if>>一级菜单</option>
					      <option value="2" <c:if test="${cCMSpType.spTypeLevel=='2'}">selected</c:if>>二级菜单</option>
					      <option value="3" <c:if test="${cCMSpType.spTypeLevel=='3'}">selected</c:if>>三级菜单</option>
                </select> 
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group" id="levelparent">
			<label class="control-label">上级类别代码：</label>
			<div class="controls">
				<form:select path="spTypeParent1" class="input-xxlarge">
							<form:options items="${levelList}" itemLabel="spTypeName" itemValue="spTypeCode" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group" id="levelparent2">
			<label class="control-label">上级类别代码：</label>
			<div class="controls">
				<form:select path="spTypeParent2" class="input-xxlarge">
							<form:options items="${levelList2}" itemLabel="spTypeName" itemValue="spTypeCode" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label">所属商户：</label>
			<div class="controls">
				<form:select path="shopName" class="input-xxlarge">
							<form:options items="${cCMShopList}" itemLabel="shopName" itemValue="shopName" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sptype:cCMSpType:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>