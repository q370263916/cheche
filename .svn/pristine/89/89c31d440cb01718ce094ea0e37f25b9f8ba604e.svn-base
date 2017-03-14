<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			var shopName = $("#shopCode option:selected").text();
			$("#shopName").val(shopName);

			var shopCode = $("#shopCode option:selected").val();
			$.ajax({
				type: "POST",
				url: "${ctx}/sp/cCMSp/getSpTypeByCode",
				data: {
					"shopCode":shopCode
				},
				async: false,
				dataType: "json",
				success: function(data){
					//console.log(data);
					var html = "<select name='spTypeCode' id='spTypeCode'>";
					for(var i=0;i<data.length;i++){
						html+="<option value='"+data[i].spTypeCode+"'>"+data[i].spTypeName+"</option>";
					}
					html+="</select>";
					document.getElementById('spTypeCodeSelectId').innerHTML = html;
				}

			});

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
			var shopName = $("#shopCode option:selected").text();
			$("#shopName").val(shopName);

			var shopCode = $("#shopCode option:selected").val();
			$.ajax({
				type: "POST",
				url: "${ctx}/sp/cCMSp/getSpTypeByCode",
				data: {
					"shopCode":shopCode
				},
				async: false,
				dataType: "json",
				success: function(data){
					//console.log(data);
					var html = "<select name='spTypeCode' id='spTypeCode'>";
					for(var i=0;i<data.length;i++){
						html+="<option value='"+data[i].spTypeCode+"'>"+data[i].spTypeName+"</option>";
					}
					html+="</select>";
					document.getElementById('spTypeCodeSelectId').innerHTML = html;
				}

			});

		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sp/cCMSp/">商品管理列表</a></li>
		<li class="active"><a href="${ctx}/sp/cCMSp/form?id=${cCMSp.id}">商品管理<shiro:hasPermission name="sp:cCMSp:edit">${not empty cCMSp.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sp:cCMSp:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="CCMSp" action="${ctx}/sp/cCMSp/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<input type="hidden" id="shopName" name="shopName" value="${CCMSp.shopName}">
		<div class="control-group">
			<label class="control-label">商户名称：</label>
			<div class="controls">
				<%--<form:input path="shopCode" htmlEscape="false" maxlength="10" class="input-xlarge "/>--%>
				<form:select path="shopCode" class="required input-xlarge" onchange="setShopNameAndCode();">
					<form:options items="${shopInfoSelect}" itemLabel="shopName" itemValue="shopCode" htmlEscape="false"/>
				</form:select>
			</div>
		</div>

		<%--<div class="control-group">
			<label class="control-label">商品编码：</label>
			<div class="controls">
				<form:input path="spCode" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">商品名称：</label>
			<div class="controls">
				<form:input path="spName" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品类别：</label>
			<div class="controls" id="spTypeCodeSelectId">
				<%--<form:input path="spTypeCode" htmlEscape="false" maxlength="20" class="input-xlarge required"/>--%>
				<%--<form:select path="spTypeCode" class="required input-xlarge">
					<form:options items="${shopInfoSelect}" itemLabel="shopName" itemValue="shopCode" htmlEscape="false"/>
				</form:select>--%>
				<select name="spTypeCode">
					<option value="1">1</option>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">折扣：</label>
			<div class="controls">
				<form:input path="zhekou" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">库存数量：</label>
			<div class="controls">
				<form:input path="kuCun" htmlEscape="false" maxlength="11" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">价格：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<%--<div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cCMSp.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">修改时间：</label>
			<div class="controls">
				<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${cCMSp.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">商品的图标：</label>
			<div class="controls">
				<form:input path="spImg" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">消费后的积分：</label>
			<div class="controls">
				<form:input path="jifen" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">红包分值：</label>
			<div class="controls">
				<form:input path="redbagVal" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">扩展字段1：</label>
			<div class="controls">
				<form:input path="extend1" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">扩展字段2：</label>
			<div class="controls">
				<form:input path="extend2" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">扩展字段3：</label>
			<div class="controls">
				<form:input path="extend3" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>--%>
		<%--<div class="control-group">
			<label class="control-label">商户名称：</label>
			<div class="controls">
				<form:input path="shopName" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>--%>
		<div class="form-actions">
			<shiro:hasPermission name="sp:cCMSp:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>