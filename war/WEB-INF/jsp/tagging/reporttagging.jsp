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
<script type="text/javascript">
	function onYouTubePlayerReady(playerId) {
		ytplayer = document.getElementById("ytPlayer");
		setInterval(updatePlayerInfo, 250);
		updatePlayerInfo();
		ytplayer.addEventListener("onStateChange", "onPlayerStateChange");
		ytplayer.addEventListener("onError", "onPlayerError");
	}

	function _run(videoID) {
		var params = {
			allowScriptAccess : "always"
		};
		var atts = {
			id : "ytPlayer"
		};
		swfobject.embedSWF("http://www.youtube.com/v/" + videoID
				+ "?version=3&enablejsapi=1&playerapiid=player1", "videoDiv",
				"640", "360", "9", null, null, params, atts);
	}

	google.load("swfobject", "2.1");
	google.setOnLoadCallback(_run);
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/includes/menubar.jsp" />
	<jsp:include page="/WEB-INF/jsp/includes/msg_error.jsp" />
	<jsp:include page="/WEB-INF/jsp/includes/msg_flash.jsp" />
	<div id="report">
		<div id="report_evaluation">
			<h1>Avaliação - ${evaluation.name}</h1>
				<div id="report_video">
					<div id="videoDiv" style="float: left;">
						<img alt="Carregando" src="/images/icons/loading.gif">
						<p>Se estiver demorando verifique a sua conexão com o <a href="http://www.youtube.com" title="YouTube">Youtube!</a></p>
						<script type="text/javascript">
							_run("${evaluation.video}");
						</script>
					</div>
				</div>
				<div id="report_evaluation_info">
					<strong>Nome do aplicativo:</strong>${evaluation.appName}<br></p>
						<p><strong>Descrição do aplicativo:</strong>${evaluation.appDescription}<br></p>
						<p><strong>Objetivo da avaliação:</strong>${evaluation.goalEvaluation}<br></p>
						<p><strong>URL do vídeo:</strong><a href="http://www.youtube.com/watch?v=${evaluation.video}" title="Link para o vídeo da avaliação">http://www.youtube.com/watch?v=${evaluation.video}</a></p>
						<p><strong>Avaliador:</strong>${user.name}</p>
				</div>
		</div>
		<div></div>
		<div id="report_tagging_list">
		<h2>Etiquetagem</h2>
		<table>
			<thead>
				<tr>
					<td>Etiqueta</td>
					<td>Início</td>
					<td>Fim</td>
					<td>Observações</td>
				</tr>
			</thead>
			<tbody>		
				<c:forEach items="${tags}" var="tag" varStatus="st">
					<tr>
						<td><img src="/images/tags/${tag.tag}.png" alt="Etiqueta" title="Etiqueta">
							<c:choose>
							 	<c:when test="${tag.tag == 1}">Desisto</c:when>
							 	<c:when test="${tag.tag == 2}">Para mim está bom</c:when>
							 	<c:when test="${tag.tag == 3}">Não obrigado</c:when>
							 	<c:when test="${tag.tag == 4}">Vai de outro jeito</c:when>
							 	<c:when test="${tag.tag == 5}">Cade?</c:when>
							 	<c:when test="${tag.tag == 6}">Ué, o que houve?</c:when>
							 	<c:when test="${tag.tag == 7}">E agora?</c:when>
							 	<c:when test="${tag.tag == 8}">Onde estou?</c:when>
							 	<c:when test="${tag.tag == 9}">Epa!</c:when>
							 	<c:when test="${tag.tag == 10}">Assim não dá!</c:when>
							 	<c:when test="${tag.tag == 11}">O que é isso?</c:when>
							 	<c:when test="${tag.tag == 12}">Socorro!</c:when>
							 	<c:when test="${tag.tag == 12}">Por que não funciona</c:when>
							 	<c:otherwise>Etiqueta não identificada!</c:otherwise>
							</c:choose>
						</td>
						<td>${tag.startTime}</td>
						<td>${tag.endTime}</td>
						<td>${tag.observations}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		<div id ="report_compilation">
<!-- 			<h2>Compilação</h2> -->
<!-- 			<table> -->
<!-- 				<thead> -->
<!-- 					<tr> -->
<!-- 						<td>Etiqueta</td> -->
<!-- 						<td>Frequência</td>						 -->
<!-- 						<td>Tipo de falha</td> -->
<!-- 					</tr> -->
<!-- 				</thead> -->
<!-- 				<tbody> -->
<%-- 					<c:forEach items="${tags}" var="tag" varStatus="st"> --%>
						
<%-- 					</c:forEach> --%>
<!-- 				</tbody> -->
<!-- 			</table> -->
		</div>
	</div>
	<script language="javascript">
		$(document).ready(function(e) {
			try {
				$("body select").msDropDown();
			} catch (e) {
				alert(e.message);
			}
		});
		$(document).ready(function() {
			$("tr:even").addClass("even");
			$("tr:odd").addClass("odd");
		});
	</script>
</body>
</html>
