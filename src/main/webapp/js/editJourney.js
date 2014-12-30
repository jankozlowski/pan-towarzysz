
		//image showing for people slider
		function setPeople(ui) {

			if (ui === 0) {
				$("#imgPeople").attr("src", "${pageContext.request.contextPath}/../images/question.png");
				$("#numberPeople").text("?");
			}

			if (ui >= 1 && ui <= 3) {
				$("#imgPeople").attr("src", "${pageContext.request.contextPath}/../images/group1.png");
			} else if (ui >= 4 && ui <= 7) {
				$("#imgPeople").attr("src", "${pageContext.request.contextPath}/../images/group2.png");
			} else if (ui >= 8 && ui <= 11) {
				$("#imgPeople").attr("src", "${pageContext.request.contextPath}/../images/group3.png");
			} else if (ui >= 12 && ui <= 15) {
				$("#imgPeople").attr("src", "${pageContext.request.contextPath}/../images/group4.png");
			} else if (ui >= 16 && ui <= 19) {
				$("#imgPeople").attr("src", "${pageContext.request.contextPath}/../images/group5.png");
			} else if (ui >= 20 && ui <= 24) {
				$("#imgPeople").attr("src", "${pageContext.request.contextPath}/../images/group6.png");
			}

			else if (ui == 25) {
				$("#numberPeople").text(">24");
				$("#imgPeople").attr("src", "${pageContext.request.contextPath}/../images/group7.png");
			}

			$("#ludzie").val(ui);
		};

		//function for showing active sprots
		function makeActive(object) {
			$(object).removeClass("shownObject");
			$(object).addClass("hiddenObject");
			$(object).next("img").addClass("shownObject");
			$(object).next("img").removeClass("hiddenObject");
			$(object).nextAll('input').first().attr('value', 'true');
		};

		//function for showing/hiding sports
		function showPhoto() {
			$("#zdjecieButton").hide();
			$("#zdjecieCon").show();
		};

		//function for showing/hiding sports
		function showPhotos() {
			$("#zdjeciaButton").hide();
			$("#zdjeciaCon").show();
		};

		//function for reading equipment
		function readEquipment(list) {
			
			var eqlist = list;
			var array = eqlist.split(';');
			for (var a = 0; a < array.length - 1; a++) {
				$("#listEq").append("<div class='addedDiv col-xs-4' style='display:none;padding-top:5px'><img src='${pageContext.request.contextPath}/../images/delete.png' class='someText' style='margin-right:10px' /><span>" + array[a] + "</span></br></div>");
			}
		};
		//function read number of people
		function numPeople(people) {
			var liczbaOsob = people;
			$("#ludzie").attr("value", liczbaOsob);
			$("#numberPeople").text(liczbaOsob);
			setPeople(liczbaOsob);
			
		};

		//read hardness
		function hardness(hard) {
			var trudnosc = hard;
			$("#trudnosc").attr("value", trudnosc);
			$(".rating").slice(0, trudnosc).attr("src", "${pageContext.request.contextPath}/../images/tent-1.png");
			$(".rating").slice(trudnosc, 10).attr("src", "${pageContext.request.contextPath}/../images/tent.png");
		};

		//read inpute value
		function activeValue() {
			$("input").each(function() {
				if ($(this).val() == "true") {
					makeActive($(this).prevAll(".shownObject:first"));
				}
			});

			$(".addedDiv").slideDown("fast");
		};

		function myPopBox() {
			$('.popbox').popbox({
				'open' : '.open',
				'box' : '.box',
				'arrow' : '.arrow',
				'arrow-border' : '.arrow-border',
				'close' : '.close'
			});
		};
		function myMap() {
			$("#map").geolocate({

				lat : "#lat",
				lng : "#lng",
				address : [ "#address" ],
				mapOptions : {
					zoom : 8
				}
			});
		};
		//trudnosc rating
		function rating() {
			var selected = trudnosc;
			$(".rating").click(function() {
				selected = $(".rating").index(this);
				$(".rating").slice(0, ++selected).attr("src", "${pageContext.request.contextPath}/../images/tent-1.png");
				$(".rating").slice(selected, 10).attr("src", "${pageContext.request.contextPath}/../images/tent.png");
				$("#trudnosc").val(selected);
			});
			$(".rating").mouseover(function() {
				var index = $(".rating").index(this);
				$(".rating").slice(0, ++index).attr("src", "${pageContext.request.contextPath}/../images/tent-1.png");
				$(".rating").slice(index, 10).attr("src", "${pageContext.request.contextPath}/../images/tent.png");

			});
			$(".rating").mouseout(function() {
				$(".rating").slice(0, selected).attr("src", "${pageContext.request.contextPath}/../images/tent-1.png");
				$(".rating").slice(selected, 10).attr("src", "${pageContext.request.contextPath}/../images/tent.png");

			});
		};

		function Equimpment() {
			$("#addEq").on("click", function() {
				$("#listEq").append("<div class='addedDiv col-xs-4' style='display:none;padding-top:5px'><img src='${pageContext.request.contextPath}/../images/delete.png' class='someText' style='margin-right:10px' /><span>" + $("#sprzet").val() + "</span></br></div>");
				$(".addedDiv").slideDown("slow");
				$("#sprzetHide").val($("#sprzet").val() + ";" + $("#sprzetHide").val());
				$("#sprzet").val("");

			});
			$("body").on("click", ".someText", function() {
				var valString = $("#sprzetHide").val();
				var deleteString = $(this).next().text();

				valString = valString.replace(deleteString + ";", '');
				$("#sprzetHide").val(valString);

				$(this).parent().slideUp("slow", function() {
					$(this).remove();
				});

			});
		};

		function peopleSlider(liczbaOsob) {
			$('#defaultslide').slider({
				max : 25,
				min : 0,
				value : liczbaOsob,
				slide : function(e, ui) {
					$('#numberPeople').html(ui.value);

					setPeople(ui.value);
				}
			});
			
		};

		function timeSpins() {
			$("#daySpinner").TouchSpin({
				min : 0,
				max : 999,
				verticalbuttons : true,
				prefix : '<img src="${pageContext.request.contextPath}/../images/day.png">',
				verticalupclass : 'glyphicon glyphicon-plus',
				verticaldownclass : 'glyphicon glyphicon-minus'
			});
			$("#hourSpinner").TouchSpin({
				min : 0,
				max : 23,
				verticalbuttons : true,
				prefix : '<img src="${pageContext.request.contextPath}/../images/hour.png">',
				verticalupclass : 'glyphicon glyphicon-plus',
				verticaldownclass : 'glyphicon glyphicon-minus'
			});
			$("#minuteSpinner").TouchSpin({
				min : 0,
				max : 59,
				verticalbuttons : true,
				prefix : '<img src="${pageContext.request.contextPath}/../images/sec.png">',
				verticalupclass : 'glyphicon glyphicon-plus',
				verticaldownclass : 'glyphicon glyphicon-minus'
			});
		};

		function myClock() {
			$('.clockpicker').clockpicker({
				placement : 'bottom',
				align : 'left',
				donetext : 'Gotowe'
			});
		};

		function selectedSports() {
			$(".activeSport").on("click", function() {
				$(this).removeClass("shownObject");
				$(this).addClass("hiddenObject");
				$(this).prev("img").addClass("shownObject");
				$(this).prev("img").removeClass("hiddenObject");
				$(this).nextAll('input').first().attr('value', 'false');

			});
			$(".inactiveSport").on("click", function() {
				makeActive($(this));

			});
		};

		function myTips() {
			$('.img-rounded').tipsy({
				title : 'alt',
				fade : true,
				gravity : 'n'
			});
		};

		function myValidate() {
			$("#editForm").validate({

				ignore : [],
				errorPlacement : function(error, element) {

					error.appendTo(element.closest(".form-group"));
				},
				rules : {
					nazwa : "required",
					miejsce : "required",

				},
				messages : {
					nazwa : "Nazwa musi być podana",
					miejsce : "Miejsce musi być podane",

				}
			});
		};

		function myDatePicker() {
			var options = {
				dateInputNode : $("#data"),
				currentDate : new Date(2014, 9, 3),
				modules : { // An object that define enable modules using in date picker.
					header : true,
					footer : true,
					daysOfTheWeek : true,
					navBar : true,
					today : true,
					gotoDate : true,
					icon : false,
					clear : false
				},
			};
			var instance = new BeatPicker(options);
		};
