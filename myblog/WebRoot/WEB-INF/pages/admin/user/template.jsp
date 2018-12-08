<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@include file="/WEB-INF/pages/common/taglib.jsp"%>
<c:forEach items="${users}" varStatus="userindex" var="user">
	<tr>
		<td>${user.id} <c:if test="${userindex.index==0}">
				<input type="hidden" id="itemCount" value="${itemCount}" />
			</c:if>
		</td>
		<td>${user.username}</td>
		<td>${user.email}</td>
		<td>${user.telephone}</td>
		<td class="tmui-tips"
			data-tip="${wp:formatDate(user.createTime,'yyyy-MM-dd mm:hh:ss')}">
			${wp:timeFormat(user.createTime)}</td>
		<td><a href="javascript:void(0);" data-opid="${user.id}" data-state="${user.forbiden==1?0:1}" data-mark="forbid" onclick="wpAdmin.updateUser(this)" >${user.forbiden==1?"禁止":"已禁止"}</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" data-opid="${user.id}" data-state="${user.isDelete==0?1:0}" data-mark="del" onclick="wpAdmin.updateUser(this)" >${user.isDelete==0?"删除":"已删除"}</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" data-opid="${user.id}" data-mark="update" onclick="updateUser(this)" >修改</a></td>
	</tr>
</c:forEach>

