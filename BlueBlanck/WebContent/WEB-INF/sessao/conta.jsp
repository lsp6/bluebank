<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

<spring:url value="/resources/css/template.css" var="template"></spring:url>
<link href="${template}" rel="stylesheet">

<spring:url value="/resources/css/960.css" var="novecentos"></spring:url>
<link href="${novecentos}" rel="stylesheet">

<spring:url value="/resources/css/colour.css" var="colour"></spring:url>
<link href="${colour}" rel="stylesheet">


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<ul id="navigation">
		<li><span class="active">Conta</span></li>
		<li><a href="transferencias">Tranferências</a></li>
		<li style="float: right"><a href="sair">Sair</a></li>
	</ul>

	<div id="content" class="container_16 clearfix">


		<div class="grid_5">
			<div class="box">
				<h2>${usuario}</h2>
				<p>
					<strong>CPF: </strong>${cpf}<br /> <strong>RG: </strong> ${rg}
				</p>
			</div>

			<div class="box">
				<h2>Saldo</h2>

				<p class="center">
					<fmt:setLocale value="en_BR" />
					<fmt:formatNumber value="${saldo}" type="currency" />
				</p>
			</div>

		</div>
		<div class="grid_11">
			<div class="box">
				<h2>Historico</h2>
				<table>
					<tr>
						<th><p>Dia</p></th>
						<th><p>Historico</p></th>
						<th><p>Remetente</p></th>
						<th><p>Destinatario</p></th>
						<th><p>Valor</p></th>
						<th><p>Saldo Anterior</p></th>
					</tr>
					<c:forEach var="listaHistorico" items="${historico}">
						<tr>
							<fmt:formatDate value="${listaHistorico.data}" var="dia"
								type="date" pattern="dd/MM/yyyy" />
							<td>${dia}</td>
							<td>${listaHistorico.tipoHistorico}</td>
							<td>${listaHistorico.remetente.nome}</td>
							<td>${listaHistorico.destinatario.nome}</td>


							<td><fmt:setLocale value="en_BR" /> <fmt:formatNumber
									value="${listaHistorico.valor}" type="currency" /></td>
							<c:choose>
								<c:when test="${Usuario.cpf == listaHistorico.remetente.cpf}">
									<td><fmt:setLocale value="en_BR" /> <fmt:formatNumber value="${listaHistorico.saldoAnteriorRemetente}" type="currency" /></td>
								</c:when>
								<c:when test="${Usuario.cpf == listaHistorico.destinatario.cpf}">
									<td><fmt:setLocale value="en_BR" /> <fmt:formatNumber value="${listaHistorico.saldoAnteriorDestinatario}" type="currency" /></td>
								</c:when>
							</c:choose>

						</tr>
					</c:forEach>


				</table>
				<p>${movimentacao}</p>
			</div>

		</div>



	</div>

</body>


</body>
</html>