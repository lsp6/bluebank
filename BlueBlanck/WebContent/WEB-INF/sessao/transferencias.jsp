<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<spring:url value="/resources/js/jquery-3.1.1.min.js" var="jquery"></spring:url>
<script src="${jquery}"></script>

<spring:url value="/resources/css/template.css" var="template"></spring:url>
<link href="${template}" rel="stylesheet">

<spring:url value="/resources/css/960.css" var="novecentos"></spring:url>
<link href="${novecentos}" rel="stylesheet">

<spring:url value="/resources/css/colour.css" var="colour"></spring:url>
<link href="${colour}" rel="stylesheet">

<script type="text/javascript">
	function decimal() {

		var valor = $("#valor").val();
		var decimal = '0,0';

		var cont = 0;
		var result = new Array;
		valor = valor.replace(/\D/g, '');
		valor = valor.replace(/\,/g, '');
		valor = valor.replace(/\./g, '');
		var comecaComZero = true;
		while (comecaComZero == true) {

			if (String(valor.charAt(0)).match('0')) {
				valor = valor.replace('0', '');
			} else {
				comecaComZero = false;
			}

		}

		var valorSize = valor.length;
		if (valor.length > 5) {

			var groupOf3 = 0;
			for (var i = valorSize - 2; i >= 0; i--) {

				if (cont == 3) {

					result[groupOf3] = valor.substring(i, i + 3);

					groupOf3++;
					cont = 0;

				}
				;
				cont++;

			}

			var decimalString = valor.substring(valorSize - 2, valorSize);
			var string = '';

			for (var i = result.length - 1; i >= 0; i--) {
				if (cont == 1 && result.length - 1 == i) {
					string = string.concat(String(result[i]))
				} else {
					string = string.concat('.'.concat(String(result[i])));
				}

			}

			string = valor.substring(0, cont - 1).concat(string).concat(',',
					decimalString);
			$("#valor").val(string);
			console.log(valor);
		} else {
			if (valor.length == 2) {
				decimal = '0,';

			} else if (valor.length >= 3) {

				var charAt = valor.charAt(valor.length - 3);

				valor = valor.substring(0, valor.length - 2).concat(',',
						valor.substring(valor.length - 2, valor.length));

				$("#valor").val(valor);
				return;
			}

			valor = decimal.concat(valor);
			$("#valor").val(valor);

		}
		;
	}
	
</script>
<script type="text/javascript">

</script>
<style type="text/css">
form {
	justify-content: center;
}

label {
	display: inline-block;
	width: 180px;
}
</style>


</head>
<body>


	<ul id="navigation">
		<li><a href="conta">Conta</a></li>
		<li><span class="active">Tranferências</span></li>
		<li style="float: right"><a href="sair">Sair</a></li>
	</ul>

	<div id="content" class="container_16 clearfix">

		<div class="grid_6">
			<div class="box">
				<h2>Transferência</h2>

				<form id="idForm" method="post" onsubmit="transacaoEfetuada()">
					<p style="color: red;">${msg}</p>
					<p>
						<label for="title">Agência: </label> <input name="agencia"
							type="number" name="title" />
					</p>
					<p>
						<label for="title">Conta: </label> <input name="conta"
							type="number" name="title" />
					</p>
					<p>
						<label for="title">Data: </label> <input name="data" type="date"
							name="title" />
					</p>
					<p>
						<label for="title">Valor: </label> <input name="valor" id="valor"
							onkeyup="decimal()" type="text" name="title" />
					</p>
					<p>
						<input type="submit" id="botao" value="Confirmar" />
					</p>
				</form>
			</div>
		</div>
		<div class="grid_6">
			<div class="box">
				<h2>Info</h2>
				<p>É possível ver a quantidade depositada nas outras contas também.</p>
				<p>Basta acessá-las e aparecerá no histórico o valor depositado,
					juntamente com o saldo anterior da conta.</p>
			</div>

		</div>
		<div class="grid_6">
			<div class="box">
				<h2>Contas para consulta</h2>
				<table>
					<tr>
						<th><p>Agência</p></th>
						<th><p>Conta</p></th>
					</tr>
					<c:forEach var="listaContas" items="${contas}">
						<tr>
							<td>${listaContas.numeroAgencia}</td>
							<td>${listaContas.numeroConta}</td>
						</tr>
					</c:forEach>

				</table>
			</div>

		</div>



	</div>

</body>

</body>
</html>