<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@include file="../../jspf/header.jspf"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-filestyle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.bootstrap-touchspin.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.bootstrap-touchspin.css" type="text/css">
<script type='text/javascript' src='${pageContext.request.contextPath}/js/jquery.tipsy.js'></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tipsy.css" type="text/css" />
<script type='text/javascript' src='${pageContext.request.contextPath}/js/account.js'></script>
<%@include file="../../jspf/navbar.jspf"%>
<style>
</style>

<div class="row ">
	<div class="col-md-8 myBackground aboutMargin">
		<h1>Edytuj konto</h1>

		<form class="form-horizontal " role="form" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/uploadServlet">

			<input type="hidden" name="option" value="save" />
			<img src="/uploads/users/${userData.getPhoto()}" style="float: left; margin: 10px; margin-bottom: 25px">
			<p style="padding-left: 250px; padding-top: 7px; padding-bottom: 5px">
				<b>Zmien avatar</b>
			</p>
			<div class="col-sm-6">
				<input class="filestyle" type="file" name="file" size="50" style="float: left" data-buttonText="Wybierz plik">
			</div>
			<br /> <br />
			<button style="margin-top: 10px; margin-left: 15px" type="submit" class="btn btn-default">Prześlij</button>

		</form>

		<form id="accountForm" class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/account?action=account" style="clear: both">
			<input type="hidden" name="option" value="editUser" />

			<div class="form-group">
				<label for="user" class="col-sm-2 control-label">Login</label>
				<div class="col-sm-10">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="User" value="${userData.getUser()}" name="user" readonly>
						<span class="input-group-addon"> <img src="${pageContext.request.contextPath}/images/user.png" />
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Email" value="${userData.getEmail()}" name="email" readonly>
						<span class="input-group-addon"> <img src="${pageContext.request.contextPath}/images/email.png" />
						</span>
					</div>

				</div>
			</div>
			<div class="form-group">
				<label for="haslo" class="col-sm-2 control-label">Hasło</label>
				<div class="col-sm-10">
					<div class="input-group">
						<input type="password" class="form-control" placeholder="haslo" name="haslo">
						<span class="input-group-addon"> <img src="${pageContext.request.contextPath}/images/password.png" />
						</span>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="powtorzhaslo" class="col-sm-2 control-label">Powtórz hasło</label>
				<div class="col-sm-10">
					<div class="input-group">
						<input type="password" class="form-control" placeholder="Powtórz haslo" name="powtorzhaslo">
						<span class="input-group-addon"> <img src="${pageContext.request.contextPath}/images/password2.png" />
						</span>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">Imię</label>
				<div class="col-sm-10">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Imię" value="${userData.getName()}" name="name">
						<span class="input-group-addon"> <img src="${pageContext.request.contextPath}/images/identification.png" />
						</span>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="surname" class="col-sm-2 control-label">Nazwisko</label>
				<div class="col-sm-10">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Nazwisko" value="${userData.getSurname()}" name="surename">
						<span class="input-group-addon"> <img src="${pageContext.request.contextPath}/images/identification.png" />
						</span>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="surname" class="col-sm-2 control-label">Płec</label>
				<div class="col-sm-3 col-sm-offset-1" style="margin-top: 5px">
					<div class="btn-group btn-toggle">
						<button type="button" class="btn btn-sm btn-primary active">Męzczyzna</button>
						<button type="button" class="btn btn-sm btn-default">Kobieta</button>
						<input type="hidden" value="${userData.getGender()}" name="plec" id="plec">
					</div>
				</div>
				<label for="age" class="col-sm-2 control-label">Wiek</label>
				<div class="col-sm-3">

					<input type="text" class="form-control" placeholder="Wiek" value="${userData.getAge()}" name="age" id="ageSpinner">
				</div>
			</div>

			<div class="form-group">
				<label for="about" class="col-sm-2 control-label">O mnie</label>
				<div class="col-sm-10">
					<div class="input-group">
						<textarea rows="5" class="form-control" placeholder="O mnie" name="about">${userData.getAbout()}</textarea>
						<span class="input-group-addon"> <img src="${pageContext.request.contextPath}/images/aboutme.png" />
						</span>
					</div>

				</div>
			</div>

			<div class="form-group">
				<label for="about" class="col-sm-2 control-label">Ulubione dyscipliny</label>
				<div class="col-sm-10">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/running.png" alt="Bieganie" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/runningInv.png" alt="Bieganie" />
					<input type="hidden" value="${userData.getSport().isBieganie()}" name="bieganie">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/horseRiding.png" alt="Jazda Konna" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/horseRidingInv.png" alt="Jazda Konna" />
					<input type="hidden" value="${userData.getSport().isJazda_konna()}" name="jazda_konna">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/archery.png" alt="Łucznictwo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/archeryInv.png" alt="Łucznictwo" />
					<input type="hidden" value="${userData.getSport().isLucznictwo()}" name="lucznictwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/badminton.png" alt="Babington" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/badmintonInv.png" alt="Babington" />
					<input type="hidden" value="${userData.getSport().isBabington()}" name="babington">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/baseball.png" alt="Baseball" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/baseballInv.png" alt="Baseball" />
					<input type="hidden" value="${userData.getSport().isBaseball()}" name="baseball">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/basketball.png" alt="Koszykówka" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/basketballInv.png" alt="Koszykówka" />
					<input type="hidden" value="${userData.getSport().isKoszykowka()}" name="koszykowka">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/bicycle.png" alt="Jazda Rowerem" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/bicycleInv.png" alt="Jazda Rowerem" />
					<input type="hidden" value="${userData.getSport().isJazda_rowerem()}" name="jazda_rowerem">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/canoeing.png" alt="Kajakarstwo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/canoeingInv.png" alt="Kajakarstwo" />
					<input type="hidden" value="${userData.getSport().isKajakarstwo()}" name="kajakarstwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/climbing.png" alt="Wspinaczka" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/climbingInv.png" alt="Wspinaczka" />
					<input type="hidden" value="${userData.getSport().isWspinaczka()}" name="wspinaczka">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/dancing.png" alt="Taniec" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/dancingInv.png" alt="Taniec" />
					<input type="hidden" value="${userData.getSport().isTaniec()}" name="taniec">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/diving.png" alt="Nurkowanie" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/divingInv.png" alt="Nurkowanie" />
					<input type="hidden" value="${userData.getSport().isNurkowanie()}" name="nurkowanie">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/fencing.png" alt="Szermierka" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/fencingInv.png" alt="Szermierka" />
					<input type="hidden" value="${userData.getSport().isSzermierka()}" name="szermierka">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/fishing.png" alt="Wędkarstwo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/fishingInv.png" alt="Wędkarstwo" />
					<input type="hidden" value="${userData.getSport().isWedkarstwo()}" name="wedkarstwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/football.png" alt="Piłka Nożna" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/footballInv.png" alt="Piłka Nożna" />
					<input type="hidden" value="${userData.getSport().isPilka_nozna()}" name="pilka_nozna">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/geocaching.png" alt="Geocaching" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/geocachingInv.png" alt="Geocaching" />
					<input type="hidden" value="${userData.getSport().isGeocaching()}" name="geocaching">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/golf.png" alt="Golf" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/golfInv.png" alt="Golf" />
					<input type="hidden" value="${userData.getSport().isGolf()}" name="golf">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/hockey.png" alt="Hokey" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/hockeyInv.png" alt="Hokey" />
					<input type="hidden" value="${userData.getSport().isHokey()}" name="hokey">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/ice.png" alt="Łyżwiarstwo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/iceInv.png" alt="Łyżwiarstwo" />
					<input type="hidden" value="${userData.getSport().isLyzwiarstwo()}" name="lyzwiarstwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/martial arts.png" alt="Sztuki Walki" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/martial artsInv.png" alt="Sztuki Walki" />
					<input type="hidden" value="${userData.getSport().isSztuki_walki()}" name="sztuki_walki">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/meditation.png" alt="Medytacja" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/meditationInv.png" alt="Medytacja" />
					<input type="hidden" value="${userData.getSport().isMedytacja()}" name="medytacja">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/paratrooper.png" alt="Spadachroniarstwo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/paratrooperInv.png" alt="Spadachroniarstwo" />
					<input type="hidden" value="${userData.getSport().isSpadochroniarstwo()}" name="spadochroniarstwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/polo.png" alt="Polo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/poloInv.png" alt="Polo" />
					<input type="hidden" value="${userData.getSport().isPolo()}" name="polo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/rowing.png" alt="Wioslarstwo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/rowingInv.png" alt="Wioslarstwo" />
					<input type="hidden" value="${userData.getSport().isWioslarstwo()}" name="wioslarstwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/shooting.png" alt="Strzelectwo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/shootingInv.png" alt="Strzelectwo" />
					<input type="hidden" value="${userData.getSport().isStrzelectwo()}" name="strzelectwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/skating.png" alt="Skating" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/skatingInv.png" alt="Skating" />
					<input type="hidden" value="${userData.getSport().isSkateboarding()}" name="skateboarding">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/skating1.png" alt="Skating1" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/skating1Inv.png" alt="Skating1" />
					<input type="hidden" value="${userData.getSport().isRolkarstwo()}" name="rolkarstwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/skiing.png" alt="Skiing" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/skiingInv.png" alt="Skiing" />
					<input type="hidden" value="${userData.getSport().isNarciarstwo()}" name="narciarstwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/snowboarding.png" alt="Snowboarding" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/snowboardingInv.png" alt="Snowboarding" />
					<input type="hidden" value="${userData.getSport().isSnowboarding()}" name="snowboarding">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/surfer1.png" alt="Surfer" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/surfer1Inv.png" alt="Surfer" />
					<input type="hidden" value="${userData.getSport().isSurfing()}" name="surfing">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/swimming.png" alt="Pływanie" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/swimmingInv.png" alt="Pływanie" />
					<input type="hidden" value="${userData.getSport().isPlywanie()}" name="plywanie">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/tennis.png" alt="Tennis" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/tennisInv.png" alt="Tennis" />
					<input type="hidden" value="${userData.getSport().isTennis()}" name="tennis">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/trekking.png" alt="Trekking" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/trekkingInv.png" alt="Trekking" />
					<input type="hidden" value="${userData.getSport().isTrekking()}" name="trekking">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/vollelball.png" alt="Volleball" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/vollelballInv.png" alt="Volleball" />
					<input type="hidden" value="${userData.getSport().isVolleyball()}" name="volleyball">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/walking.png" alt="Walking" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/walkingInv.png" alt="Walking" />
					<input type="hidden" value="${userData.getSport().isChodzenie()}" name="chodzenie">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/weightlifting.png" alt="Podnoszenie Cięzarów" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/weightliftingInv.png" alt="Podnoszenie Cięzarów" />
					<input type="hidden" value="${userData.getSport().isPodnoszenie()}" name="podnoszenie">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/zeglarstwo.png" alt="Zeglarstwo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/zeglarstwoInv.png" alt="Zeglarstwo" />
					<input type="hidden" value="${userData.getSport().isZeglarstwo()}" name="zeglarstwo">
				</div>
			</div>

			<div class="form-group">
				<label for="phone" class="col-sm-2 control-label">Telefon</label>
				<div class="col-sm-10">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Telefon" value="${userData.getPhone()}" name="phone">
						<span class="input-group-addon"> <img src="${pageContext.request.contextPath}/images/phone.png" />
						</span>
					</div>
				</div>
			</div>
			<button style="float: right" type="submit" class="btn btn-primary">Wyślij</button>
		</form>
	</div>

	<%@include file="../../jspf/rightColumn.jspf"%>
</div>

<%@include file="../../jspf/footer.jspf"%>