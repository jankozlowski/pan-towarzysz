<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../../jspf/header.jspf"%>

<style>
.info-window {
	width: 400px;
	height: 180px;
	line-height: 24px;
	padding: 20px;
	margin: 20px
}

#map_canvas {
	width: 900px;
	height: 500px;
	margin-top: 30px;
}

.pageImageContainer {
	overflow: hidden;
	width: 200px;
	height: 140px;
	float: right;
	margin-bottom: 20px;
}

.pageImage {
	width: 200px;
	padding-bottom: 100px;
}
.info-window a {
	color: #333;
}
.mapShadow {
	width: 900px;
	height: 500px;
	margin: 0px;
	padding: 0px;
	margin-left: 5px;
}
</style>
<script src="https://maps.googleapis.com/maps/api/js"></script>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/json3/3.3.2/json3.min.js"></script>
<script>


var placeList = JSON.parse('${places}');

	function initialize() {

		var map_canvas = document.getElementById('map_canvas');
		var map_options = {
			center : new google.maps.LatLng(54.4, 18.6),
			zoom : 6,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		var map = new google.maps.Map(map_canvas, map_options);

		var infoWindow = new google.maps.InfoWindow(), marker;

		
		
		for (var i = 0; i < placeList.length; i++) {

			var myLatlng = new google.maps.LatLng(placeList[i].placeObject.latitude, placeList[i].placeObject.longitude);
			marker = new google.maps.Marker({
				position : myLatlng,
				map : map,
				title : placeList[i].placeObject.place,
				icon : '${pageContext.request.contextPath}/images/signpost.png',
				clickable : true
			});
		
			google.maps.event.addListener(marker, 'click', (function(marker, i) {
				return function() {
					
					var myvar = placeList[i].id;
					var content = '<div class="myBackground info-window">';
					content += '<div class="pageImageContainer">';
					content += '<img class="pageImage" src="'+'uploads/journeyProfileMini/'+placeList[i].zdjecie+'"/></div>';
					content += '<strong>Nazwa: <a href="journey?action=journey&id=' + myvar + '">' + placeList[i].nazwa + '</a><br/>';
					content += 'Organizator: ' + placeList[i].organizator + '<br/>';
					content += 'Miejsce: ' + placeList[i].placeObject.place + '</strong></div>';
					
					infoWindow.setContent(content);
					infoWindow.open(map, marker);
				};
			})(marker, i));
		}
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
<%@include file="../../jspf/navbar.jspf"%>
<div class="row">
	<div class="col-xs-8">
		<div class="col-xs-12">
			<div class="myBackground mapShadow">
				<div id="map_canvas" class="img-rounded"></div>
			</div>

		</div>
	</div>

	<%@include file="../../jspf/rightColumn.jspf"%>

	<%@include file="../../jspf/footer.jspf"%>