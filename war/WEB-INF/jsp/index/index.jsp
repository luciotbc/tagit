<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-br">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tag-It</title>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>
	<div id="index_menu">
		<div id="index_logo">
			<a href="/"><img src="/images/logo/tagit_small.png" alt="Tag-It" title="Tag-It"></a>
		</div>
		<div id="login_box">
			<form action="<c:url value='/login'/>" method="post">
				<table>
					<tr>
						<td><label class="label" for="login_email">E-mail:</label>
						</td>
						<td><label class="label" for="login_password">Senha:</label>
						</td>
						<td></td>
					</tr>
					<tr>
						<td><input id="login_email" class="text_box" type="text"
							title="E-mail do avaliador" value="" name="user.email">
						</td>
						<td><input id="login_password" class="text_box"
							type="password" title="Senha do avaliador" value=""
							name="user.password">
						</td>
						<td><input id="login" class="button" type="submit"
							title="Entrar" value="Entrar">
						</td>
					</tr>
					<tr>
						<td>
<!-- 						<input type="checkbox" name="login_persist" -->
<!-- 							id="login_persist" value="1" class="inputcheckbox"> <label -->
<!-- 							for="login_persist">Mantenha-me conectado</label> -->
							</td>
						<td><a href="#">Esqueceu sua senha?</a>
						</td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<div id="content">
		<div id="main">
			<jsp:include page="/WEB-INF/jsp/includes/introduction.jsp" />
		</div>
		<div id="register">
			<jsp:include page="/WEB-INF/jsp/includes/msg_error.jsp" />
	<jsp:include page="/WEB-INF/jsp/includes/msg_flash.jsp" />
			<h1>Cadastre-se</h1>
			<form action="<c:url value='/register'/>" method="post">
				<label class="label" for="name_singin">Nome:</label>
				<input id="name_singin" class="text_box" type="text" title="Cadastrar nome do avaliador" value="" name="user.name">
				<label class="label" for="email_singin">E-mail:</label>
				<input id="email_singin" class="text_box" type="text" title="Cadastrar e-mail do avaliador" value="" name="user.email">
				<label class="label" for="password_singin">Senha:</label>
				<input id="password_singin" class="text_box" type="password" title="Cadastrar senha do avaliador" value="" name="user.password">
				<p>Senha com 6 ou mais caracteres.</p>
				<label class="label" for="confirm_password_singin">Confirmar senha:</label>
				<input id="confirm_password_singin" class="text_box" type="password" title="Confirmar senha do avaliador" value="" name="password">
				<input id="button_singin" class="button" type="submit" title="Inscreva-se Agora*" value="Inscreva-se Agora*">
			</form>
			
<!-- 			<div id="register_alert"> -->
<!-- 				<p>* Ao clicar em Inscreva-se agora ou ao utilizar o Tag-It, -->
<!-- 					você indica que leu, entendeu e concordou com o Contrato do usuário -->
<!-- 					e com a Política de privacidade do Tag-It.</p> -->
<!-- 			</div> -->
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/includes/footer.jsp" />
</body>
</html>
