<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty flash}">
	<div id="msg_flash">
		<ul>
			<c:forEach items="${flash}" var="msg">
				<%-- <li>${msg.category} - ${msg.message}</li> --%>
				<li>${msg.message}</li>
			</c:forEach>
		</ul>
	</div>
	</c:if>