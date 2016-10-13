<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home Page</title>
</head>
<body>
	<div id="content-container">
		<div id="container">
			<h2>${login.getUserName()} you are on home page</h2>
		</div>
		<table class="data">
			<tr>
				<th><a href="/cims/home">Home Page</a></th>
				<th><a href="/cims/user">Manage User</a></th>
			</tr>
		</table>
	</div>
</body>
</html>