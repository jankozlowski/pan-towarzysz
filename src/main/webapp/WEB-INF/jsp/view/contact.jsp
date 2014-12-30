<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../../jspf/header.jspf"%>
<script>
	$().ready(function() {
		// validate the comment form when it is submitted
		$("#contactForm").validate({
			rules : {
				imie : "required",
				mail : "required",
				temat : "required",
				tresc : "required"

			},
			messages : {
				imie : "Musisz podać swoje imie lub nick",
				mail : "Adress e-mail jest wymagany",
				temat : "Podaj temat wiadomośći",
				tresc : "Podaj treść wiadomośći",
			}
		});
	});
</script>
<%@include file="../../jspf/navbar.jspf"%>

<div class="row">
	<div class="col-sm-8 myBackground contact">

		<h1>Kontakt</h1>

		<img style="margin-left: 95px;" src="${pageContext.request.contextPath}/images/mails.png">
		<div class="col-sm-5 col-md-offset-1" style="margin-right: 20px; float: right">
			<form id="contactForm" action="${pageContext.request.contextPath}/sendMailServlet" method="post">
				<br>
				<input id="imie" type="text" name="imie" placeholder="Imie" class="contactBox">
				<br> <br>
				<input type="email" id="mail" name="mail" placeholder="E-mail" class="contactBox">
				<br> <br>
				<input type="text" id="temat" name="temat" placeholder="Temat" class="contactBox">
				<br> <br>
				<textarea name="tresc" id="tresc" placeholder="Treść" class="contactBox" style="height: 200px"></textarea>
				<input type="submit" value="Wyślij" class="btn btn-primary" style="margin-left: 205px; margin-bottom: 15px;">
			</form>
		</div>
		<br />
		<br />
		<br />
		<div class=" myBackground col-sm-5 contactData">
			<p>The Longest Journey</p>
			<p>www.thelongestjourney.com</p>
			<p>thelongestjourney@gold.com</p>
			<p>514-393-032</p>
		</div>

	</div>

	<%@include file="../../jspf/rightColumn.jspf"%>

	<%@include file="../../jspf/footer.jspf"%>