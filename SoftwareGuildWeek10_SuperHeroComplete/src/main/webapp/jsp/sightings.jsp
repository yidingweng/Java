
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sightings</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Sightings</h1>
            <hr/>
            <div class="navbar">
                <ul class ="nav nav-tabs"><!-- tab to tabs almost a typo -->
                    <li role="presentation"> 
                        <a href="${pageContext.request.contextPath}/index.jsp">
                            Home
                        </a>
                    </li>
                    <li role="presentation"> <!-- active will move around for active page -->
                        <a href="${pageContext.request.contextPath}/displaySuperHerosPage">
                            Super Heros
                        </a>
                    </li>
                    <li role="presentation"> <!-- active will move around for active page -->
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
                    <li role="presentation"
                        class="active">
                        <a href="${pageContext.request.contextPath}/displaySightingsPage">
                            Sightings
                        </a>
                    </li>
                </ul>
            </div>
            
            <div class="row">
                <div class="col-md-6">
                    <h2>Current Sightings</h2>
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            <th width="35%">Date</th>
                            <th width="35%">Location</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentSighting" items="${sightingList}">
                            <tr>
                                <td>
                                    <a href="displaySightingDetails?sightingid=${currentSighting.sightingid}">
                                        <c:out value="${currentSighting.sightingDate}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentSighting.location.getLocationName()}"/>
                                </td>
                                <td>
                                    <a href="displayEditSightingForm?sightingid=${currentSighting.sightingid}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteSighting?sightingid=${currentSighting.sightingid}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="col-md-6">
                    <h2>Add Sighting</h2>
                    <form class="form-horizontal" 
                        role="form" method="POST" 
                        action="createSighting"> 
                        <div class="form-group">
                            <label for="add-sightingDate" class="col-md-4 control-label">Date:</label>
                            <div class="col-md-8">
                              <input type="text" class="form-control" name="sightingDate" placeholder="dd/MM/yyyy"/>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="add-location" class="col-md-4 control-label">Location:</label>
                            <div class="col-md-8">
                                <select name="location">
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
                                <input type="submit" class="btn btn-default" value="Create Sighting"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        </div>
    </body>
</html>
