<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:message code="menu.users"/></title>
</head>
<body>
<%@include file="/WEB-INF/incl/menu.app" %>
<h2><s:message code="menu.users"/></h2>
<table width="1000" border="0" cellpadding="6" cellspacing="2">
	<tr bgcolor="#ffddcc">
		<td width="40" align="center"><b><s:message code="admin.user.id"/></b></td>
		<td width="190" align="center"><b><s:message code="register.name"/></b></td>
		<td width="190" align="center"><b><s:message code="register.lastName"/></b></td>
		<td width="200" align="center"><b><s:message code="register.email"/></b></td>
		<td width="100" align="center"><b><s:message code="profil.czyAktywny"/></b></td>
		<td width="190" align="center"><b><s:message code="profil.rola"/></b></td>
	</tr>
	<c:forEach var="u" items="${userList }">
	<tr>
			<td><c:out value="${u.id }" /></td>
			<td><c:out value="${u.name }" /></td>
			<td><c:out value="${u.lastName }" /></td>
			<td><c:out value="${u.email }" /></td>
			<td align="center">
				<c:choose>
					<c:when test="${u.active == 1 }">
						<font color="green"><s:message code="word.tak"/></font>
					</c:when>
					<c:otherwise>
						<font color="red"><s:message code="word.nie"/></font>
					</c:otherwise>
				</c:choose>
			</td>
			<td align="center">
			<c:choose>
				<c:when test="${u.nrRoli == 1 }">
					<font color="green"><s:message code="word.admin"/></font>
				</c:when>
				<c:otherwise>
					<s:message code="word.user"/>
				</c:otherwise>
			</c:choose>
			</td>
			<td>
			<c:choose>
				<c:when test="${u.nrRoli == 1 }">
					<img src="/resources/images/deliconinact.png" width="16" height="16"/>
				</c:when>
				<c:otherwise>
					<a href="delete/${u.id }">
						<img src="/resources/images/delicon.png" width="16" height="16" title="<s:message code="delete.user"/>"/>
					</a>
				</c:otherwise>
			</c:choose>
			</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>