<%@include file="jspf/header.jspf"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/image_css.css">
<script src="${pageContext.request.contextPath}/js/kkcountdown.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".kkcountdown").kkcountdown();
	});
</script>
<%@include file="jspf/navbar.jspf"%>
<div class="row">
	<div class="col-xs-8 block-margin">
		<c:forEach items="${journeyList}" var="item" begin="${0+10*(page-1)}" end="${9+10*(page-1)}">
			<div class="col-xs-12 myBackground myMargin">
				<div class="col-xs-5 journey-right">
					<img src="/uploads/journeyProfileMini/${item.getZdjecie()}" alt="photo" class="img-rounded profile-image"></img>
				</div>
				<div class="col-xs-7">
					<div>
						<a class="journey-title" href="<c:url value="/journey"><c:param name="action" value="journey" /><c:param name="id" value="${item.getId()}" /></c:url>">${item.getNazwa()}</a><br /> <br>
						<span class="kkcountdown journey-text" data-time="${item.getData().getMillis()/1000}"></span><span class="journey-text date-text">${item.getData().toString().substring(0, 10)}</span>
						<p class="journey-text">${item.getPlaceObject().getPlace()}</p>
					</div>
					<div>
						<c:choose>
							<c:when test="${item.getData().getMillis() <=  currentTime && item.getCzas().getMillis() <= currentTime}">
								<img width="190px" src="${pageContext.request.contextPath}/images/stempel.png" alt="stempel" class="img-responsive stamp" />
							</c:when>
							<c:when test="${item.getData().getMillis() >=  currentTime}">
								<img width="190px" src="${pageContext.request.contextPath}/images/stempel2.png" alt="stempel" class="img-responsive stamp" />
							</c:when>
							<c:otherwise>
								<img width="190px" src="${pageContext.request.contextPath}/images/stempel3.png" alt="stempel" class="img-responsive stamp" />
							</c:otherwise>
						</c:choose>
						<div>
							<%@include file="jspf/sports.jspf"%>
						</div>
						<div class="edit-image">
							<c:if test="${user == item.getOrganizator()}">
								<a href="<c:url value="/editJourney"><c:param name="action" value="editJourney" /><c:param name="id" value="${item.getId()}" /></c:url>"><img src="${pageContext.request.contextPath}/images/edit.png" alt="edit" class="img-responsive" style="float: left" /></a>
								<a href="<c:url value="/deleteJourney"><c:param name="action" value="deleteJourney" /><c:param name="id" value="${item.getId()}" /></c:url>"><img src="${pageContext.request.contextPath}/images/delete2.png" width="32px" alt="delete" class="img-responsive" /></a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<%@include file="jspf/rightColumn.jspf"%>
	<div id="pagination" class="myPaginationContainer">
		<ul class="pagination mypagination">
			<c:choose>
				<c:when test="${page==1}">
					<li class="disabled">
				</c:when>
				<c:otherwise>
					<li>
				</c:otherwise>
			</c:choose>
			<a href="<c:url value="/journeys"><c:param name="action" value="journeys" /><c:param name="page" value="1" /></c:url>"><<</a>
			</li>
			<c:forEach var="i" begin="1" end="${(size/10)+(1-((size/10)%1))%1}">
				<c:choose>
					<c:when test="${page==i}">
						<li class="active">
					</c:when>
					<c:otherwise>
						<li>
					</c:otherwise>
				</c:choose>
				<a href="<c:url value="/journeys"><c:param name="action" value="journeys" /><c:param name="page" value="${i}" /></c:url>">${i}</a>
				</li>
			</c:forEach>
			<c:choose>
				<c:when test="${page==((size/10)+(1-((size/10)%1))%1)}">
					<li class="disabled">
				</c:when>
				<c:otherwise>
					<li>
				</c:otherwise>
			</c:choose>
			<a href="<c:url value="/journeys"><c:param name="action" value="journeys" /><c:param name="page" value="${pageNumbers}" /></c:url>">>></a>
			</li>
		</ul>
	</div>
	<%@include file="jspf/footer.jspf"%>