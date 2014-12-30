$(document).ready(function() {

	$("#zawansowaneSzukanie").on("change", function() {
		$("#appendSzukanie").slideToggle("slow");

	});

	$("#state").selectmenu();
	$("#rodzaj").selectmenu().selectmenu("menuWidget").addClass("overflow");
	;

	$("#newsletterForm").validate({
		rules : {
			email : {
				email : true
			},

		},
		messages : {
			email : "Musisz podać prawidłowy adres e-mail",
		}
	});
});