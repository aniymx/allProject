<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@include file="/WEB-INF/pages/common/taglib.jsp"%>
<c:forEach items="${roles}" varStatus="roleindex" var="role">
	<tr>
		<td>${role.id} <c:if test="${roleindex.index==0}">
				<input type="hidden" id="itemCount" value="${itemCount}" />
			</c:if>
		</td>
		<td>${role.name}</td>
		<%-- <td>${role.userId}</td> --%>
		<td class="tmui-tips"
			data-tip="${wp:formatDate(role.createTime,'yyyy-MM-dd mm:hh:ss')}">
			${wp:timeFormat(role.createTime)}</td>
		<td>
			<wp:if test="${role.isDelete==1}">
				<wp:then><a href="javascript:void(0)" class="red" data-mark="isDelete" data-opid="${role.id}" data-val="1" onclick="wpAdmin.op(this)" >删除</a></wp:then>
				<wp:else><a href="javascript:void(0)" class="green" data-mark="isDelete" data-opid="${role.id}" data-val="0" onclick="wpAdmin.op(this)" >未删除</a></wp:else>
			</wp:if>
		</td>
		<td><a href="javascript:void(0);" data-opid="${role.id}"  onclick="wpRole.user(this)" >分配用户</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" data-opid="${role.id}"  onclick="wpRole.permission(this)" >分配权限</a></td>
	</tr>
</c:forEach>

