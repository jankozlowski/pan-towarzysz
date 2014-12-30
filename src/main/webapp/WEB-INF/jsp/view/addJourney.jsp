<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@include file="../../jspf/header.jspf"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/BeatPicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/BeatPicker.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/clockpicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/geolocation.edit.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/clockpicker.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/popbox.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/popbox.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.bootstrap-touchspin.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.bootstrap-touchspin.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-filestyle.min.js"></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/js/jquery.tipsy.js'></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tipsy.css" type="text/css" />
<script type='text/javascript' src='${pageContext.request.contextPath}/js/addJourney.js'></script>
<style>
#map {
	width: 575px;
	height: 360px;
	display: block;
	margin-bottom: 10px;
}

label.error {
	margin-left: 200px;
}
</style>
<%@include file="../../jspf/navbar.jspf"%>

<div class="row">
	<div class="col-xs-8 myBackground aboutMargin">

		<h1>Dodaj podróż</h1>
		<br>

		<form id="addForm" method="post" class="form-horizontal cmxform" role="form" enctype="multipart/form-data" action="${pageContext.request.contextPath}/addJourneyServlet">


			<input type="hidden" name="action" value="addJourney" />
			<input type="hidden" name="saveOrUpdate" value="true" />

			<div class="form-group">

				<label for="nazwa" class="col-xs-2 control-label">Nazwa podrózy</label>
				<div class="col-xs-10">
					<div class='popbox'>
						<div class="input-group open">
							<a class='open' href='#'> <input type="text" class="form-control" placeholder="Nazwa" id="nazwa" readonly></a> <span class="input-group-addon "> <img src="${pageContext.request.contextPath}/images/header.png" />
							</span>
						</div>
						<div class='colla'>
							<div class='box col-xs-10'>
								<div class='arrow'></div>
								<div class='arrow-border'></div>
								<input type="text" class="form-control" placeholder="Nazwa" name="nazwa" id="nazwaBox">
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="nazwa" class="col-xs-2 control-label">Organizator</label>
				<div class="col-xs-10">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Organizator" name="organizator" id="organizator" value="${user}" readonly>
						<span class="input-group-addon"> <img src="${pageContext.request.contextPath}/images/user.png" />
						</span>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="opis" class="col-xs-2 control-label">Opis</label>
				<div class="col-xs-10">
					<div class='popbox'>
						<div class="input-group open">
							<a class='open' href='#'> <textarea rows="5" class="form-control" placeholder="Opis" name="opis" id="opis" readonly></textarea>
							</a><span class="input-group-addon"> <img src="${pageContext.request.contextPath}/images/descripction.png" />
							</span>
						</div>
						<div class='colla'>
							<div class='box col-xs-10'>
								<div class='arrow'></div>
								<div class='arrow-border'></div>
								<textarea rows="5" class="form-control" placeholder="Opis" id="opisBox"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="trasa" class="col-xs-2 control-label">Trasa</label>
				<div class="col-xs-10">
					<div class='popbox'>
						<div class="input-group open">
							<a class='open' href='#'> <textarea rows="4" class="form-control" placeholder="Trasa" name="trasa" id="trasa" readonly></textarea>
							</a><span class="input-group-addon"> <img src="${pageContext.request.contextPath}/images/distance.png" />
							</span>
						</div>
						<div class='colla'>
							<div class='box col-xs-10'>
								<div class='arrow'></div>
								<div class='arrow-border'></div>
								<textarea rows="5" class="form-control" placeholder="Trasa" id="trasaBox"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="miejsce" class="col-xs-2 control-label">Miejsce</label>
				<div class="col-xs-10">

					<div class='popbox'>
						<div class="input-group open">
							<a class='open' href='#'><input type="text" class="form-control" placeholder="Miejsce" id="miejsce" value="" readonly></a> <span class="input-group-addon "> <img src="${pageContext.request.contextPath}/images/map.png" />
							</span>
						</div>

						<div class='colla'>
							<div class='box col-xs-10' id="mapek">
								<div class='arrow'></div>
								<div class='arrow-border'></div>

								<div id="map"></div>
								<input type="hidden" class="txtfield" id="lat" name="lat" value="54.3483">
								<input type="hidden" class="txtfield" id="lng" name="lng" value="18.6540">
								<input type="text" id="address" name="miejsce" value="">
								<input type="button" value="Wyszukaj" onclick="$('#map').geolocate('callGeocoding');">
								<a href="#" class="close">zamknij</a>

							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="koszt" class="col-xs-2 control-label">Koszt</label>
				<div class="col-xs-10">
					<div class='popbox'>
						<div class="input-group open">
							<a class='open' href='#'> <input type="text" class="form-control" placeholder="Koszt" name="koszt" id="koszt" readonly />
							</a><span class="input-group-addon"> <img src="${pageContext.request.contextPath}/images/money.png" />
							</span>
						</div>
						<div class='colla'>
							<div class='box col-xs-10'>
								<div class='arrow'></div>
								<div class='arrow-border'></div>
								<input type="text" class="form-control" placeholder="Koszt" name="koszt" id="kosztBox" />
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="sprzet" class="col-xs-2 control-label">Sprzet</label>
				<div class="col-xs-10">
					<div class="input-group">
						<input type="hidden" class="txtfield" id="sprzetHide" name="sprzet" value="">
						<input type="text" class="form-control" placeholder="Sprzet" id="sprzet" />
						<span class="input-group-btn">

							<button class="btn btn-default" type="button" id="addEq">Dodaj</button>
						</span>
					</div>
					<div id="listEq"></div>
				</div>
			</div>

			<div class="form-group">
				<label for="time" class="col-xs-2 control-label">Godzina/Data</label>
				<div class="col-xs-4">
					<div class="input-group clockpicker">
						<input type="text" class="form-control" value="8:00" id="godzina" name="time" readonly>
						<span class="input-group-addon"> <span class="glyphicon glyphicon-time"></span>
						</span>
					</div>
				</div>

				<div class="col-xs-2"></div>

				<div class="col-xs-4">
					<div class="input-group">
						<input type="text" class="form-control" data-beatpicker="true" data-beatpicker-module="clear,icon" placeholder="Data" id="data" name="data">
						<span class="input-group-addon"> <img src="${pageContext.request.contextPath}/images/calendar.png" />
						</span>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="ilosc" class="col-xs-2 control-label">Ilość osób</label>
				<div class="col-xs-1" style="padding-left: 30px; padding-right: 0px;">
					<img src="${pageContext.request.contextPath}/images/group1.png" alt="people" style="float: left" id="imgPeople" />
				</div>
				<div class="col-xs-8" id="defaultslide" style="margin-top: 7px"></div>

				<div class="col-xs-1">
					<span id="numberPeople" style="font-size: 18px"><b>1</b></span>
					<input type="hidden" class="txtfield" id="ludzie" name="ludzie" value="1">
				</div>

			</div>

			<div class="form-group">
				<label for="trudność" class="col-xs-2 control-label">Trudność</label>
				<div class="col-xs-10">
					<input type="hidden" class="txtfield" id="trudnosc" name="trudnosc" value="0">
					<img src="${pageContext.request.contextPath}/images/tent.png" alt="tent" class="rating" />
					<img src="${pageContext.request.contextPath}/images/tent.png" alt="tent" class="rating" />
					<img src="${pageContext.request.contextPath}/images/tent.png" alt="tent" class="rating" />
					<img src="${pageContext.request.contextPath}/images/tent.png" alt="tent" class="rating" />
					<img src="${pageContext.request.contextPath}/images/tent.png" alt="tent" class="rating" />
					<img src="${pageContext.request.contextPath}/images/tent.png" alt="tent" class="rating" />
					<img src="${pageContext.request.contextPath}/images/tent.png" alt="tent" class="rating" />
					<img src="${pageContext.request.contextPath}/images/tent.png" alt="tent" class="rating" />
					<img src="${pageContext.request.contextPath}/images/tent.png" alt="tent" class="rating" />
					<img src="${pageContext.request.contextPath}/images/tent.png" alt="tent" class="rating" />
				</div>
			</div>

			<div class="form-group">
				<label for="czas" class="col-xs-2 control-label">Czas</label>
				<div class="col-xs-2">
					<input id="daySpinner" type="text" value="0" name="daySpinner">
				</div>
				<div class="col-xs-2">
					<input id="hourSpinner" type="text" value="0" name="hourSpinner">
				</div>
				<div class="col-xs-2">
					<input id="minuteSpinner" type="text" value="0" name="minuteSpinner">
				</div>
			</div>

			<div class="form-group">
				<label for="rodzaj" class="col-xs-2 control-label">Rodzaj</label>
				<div class="col-xs-10">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/running.png" alt="Bieganie" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/runningInv.png" alt="Bieganie" />
					<input type="hidden" value="false" name="bieganie">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/horseRiding.png" alt="Jazda Konna" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/horseRidingInv.png" alt="Jazda Konna" />
					<input type="hidden" value="false" name="jazda_konna">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/archery.png" alt="Łucznictwo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/archeryInv.png" alt="Łucznictwo" />
					<input type="hidden" value="false" name="lucznictwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/badminton.png" alt="Babington" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/badmintonInv.png" alt="Babington" />
					<input type="hidden" value="false" name="babington">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/baseball.png" alt="Baseball" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/baseballInv.png" alt="Baseball" />
					<input type="hidden" value="false" name="baseball">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/basketball.png" alt="Koszykówka" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/basketballInv.png" alt="Koszykówka" />
					<input type="hidden" value="false" name="koszykowka">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/bicycle.png" alt="Jazda Rowerem" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/bicycleInv.png" alt="Jazda Rowerem" />
					<input type="hidden" value="false" name="jazda_rowerem">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/canoeing.png" alt="Kajakarstwo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/canoeingInv.png" alt="Kajakarstwo" />
					<input type="hidden" value="false" name="kajakarstwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/climbing.png" alt="Wspinaczka" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/climbingInv.png" alt="Wspinaczka" />
					<input type="hidden" value="false" name="wspinaczka">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/dancing.png" alt="Taniec" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/dancingInv.png" alt="Taniec" />
					<input type="hidden" value="false" name="taniec">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/diving.png" alt="Nurkowanie" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/divingInv.png" alt="Nurkowanie" />
					<input type="hidden" value="false" name="nurkowanie">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/fencing.png" alt="Szermierka" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/fencingInv.png" alt="Szermierka" />
					<input type="hidden" value="false" name="szermierka">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/fishing.png" alt="Wędkarstwo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/fishingInv.png" alt="Wędkarstwo" />
					<input type="hidden" value="false" name="wedkarstwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/football.png" alt="Piłka Nożna" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/footballInv.png" alt="Piłka Nożna" />
					<input type="hidden" value="false" name="pilka_nozna">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/geocaching.png" alt="Geocaching" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/geocachingInv.png" alt="Geocaching" />
					<input type="hidden" value="false" name="geocaching">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/golf.png" alt="Golf" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/golfInv.png" alt="Golf" />
					<input type="hidden" value="false" name="golf">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/hockey.png" alt="Hokey" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/hockeyInv.png" alt="Hokey" />
					<input type="hidden" value="false" name="hokey">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/ice.png" alt="Łyżwiarstwo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/iceInv.png" alt="Łyżwiarstwo" />
					<input type="hidden" value="false" name="lyzwiarstwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/martial arts.png" alt="Sztuki Walki" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/martial artsInv.png" alt="Sztuki Walki" />
					<input type="hidden" value="false" name="sztuki_walki">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/meditation.png" alt="Medytacja" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/meditationInv.png" alt="Medytacja" />
					<input type="hidden" value="false" name="medytacja">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/paratrooper.png" alt="Spadachroniarstwo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/paratrooperInv.png" alt="Spadachroniarstwo" />
					<input type="hidden" value="false" name="spadochroniarstwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/polo.png" alt="Polo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/poloInv.png" alt="Polo" />
					<input type="hidden" value="false" name="polo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/rowing.png" alt="Wioslarstwo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/rowingInv.png" alt="Wioslarstwo" />
					<input type="hidden" value="false" name="wioslarstwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/shooting.png" alt="Strzelectwo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/shootingInv.png" alt="Strzelectwo" />
					<input type="hidden" value="false" name="strzelectwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/skating.png" alt="Skating" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/skatingInv.png" alt="Skating" />
					<input type="hidden" value="false" name="skateboarding">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/skating1.png" alt="Skating1" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/skating1Inv.png" alt="Skating1" />
					<input type="hidden" value="false" name="rolkarstwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/skiing.png" alt="Skiing" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/skiingInv.png" alt="Skiing" />
					<input type="hidden" value="false" name="narciarstwo">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/snowboarding.png" alt="Snowboarding" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/snowboardingInv.png" alt="Snowboarding" />
					<input type="hidden" value="false" name="snowboarding">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/surfer1.png" alt="Surfer" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/surfer1Inv.png" alt="Surfer" />
					<input type="hidden" value="false" name="surfing">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/swimming.png" alt="Pływanie" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/swimmingInv.png" alt="Pływanie" />
					<input type="hidden" value="false" name="plywanie">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/tennis.png" alt="Tennis" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/tennisInv.png" alt="Tennis" />
					<input type="hidden" value="false" name="tennis">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/trekking.png" alt="Trekking" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/trekkingInv.png" alt="Trekking" />
					<input type="hidden" value="false" name="trekking">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/vollelball.png" alt="Volleball" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/vollelballInv.png" alt="Volleball" />
					<input type="hidden" value="false" name="volleyball">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/walking.png" alt="Walking" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/walkingInv.png" alt="Walking" />
					<input type="hidden" value="false" name="chodzenie">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/weightlifting.png" alt="Podnoszenie Cięzarów" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/weightliftingInv.png" alt="Podnoszenie Cięzarów" />
					<input type="hidden" value="false" name="podnoszenie">
					<img class="img-rounded inactiveSport shownObject" src="${pageContext.request.contextPath}/images/sports/zeglarstwo.png" alt="Zeglarstwo" />
					<img class="img-rounded activeSport hiddenObject" src="${pageContext.request.contextPath}/images/sports/zeglarstwoInv.png" alt="Zeglarstwo" />
					<input type="hidden" value="false" name="zeglarstwo">
				</div>
			</div>

			<div class="form-group">
				<label for="zdjecie" class="col-xs-2 control-label">Zdjecie Profilowe</label>
				<div class="col-xs-9">
					<input class="filestyle" type="file" name="zdjecie" id="zdjecie" style="margin-top: 8px" data-buttonText="Wybierz plik&nbsp">
				</div>
			</div>

			<div class="form-group">
				<label for="zdjecia" class="col-xs-2 control-label">Zdjecia</label>
				<div class="col-xs-9">
					<input class="filestyle" type="file" name="zdjecia" id="zdjecia" multiple style="margin-top: 8px" data-buttonText="Wybierz pliki">
				</div>
			</div>

			<div class="form-group">
				<div class="col-xs-offset-11">
					<button type="submit" class="btn btn-primary">Wyślij</button>
				</div>
			</div>
		</form>
	</div>
	<%@include file="../../jspf/rightColumn.jspf"%>
	<%@include file="../../jspf/footer.jspf"%>