<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Page</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('select#departmentId')
								.change(
										function() {
											var controllerUrl = 'ajax/role';
											var pageUrl = window.location.pathname;
											if (/edit/.test(window.location.pathname)) {
												controllerUrl = pageUrl + '/roles';
											}
											$.ajax({
														url : controllerUrl,
														dataType : 'json',
														type : 'POST',
														// This is query string i.e. departmentId=123
														data : {
															deptId : $(
																	'select#departmentId')
																	.val()
														},
														success : function(data) {
															$('select#roleId')
																	.empty(); // clear the current elements in select box
															$('select#roleId')
																	.append(
																			'<option value="">--- Select ---</option>');
															$
																	.each(
																			data,
																			function(
																					roleValue,
																					roletext) {
																				$(
																						'select#roleId')
																						.append(
																								$(
																										'<option></option>')
																										.val(
																												roleValue)
																										.html(
																												roletext));
																			});
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															alert(errorThrown);
														}
													});
										});
					});
</script>
</head>
<body>
	<div id="container">
		<div id="content-container">
			<div id="content">
			<c:choose>
				<c:when test="${empty login.getUserName()}">
					<% response.sendRedirect("/cims/login"); %>
				</c:when>
				<c:otherwise>
					<h2>Welcome ${login.getUserName()} to CIMS portal </h2>
				</c:otherwise>
			</c:choose>
			<div align="right"><a href="/cims/logout">Logout</a></div>
				<form:form method="post" action="/cims/user/add" commandName="user">
					<table>
						<tr>
							<td><form:label path="firstName">
									<spring:message text="First Name" />
								</form:label></td>
							<td><form:input path="firstName" /></td>
						</tr>
						<tr>
							<td><form:label path="lastName">
									<spring:message text="Last Name" />
								</form:label></td>
							<td><form:input path="lastName" /></td>
						</tr>
						<tr>
							<td><form:label path="contact">
									<spring:message text="Contact" />
								</form:label></td>
							<td><form:input path="contact" /></td>
						</tr>
						<tr>
							<td><form:label path="emailId">
									<spring:message text="Email Id" />
								</form:label></td>
							<td><form:input path="emailId" /></td>
						</tr>
						<tr>
							<td><form:label path="departmentId">
									<spring:message text="Department Name" />
								</form:label></td>
							<td><form:select path="departmentId">
									<form:option value="NONE" label="--- Select ---" />
									<form:options items="${departments}" />
								</form:select></td>
						</tr>
						<tr>
							<td><form:label path="roleId">
									<spring:message text="Role Name" />
								</form:label></td>
							<td><form:select path="roleId">
									<form:option value="NONE" label="--- Select ---" />
									<form:options items="${roles}" />
								</form:select></td>
						</tr>
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
							<td><form:input path="password" /></td>
						</tr>
						<tr>
							<td colspan="2"><c:choose>
									<c:when test="${empty user.userId}">
										<input type="submit" value="Save User" />
									</c:when>
									<c:otherwise>
										<input type="submit" value="Update User" />
									</c:otherwise>
								</c:choose></td>
						</tr>
					</table>
					<form:hidden path="userId" readonly="true" />
					<form:hidden path="createdDate" readonly="true" />
					<form:hidden path="createdBy" readonly="true" />
				</form:form>

				<h3>Users</h3>
				<c:if test="${!empty userList}">
					<table class="data">
						<tr>
							<th>Name</th>
							<th>Email</th>
							<th>Telephone</th>
							<th>&nbsp;</th>
						</tr>
						<c:forEach items="${userList}" var="user">
							<tr>
								<td>${user.lastName},${user.firstName}</td>
								<td>${user.emailId}</td>
								<td>${user.contact}</td>
								<td><a href="/cims/user/edit/${user.userId}">update</a></td>
								<td><a href="/cims/user/delete/${user.userId}">delete</a></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
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