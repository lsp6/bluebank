<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<spring:url value="/resources/css/template.css" var="template"></spring:url>
<link href="${template}" rel="stylesheet">

<spring:url value="/resources/css/960.css" var="novecentos"></spring:url>
<link href="${novecentos}" rel="stylesheet">

<spring:url value="/resources/css/colour.css" var="colour"></spring:url>
<link href="${colour}" rel="stylesheet">

<style type="text/css">
form {
 	width: 400px; 
 	justify-content: center; 
 	position: fixed; 
 	top: 50%; 
 	left: 50%; 
 	margin-top: -200px; 
 	margin-left: -200px; 
 	padding: 1em; 
 	border: 1px solid #CCC; 
border-radius: 1em; 
}


 .label90 { 
 	display: inline-block; 
 	width: 90px; 
 } 
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<div class="grid_6">
		<div class="box">
			<h2>Contas para consulta</h2>
			<table>
				<tr>
					<th><p>Agência</p></th>
					<th><p>Conta</p></th>
					<th><p>Senha</p></th>
				</tr>
				<c:forEach var="listaContas" items="${contas}">
					<tr>
						<td>${listaContas.numeroAgencia}</td>
						<td>${listaContas.numeroConta}</td>
						<td>${listaContas.cliente.senha}</td>
					</tr>
				</c:forEach>

			</table>
		</div>

	</div>

	<div >
		<form action="#" method="POST">
			<div>
				<label class="label90"></label><label style="color: red">${msg}</label>
			</div>
			<div>
				<label class="label90">Agência: </label> <input type="number"
					name="agencia">
			</div>
			<br>

			<div>
				<label class="label90">Conta: </label> <input type="number"
					name="conta" />
			</div>
			<br>

			<div>
				<label class="label90">Senha:</label> <input type="password"
					name="senha" /> <br> <br> <input type="submit"
					value="Logar">
			</div>

		</form>
	</div>


</body>
</html>