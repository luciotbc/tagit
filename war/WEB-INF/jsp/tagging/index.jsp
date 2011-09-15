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

	function clearTag() {
		var fild = document.getElementById("begin");
		fild.value = "00:00";
		fild = document.getElementById("end");
		fild.value = "00:00";
		fild = document.getElementById("obs");
		fild.value = "";
	}

	function updatePlayerInfo(fild) {
		if (ytplayer && ytplayer.getDuration) {
			segundos = ytplayer.getCurrentTime();
			segundo = segundos % 60;
			minutos = segundos / 60;
			minuto = minutos % 60;
			hora = minutos / 60;
			var value = "";
			if (hora.toFixed() > 0) {
				value += hora.toFixed() + ":";
			}
			if (minuto.toFixed() < 10) {
				value += "0" + minuto.toFixed() + ":";
			} else {
				value += minuto.toFixed() + ":";
			}
			if (segundo.toFixed() < 10) {
				value += "0" + segundo.toFixed();
			} else {
				value += segundo.toFixed();
			}
			fild.value = value;
		}
	}

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
	<div id="tagging">
		<h1>Avaliação - ${evaluation.name}</h1>
		<div id="video">
			<div id="videoDiv" style="float: left;">
				Carregando...
				<script type="text/javascript">
					_run("${evaluation.video}");
				</script>
			</div>
		</div>
		<div id="tag">
			<fieldset style="height: 345px;">
				<legend>Etiquetar</legend>
				<form action="<c:url value='/tagging/tag/add'/>" method="post">
					<input class="text_box" type="hidden" name="idEvaluation" value="${evaluation.id}">
					<input class="text_box" type="hidden" name="tag.idTagging" value="${tagging.id}">
					<table>
						<tr>
							<td><label class="label" for="tag">Etiqueta:</label></td>
							<td>
							  <select name="tag.tag" id="tags_selection" style="width:283px;" onchange="showValue(this.value)">
								<option title="/images/tags/1.png" value="1">Desisto</option>
								<option title="/images/tags/2.png" value="2">Para mim está bom</option>
								<option title="/images/tags/3.png" value="3">Não obrigado</option>
								<option title="/images/tags/4.png" value="4">Vai de outro jeito</option>
								<option title="/images/tags/5.png" value="5">Cadê?</option>
								<option title="/images/tags/6.png" value="6">Ué, o que houve?</option>
								<option title="/images/tags/7.png" value="7">E agora?</option>
								<option title="/images/tags/8.png" value="8">Onde estou?</option>
								<option title="/images/tags/9.png" value="9">Epa!</option>
								<option title="/images/tags/10.png" value="10">Assim não dá!</option>
								<option title="/images/tags/11.png" value="11">O que é isso?</option>
								<option title="/images/tags/12.png" value="12">Socorro!</option>
								<option title="/images/tags/13.png" value="13">Por que não funciona</option>
							  </select>
							</td>
						</tr>
						<tr>
							<td><label class="label" for="begin">Início:</label></td>
							<td>
								<input id="begin" type="text" title="Momento de ínicio da etiqueta" name="tag.starttime" value="00:00">
								<input type="button" value="Tempo Atual" onclick="updatePlayerInfo(begin)" />
							</td>		
						</tr>
						<tr>
							<td><label class="label" for="end">Fim:</label></td>
							<td>
								<input id="end" type="text" title="Momento final da etiqueta" name="tag.endtime" value="00:00">
								<input type="button" value="Tempo Atual" onclick="updatePlayerInfo(end)" />
							</td>		
						</tr>
						<tr>
							<td><label class="label" for="obs">Observações: </label></td>
							<td>
								<textarea id="obs" style="width: 250px; height: 100px;" title="Observações sobre a etiqueta" name="tag.observations"></textarea>
							</td>		
						</tr>
						<tr>
							<td><button type="button" onclick="clearTag()">Limpar</button></td>
							<td><input id="button_save" class="button" type="submit" title="Salvar etiqueta" value="Salvar etiqueta"></td>
						</tr>
					</table>
				</form>
			</fieldset>
		</div>
		<div id="tagging_list">
		<h2>Etiquetagem</h2>
		<table>
			<thead>
				<tr>
<!-- 				<td>ID</td> -->
					<td>Etiqueta</td>
					<td>Início</td>
					<td>Fim</td>
					<td>Observações</td>
					<td>Excluir</td>
				</tr>
			</thead>
			<tbody>		
				<c:forEach items="${tags}" var="tag" varStatus="st">
					<tr>
	<%-- 					<td>${tag.id}</td> --%>
						
						<td><img src="/images/tags/${tag.tag}.png" alt="Etiqueta" title="Excluir">
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
						<td><a href="/tagging/tag/delete/${evaluation.id}/${tag.id}"><img src="/images/icons/delete.png" alt="Excluir" title="Excluir"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
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
