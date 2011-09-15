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
<script language="javascript" type="text/javascript" src="/js/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/includes/menubar.jsp" />
	<jsp:include page="/WEB-INF/jsp/includes/msg_error.jsp" />
	<jsp:include page="/WEB-INF/jsp/includes/msg_flash.jsp" />
	<div id="evaluation_add">
		<h1>Editar Avaliação</h1>
		<form action="<c:url value='/evaluation/save'/>" method="post">
		<table>
			<tr>
<!-- 				<td><label class="label" for="id">ID:</label></td> -->
				<td><input id="id" class="text_box" type="hidden" title="ID da avaliação" name="evaluation.id" value="${evaluation.id}"></td>
			</tr>
			<tr>
				<td><label class="label" for="name">Nome da avaliação:</label></td>
				<td><input id="name" class="text_box" type="text" title="Nome da avaliação" name="evaluation.name" value="${evaluation.name}"></td>
				
			</tr>
			<tr>
				<td><label class="label" for="appName">Nome do aplicativo:</label></td>
				<td><input id="appName" class="text_box" type="text" title="Nome do aplicativo" name="evaluation.appName" value="${evaluation.appName}"></td>
			</tr>
			<tr>
				<td><label class="label" for="appDescription">Descrição da aplicação:</label></td>
				<td><textarea id="appDescription" style="width: 502px;" class="text_box" title="Descrição da aplicação" name="evaluation.appDescription">${evaluation.appDescription}</textarea></td>
			</tr>
			<tr>
				<td><label class="label" for="goalEvaluation">Objetivo da avaliação:</label></td>
				<td><textarea id="goalEvaluation" style="width: 502px;" class="text_box" title="Objetivo da aplicação" name="evaluation.goalEvaluation">${evaluation.goalEvaluation}</textarea></td>
			</tr>
			<tr>
				<td><label class="label" for="video">Link para o vídeo no YouTube:</label></td>
				<td><input id="video" class="text" style="width:308px; margin: 0px 5px 5px 0px;" type="text" title="Link para o vídeo no YouTube" name="evaluation.video" value="http://www.youtube.com/watch?v=${evaluation.video}">
				<a id="button_youtube" href="http://upload.youtube.com/my_videos_upload" target="new" title="Enviar vídeo no YouTube">Enviar vídeo no YouTube</a></td>
			</tr>
			<tr>
				<td></td>
				<td><input id="button_save" class="button" style="width: 510px;" type="submit" title="Salvar avaliação" value="Salvar avaliação"></td>
			</tr>
		</table>
		</form>
	</div>
	<div id="evaluation_useradd">
	<h1>Participantes</h1>
		<form action="<c:url value='/evaluation/add/user/'/>" method="post">
		<input id="id" class="text_box" type="hidden" title="ID da avaliação" name="id" value="${evaluation.id}">
			<table style="width: 510px;">
				<tr>
					<td><label class="label" for="userName">Nome:</label></td>
					<td><label class="label" for="userEmail">E-mail:</label></td>
					<td></td>
				</tr>
				<tr>
					<td><input id="userName" class="text_box" type="text" title="Nome" name="userName"></td>
					<td><input id="userEmail" class="text_box" type="text" title="Email" name="userEmail"></td>
					<td><input id="button_useradd" class="button" type="submit" title="Adicionar novo avaliador" value="Adicionar"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="evaluation_userlist">
		<table style="width: 502px;">
			<tr>
				<td>Nome</td>
				<td>Email</td>
				<td>Excluir</td>
			</tr>		
			<c:forEach items="${users}" var="user" varStatus="st">
				<tr>
				
					<td>${user.name}</td>
					<td>${user.email}</td>
					<c:if test="${evaluation.moderate != user.id}">
						<td><a href="/evaluation/delete/${evaluation.id}/${user.id}"><img src="/images/icons/delete.png" alt="Excluir" title="Excluir"></a></td>				
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
	<script>
	$(document).ready(function(){
	   $("tr:even").addClass("even");
	   $("tr:odd").addClass("odd");
	});
</script>
</body>
</html>
