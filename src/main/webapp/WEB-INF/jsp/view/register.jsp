<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../../jspf/header.jspf"%>
<script>
/*	$.validator.setDefaults({
		submitHandler: function() {
			alert("submitted!");
		}
	});*/

	$().ready(function() {
		// validate the comment form when it is submitted
		$("#registerForm").validate({
			rules:{
				login: "required",	
				email: {
					required: true,
					email: true
				},
				haslo: {
					required: true,
					minlength: 6
					
				},
				haslo2: {
					required: true,
					minlength: 6,
					equalTo: "#haslo"
				},
			},
			messages: {
				login: "Podaj unikatową nazwe użytkownika",
				email: "Podaj poprawny adres email",
				haslo: {
					required: "Podaj hasło",
					minlength: "Twoje hasło musi się składać przynajmniej z 6 znaków"
				},
				haslo2: {
					required: "Podaj hasło",
					minlength: "Twoje hasło musi się składać przynajmniej z 6 znaków",
					equalTo: "Hasło musi być identyczne w obu polach"
				},
				
			}
			});
	});
	</script>
<%@include file="../../jspf/navbar.jspf"%>

<div class="row">
	<div class="col-xs-12 myBackground myLogin">
		<form id="registerForm" class="form-horizontal cmxform" role="form" method="post">
			<div class="form-group">
				<input type="hidden" name="option" value="register" />
				<h2>Rejestracja</h2>
				<label for="login" class="col-xs-1 control-label myMargin">Login: </label>
				<div class="col-xs-11 registerPadding ">
					<input type="text" class="form-control input-large myMargin" placeholder="Login" name="login" id="login">
				</div>

				<label for="email" class="col-xs-1 control-label myMargin">E-Mail: </label>
				<div class="col-xs-11 registerPadding">
					<input type="text" class="form-control input-large myMargin" placeholder="E-Mail" name="email" id="email">

				</div>

				<label for="haslo" class="col-xs-1 control-label myMargin">Hasło: </label>

				<div class="col-xs-11 registerPadding">
					<input type="password" class="form-control input-large myMargin registerPadding" placeholder="Hasło" name="haslo" id="haslo">
				</div>

				<label style="margin-top: 10px" for="haslo2" class="col-xs-1 control-label">Powtórz Hasło: </label>
				<div class="col-xs-11 myMargin registerPadding">
					<input type="password" class="form-control input-large" placeholder="Powtórz Hasło" name="haslo2" id="haslo2">
				</div>
				<div class="col-xs-12 myMargin">
					<button type="submit" class="btn btn-default">Rejestruj</button>
					<c:if test="${suces==false}">
						<br />
						<br />
						<p style="color: red">Taki użytkownik był już zarejstrowany</p>
					</c:if>
					<c:if test="${sucess2==false}">
						<p style="color: red">Taki e-mail był już zarejestrowany</p>
					</c:if>
				</div>

			</div>
		</form>
		<p class="myMargin">
			<a href="<c:url value="/login"><c:param name="action" value="login"></c:param></c:url>"> Zaloguj się tutaj</a>
		</p>

	</div>
</div>

<%@include file="../../jspf/footer.jspf"%>