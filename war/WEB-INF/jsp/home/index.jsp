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
<script type="text/javascript">
	function _run(videoID, divID) {
		var params = { allowScriptAccess : "always" };
		var atts = { id : "ytPlayer" };
		swfobject.embedSWF("http://www.youtube.com/v/" + videoID, divID, "240", "145", "9", null, null, params, atts);
	}

	google.load("swfobject", "2.1");
	google.setOnLoadCallback(_run);
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/includes/menubar.jsp" />
	<jsp:include page="/WEB-INF/jsp/includes/msg_error.jsp" />
	<jsp:include page="/WEB-INF/jsp/includes/msg_flash.jsp" />
	<div id="content">
		<h1>Minhas avaliações</h1>
		<a class="add_evaluation_link" href="/evaluation/add"><img src="/images/icons/add.png" alt="Nova
			avaliação" title="Nova
			avaliação">Nova
			avaliação</a>
		<div id="evaluations">
			<c:forEach items="${evaluations}" var="evaluation" varStatus="st">
				<div class="evaluation">
					<div class="evaluation_video">
						<div id="${evaluation.id}" style="float: left;">
							Carregando...
							<script type="text/javascript">
								_run("${evaluation.video}", "${evaluation.id}");
							</script>
						</div>
					</div>
					<div class="evaluation_ico">
						<ul>
							<li><a href="/tagging/${evaluation.id}"> <img src="/images/icons/tagging.png" alt="Etiquetar" title="Etiquetar"></a></li>
							<li><a href="/report/${evaluation.id}"><img src="/images/icons/report.png" alt="Relatório" title="Relatório"></a></li>
							<c:if test="${evaluation.moderate == user.id}">
								<li><a href="/evaluation/edit/${evaluation.id}"><img src="/images/icons/edit.png" alt="Editar" title="Editar"></a></li>
								<li><a href="/evaluation/delete/${evaluation.id}"><img src="/images/icons/delete.png" alt="Excluir" title="Excluir"></a></li>
							</c:if>
						</ul>
					</div>
					<div class="evaluation_info">
						<H2>${evaluation.name}
						<c:if test="${evaluation.moderate == user.id}">
							 - <a href="/evaluation/edit/${evaluation.id}">[Editar]</a>
						</c:if>
						</H2>
						<table> 
							<tr>
								<td>Nome do aplicativo:</td>
								<td>${evaluation.appName}</td>
							</tr>
							<tr>
								<td>Descrição do aplicativo:</td>
								<td>${evaluation.appDescription}</td>
							</tr>
							<tr>
								<td>Objetivo da avaliação:</td>
								<td>${evaluation.goalEvaluation}</td>
							</tr>
<!-- 							<tr> -->
<!-- 								<td>Id:</td> -->
<%-- 								<td>${evaluation.id}</td> --%>
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td>Moderador:</td> -->
<%-- 								<td>${evaluation.moderate}</td> --%>
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td>Video:</td> -->
<%-- 								<td>${evaluation.video}</td> --%>
<!-- 							</tr> -->
						</table>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
<%-- 	<jsp:include page="/WEB-INF/jsp/includes/footer.jsp" /> --%>
</body>
</html>
