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
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/includes/menubar.jsp" />
	<jsp:include page="/WEB-INF/jsp/includes/msg_error.jsp" />
	<jsp:include page="/WEB-INF/jsp/includes/msg_flash.jsp" />
	<div id="evaluation_add">
		<h1>Nova Avaliação</h1>
		<form action="<c:url value='/evaluation/save'/>" method="post">
		<table>
			<tr>
				<td><label class="label" for="name">Nome da avaliação:</label></td>
				<td><input id="name" class="text_box" type="text" title="Nome da avaliação" name="evaluation.name"></td>
				
			</tr>
			<tr>
				<td><label class="label" for="appName">Nome do aplicativo:</label></td>
				<td><input id="appName" class="text_box" type="text" title="Nome do aplicativo" name="evaluation.appName"></td>
			</tr>
			<tr>
				<td><label class="label" for="appDescription">Descrição da aplicação:</label></td>
				<td><textarea id="appDescription" class="text_box" type="text" title="Descrição da aplicação" name="evaluation.appDescription"></textarea></td>
			</tr>
			<tr>
				<td><label class="label" for="goalEvaluation">Objetivo da avaliação:</label></td>
				<td><textarea id="goalEvaluation" class="text_box" type="text" title="Objetivo da aplicação" name="evaluation.goalEvaluation"></textarea></td>
			</tr>
			<tr>
				<td><label class="label" for="video">Link para o vídeo no YouTube:</label></td>
				<td><input id="video" class="text" type="text" title="Link para o vídeo no YouTube" name="evaluation.video"></td>
			</tr>
			<tr>
				<td></td>
				<td><input id="button_save" class="button" type="submit" title="Salvar avaliação" value="Salvar avaliação"></td>
			</tr>
		</table>
		</form>
	</div>
	<jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
</body>
</html>
