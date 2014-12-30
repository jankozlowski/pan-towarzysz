<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../../jspf/header.jspf"%>
<script src="${pageContext.request.contextPath}/js/kkcountdown.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/joxview/yoxview-init.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/joxview/jquery.yoxview-2.21.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/yoxview.css">
<script type="text/javascript">
	$(document).ready(function() {
		$(".kkcountdown").kkcountdown();
		$(".yoxview").yoxview();
	});
</script>
<%@include file="../../jspf/navbar.jspf"%>

<div class="row">
	<div class="col-xs-8 block-margin">
		<div class="col-xs-12 myBackground myMargin">
			<div class="col-xs-8" style="text-align: center">
				<img class="img-rounded" src="/uploads/journeyProfile/${item.getZdjecie()}" alt="photo" style="max-width: 540px"></img>
			</div>
			<div class="col-xs-4">


				<p class="journey-label" style="margin-top: 0px">Czas rozpoczęcia:</p>
				<div class="journey-label-text">
					<span class="kkcountdown " data-time="${item.getData().getMillis()/1000}"></span>
				</div>
				<p class="journey-label">Miejsce rozpoczęcia:</p>
				<p class="journey-label-text">${item.getPlaceObject().getPlace()}</p>
				<p class="journey-label">Ilość osób:</p>

				<div class="journey-label-text" style="padding-top: 6px; height: 36px;">
					<c:if test="${item.getLudzie()==0}">
						<img src="${pageContext.request.contextPath}/images/question.png" style="margin-right: 30px" />
					</c:if>
					<c:if test="${item.getLudzie()>0}">
						<img src="${pageContext.request.contextPath}/images/walking.png" style="margin-right: 34px" />
						<img src="${pageContext.request.contextPath}/images/x.png" style="margin-right: 24px" />
						<span style="font-size: 18px; vertical-align: middle"><b>${item.getLudzie()}</b></span>
					</c:if>

				</div>
				<p class="journey-label">Trudność:</p>
				<div class="journey-label-text">
					<img src="${pageContext.request.contextPath}/images/tent.png" style="margin-right: 20px" /> <img src="${pageContext.request.contextPath}/images/x.png" style="margin-right: 24px" /> <span style="font-size: 18px; vertical-align: middle"><b>${item.getTrudnosc()}</b> </span>
				</div>
				<p class="journey-label">Rodzaj:</p>
				<div class="journey-label-text" style="padding-top: 5px">
					<%@include file="../../jspf/sports.jspf"%>
				</div>
			</div>

			<div class="col-xs-12" style="text-align: center">
				<p class="journey-label">Tytuł:</p>
				<p class="journey-label-text">${item.getNazwa()}</p>
				<p class="journey-label">Organizator:</p>
				<p class="journey-label-text">${item.getOrganizator()}</p>
				<c:if test="${item.getOpis()!=''}">
					<p class="journey-label">Opis:</p>
					<p class="journey-label-text">${item.getOpis()}</p>
				</c:if>
				<c:if test="${item.getKoszt()!=''}">
					<p class="journey-label">Koszt:</p>
					<p class="journey-label-text">${item.getKoszt()}</p>
				</c:if>
				<c:if test="${item.getSprzet()!=''}">
					<p class="journey-label">Sprzęt:</p>
					<p class="journey-label-text">${item.getSprzet()}&nbsp</p>
				</c:if>
				<c:if test="${item.getTrasa()!=''}">
					<p class="journey-label">Trasa:</p>
					<p class="journey-label-text">${item.getTrasa()}</p>
				</c:if>

			</div>
			<div></div>
			<div class="col-xs-12">
				<div class="thumbnails yoxview" style="clear: both">
					<p class="journey-label">Zdjęcia</p>
					<div class="journey-label-text" style="background-color: rgba(121, 215, 121, 0.15); padding: 10px;">
						<c:forEach items="${item.getJourneyPhotos()}" var="photo">
							<c:if test="${photo.getPhoto()!=''}">
								<a id='yoxviewGallery' href="/uploads/journeyImages/${photo.getPhoto()}"><img class="img-rounded" src="/uploads/journeyImagesMini/${photo.getPhoto()}" alt="photo" style="margin-bottom:5px"></img></a>
							</c:if>
						</c:forEach>
					</div>
				</div>
				<br>
			</div>
		</div>

	</div>

	<%@include file="../../jspf/rightColumn.jspf"%>
</div>
</div>

<%@include file="../../jspf/footer.jspf"%>
