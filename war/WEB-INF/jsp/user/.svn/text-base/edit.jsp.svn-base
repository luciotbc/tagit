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
	<div id="user_show">
		<h1>Editar perfil</h1>
		<form action="<c:url value='/user/save'/>" method="post">
			<table>
				<tr>
					<td><label class="label" for="name">Nome do avaliador:</label>
					</td>
					<td><input id="name" class="text_box" type="text"
						title="Nome do avaliador:" name="user.name" value="${user.name}">
					</td>
				</tr>
				<tr>
					<td><label class="label" for="email">E-mail:</label>
					</td>
					<td><input id="email" class="text_box" type="text"
						title="E-mail do avaliador" name="user.email" value="${user.email}">
					</td>
				</tr>
				<tr>
					<td><label class="label" for="password">Alterar Senha:</label>
					</td>
					<td><input id="password" class="text_box" type="password"
						title="Alterar senha" name="user.password">
					</td>
				</tr>
				<tr>
					<td><label class="label" for="password_confirm">Confirmar senha:</label>
					</td>
					<td><input id="password_confirm" class="text_box" type="password"
						title="Confirmar alteração de senha" name="password">
					</td>
				</tr>								
				<tr>
					<td></td>
					<td><input id="button_save" class="button" type="submit" title="Salvar alterações" value="Salvar"></td>
				</tr>
			</table>
		</form>
		<br /> <br />
	</div>
	<jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
</body>
</html>
