function makeActive(object) {
	$(object).removeClass("shownObject");
	$(object).addClass("hiddenObject");
	$(object).next("img").addClass("shownObject");
	$(object).next("img").removeClass("hiddenObject");
	$(object).nextAll('input').first().attr('value', 'true');
}
function changeGender() {
	$('.btn-toggle').find('.btn').toggleClass('active');
	if ($('.btn-toggle').find('.btn-primary').size() > 0) {
		$('.btn-toggle').find('.btn').toggleClass('btn-primary');
	}
	$('.btn-toggle').find('.btn').toggleClass('btn-default');
}

$(document).ready(function() {

	if ($("#plec").val() == "true") {
		changeGender();
	}

	$("input").each(function() {
		if ($(this).val() == "true") {
			makeActive($(this).prevAll(".shownObject:first"));
		}
	});

	$("#ageSpinner").TouchSpin({
		min : 0,
		max : 125,
		verticalbuttons : true,
		prefix : '<img src="${pageContext.request.contextPath}/../images/birthday.png">',
		verticalupclass : 'glyphicon glyphicon-plus',
		verticaldownclass : 'glyphicon glyphicon-minus'
	});

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
	$('.img-rounded').tipsy({
		title : 'alt',
		fade : true,
		gravity : 'n'
	});
	$('.btn-toggle').on("click", function() {

		var val = $("#plec").val();
		changeGender();
		$("#plec").attr('value', val === "true" ? "false" : "true");
	});

	$("#accountForm").validate({
		errorPlacement : function(error, element) {

			error.appendTo(element.closest(".form-group"));
		},
		rules : {
			haslo : {
				minlength : 6
			},
			haslo2 : {
				minlength : 6,
				equalTo : "#haslo"
			},
		},
		messages : {
			haslo : {
				minlength : "Twoje hasło musi się składać przynajmniej z 6 znaków"
			},
			haslo2 : {
				minlength : "Twoje hasło musi się składać przynajmniej z 6 znaków",
				equalTo : "Hasło musi być identyczne w obu polach"
			},
		}
	});
});