<%-- 
    Document   : editSightingForm
    Created on : Jul 19, 2017, 11:58:51 PM
    Author     : yidingweng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <title>Sightings</title>
    </head>
    <body>
        <div class="container">
            <h1>Edit Sighting</h1>
            <hr/>
            
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"> 
                        <a href="${pageContext.request.contextPath}/index.jsp">
                            Home
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySuperHerosPage">
                            Super Heros
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySuperPowersPage">
                            Super Powers
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayOrganizationsPage">
                            Organizations
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayLocationsPage">
                            Locations
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySightingsPage">
                            Sightings
                        </a>
                    </li>
                </ul>
            </div>
                            
            <sf:form class="form-horizontal" role="form" modelAttribute="sighting" action="editSighting" method="POST">
                <div class="form-group">
                    <label for="add-sightingDate" class="col-md-4 control-label">Date:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-sightingDate"
                                  path="sightingDate" placeholder="Date"/>
                        <sf:errors path="sightingDate" cssclass="error"></sf:errors>
                        <sf:hidden path="sightingid"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-location" class="col-md-4 control-label">Location:</label>
                    <div class="col-md-8">
                        <select name="locationid">
                            <c:forEach var="currentLocation" items="${locationList}">
                                <option value="${currentLocation.locationid}">${currentLocation.locationName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-superHero" class="col-md-4 control-label">Super Hero:</label>
                    <div class="col-md-8">
                        <select name="superHero">
                            <c:forEach var="currentSuperHero" items="${superHeroList}">
                                <option value="${currentSuperHero.superHeroid}">${currentSuperHero.superHeroName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Sighting"/>
                    </div>
                </div>
            </sf:form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
