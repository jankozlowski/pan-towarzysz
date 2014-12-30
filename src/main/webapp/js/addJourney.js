var map;
var geocoder;

$(document).ready(function() {

	$('.popbox').popbox({
		'open' : '.open',
		'box' : '.box',
		'arrow' : '.arrow',
		'arrow-border' : '.arrow-border',
		'close' : '.close'
	});
	$("#map").geolocate({

		lat : "#lat",
		lng : "#lng",
		address : [ "#address" ],
		mapOptions : {
			zoom : 8
		}
	});

	//trudnosc rating
	var selected = 0;
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

	//sprzęt list
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

	//people slider
	$('#defaultslide').slider({
		max : 25,
		min : 0,
		value : 1,
		slide : function(e, ui) {
			$('#numberPeople').html(ui.value);

			if (ui.value == 0) {
				$("#imgPeople").attr("src", "${pageContext.request.contextPath}/../images/question.png");
				$("#numberPeople").text("?");
			}

			if (ui.value >= 1 && ui.value <= 3) {
				$("#imgPeople").attr("src", "${pageContext.request.contextPath}/../images/group1.png");
			} else if (ui.value >= 4 && ui.value <= 7) {
				$("#imgPeople").attr("src", "${pageContext.request.contextPath}/../images/group2.png");
			} else if (ui.value >= 8 && ui.value <= 11) {
				$("#imgPeople").attr("src", "${pageContext.request.contextPath}/../images/group3.png");
			} else if (ui.value >= 12 && ui.value <= 15) {
				$("#imgPeople").attr("src", "${pageContext.request.contextPath}/../images/group4.png");
			} else if (ui.value >= 16 && ui.value <= 19) {
				$("#imgPeople").attr("src", "${pageContext.request.contextPath}/../images/group5.png");
			} else if (ui.value >= 20 && ui.value <= 24) {
				$("#imgPeople").attr("src", "${pageContext.request.contextPath}/../images/group6.png");
			}

			else if (ui.value == 25) {
				$("#numberPeople").text(">24");
				$("#imgPeople").attr("src", "${pageContext.request.contextPath}/../images/group7.png");
			}

			$("#ludzie").val(ui.value);
		}
	});

	//czas spinny
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

	//clock
	$('.clockpicker').clockpicker({
		placement : 'bottom',
		align : 'left',
		donetext : 'Gotowe'
	});

	//rodzaj
	$(".activeSport").on("click", function() {
		$(this).removeClass("shownObject");
		$(this).addClass("hiddenObject");
		$(this).prev("img").addClass("shownObject");
		$(this).prev("img").removeClass("hiddenObject");
		$(this).nextAll('input').first().attr('value', 'false');
	});
	$(".inactiveSport").on("click", function() {
		$(this).removeClass("shownObject");
		$(this).addClass("hiddenObject");
		$(this).next("img").addClass("shownObject");
		$(this).next("img").removeClass("hiddenObject");
		$(this).nextAll('input').first().attr('value', 'true');

	});
	$('.img-rounded').tipsy({
		title : 'alt',
		fade : true,
		gravity : 'n'
	});

	$("#addForm").validate({

		ignore : [],
		errorPlacement : function(error, element) {

			error.appendTo(element.closest(".form-group"));
		},
		rules : {
			nazwa : "required",
			miejsce : "required",
			zdjecie : "required"

		},
		messages : {
			nazwa : "Nazwa musi być podana",
			miejsce : "Miejsce musi być podane",
			zdjecie : "Zdjecie profilowe musi być wysłane"

		}
	});

	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1; //January is 0!
	var yyyy = today.getFullYear();

	if (dd < 10) {
		dd = '0' + dd
	}

	if (mm < 10) {
		mm = '0' + mm
	}

	today = yyyy + '-' + mm + '-' + dd;
	$("#data").val(today);

});