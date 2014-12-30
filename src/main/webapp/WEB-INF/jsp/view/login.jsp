<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../../jspf/header.jspf"%>
<%@include file="../../jspf/navbar.jspf"%>

<div class="row">
	<div class="col-xs-12 myBackground myLogin">
		<form class="form-horizontal" id="loginForm" role="form" method="post">
			<div class="form-group">
				<h2>Logowanie</h2>
				<label for="login" class="myMargin col-xs-1 control-label ">Login: </label>
				<div class="col-xs-11 myMargin" style="padding-right: 65px">
					<input type="text" class="form-control input-large" placeholder="Login" name="login" id="login">
					<input type="hidden" name="option" value="login" />
				</div>
				<label for="haslo" class="col-xs-1 control-label myMargin">Hasło: </label>
				<div>
					<div class="col-xs-11" style="padding-right: 65px;">
						<input type="password" class="form-control input-large myMargin" placeholder="Haslo" name="haslo" id="haslo">
					</div>

					<button type="submit" class="btn btn-default myMargin">Zaloguj</button>
				</div>
			</div>
		</form>
		<c:if test="${sucess==false}">
			<p style="color: red">Hasło lub login nie prawidłowe spróbuj ponownie</p>
		</c:if>

		<p>
			<a href="<c:url value="/register"><c:param name="action" value="register"></c:param></c:url>"> Zarejestruj się tutaj</a>
		</p>
		<!--  <p>
			<a href="#">Przypomnij hasło</a>
		</p>-->
	</div>
</div>

<%@include file="../../jspf/footer.jspf"%>