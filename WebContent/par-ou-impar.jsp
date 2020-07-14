<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Par ou Impar</title>
</head>
<body>
	
	<form action="par-ou-impar.jsp" method="post">
		<label>Nome</label>
		<br />
		<input type="text" name="nome" placeholder="Nome da Pessoa" maxlength="80">
		<br />
		<label>Numero</label>
		<br />
		<input type="number" name="numero" placeholder="Numero" maxlength="3" min="3"  max="25">
		
		<br />
		<br />
		
		<button type="submit">Enviar dados</button>
		
		<br />
		<button href="javascript:enviar();">Enviar via GET</button>
		
		<core:if test="${param.nome != null}">
		
			<% 
				int numero = Integer.parseInt( request.getParameter( "numero" ));
			if( numero % 2==0){ 
				%>
				<h2> Número é Par</h2>
				<%
			}else{
				%>
				<h2>Número é Impar</h2>
				<%
			}
			%>
			
			<core:if test="${param.numero %2 == 0 }">			
			<h2>Número é PAR</h2>			
			</core:if>
			<core:if test="${param.numero %2 != 0 }">			
				<h2>Número é IMPAR</h2>			
			</core:if>
			
		
		</core:if>
		
		
		
	</form>
	
</body>
</html>