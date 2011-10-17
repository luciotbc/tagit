<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tag-It</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script src="http://www.google.com/jsapi" type="text/javascript"></script>
<script src="/js/jquery.min.js" type="text/javascript"></script>
<script src="/js/msdropdown/js/jquery.dd.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/js/msdropdown/dd.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/includes/menubar.jsp" />
	<jsp:include page="/WEB-INF/jsp/includes/msg_error.jsp" />
	<jsp:include page="/WEB-INF/jsp/includes/msg_flash.jsp" />
	<div id="reports">
		<div id="evaluation_userlist">
			<ul>
				<c:forEach items="${tagging}" var="tagging">
					<li><a href="/report/tagging/${tagging.value.id}"><img src="/images/icons/report.png" alt="Relatório" title="Relatório">${tagging.key.name}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>
