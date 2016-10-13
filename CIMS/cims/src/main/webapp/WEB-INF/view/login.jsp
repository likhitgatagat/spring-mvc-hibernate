<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CIMS - Login</title>
</head>
<body>
	<h3>Login Form - Welcome: ${login.getUserName()}</h3>

	<form:form action="/cims/login" commandName="login">
	<div align="center">
		<table>
			<tr>
				<td><form:label path="userName">
						<spring:message text="User Name" />
					</form:label></td>
				<td><form:input path="userName" /></td>
			</tr>
			<tr>
				<td><form:label path="password">
						<spring:message text="Password" />
					</form:label></td>
				<td><form:input type="password" path="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Login" /></td>
			</tr>
		</table>
		<div style="color: red">${error}</div>
	</div>
	</form:form>
</body>
</html>