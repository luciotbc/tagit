<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty errors}">
	<div id="msg_error">
		<ul>
			<c:forEach items="${errors}" var="error">
<%-- 				<li>${error.category} - ${error.message}</li> --%>
				<li>${error.message}</li>
			</c:forEach>
		</ul>
	</div>
</c:if>