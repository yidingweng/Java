<%-- 
    Document   : editLocationForm
    Created on : Jul 16, 2017, 11:15:13 PM
    Author     : yidingweng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <title>Locations</title>
    </head>
    <body>
        <div class="container">
            <h1>Edit Location</h1>
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
            
            <sf:form class="form-horizontal" role="form" modelAttribute="location" action="editLocation" method="POST">
                <div class="form-group">
                    <label for="add-location-name" class="col-md-4 control-label">Location Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-location-name"
                                  path="locationName" placeholder="Location Name"/>
                        <sf:errors path="locationName" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-description" class="col-md-4 control-label">Description:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-description"
                                  path="description" placeholder="Description"/>
                        <sf:errors path="description" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-addressInfo" class="col-md-4 control-label">Address Info:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-addressInfo"
                                  path="addressInfo" placeholder="Address Info"/>
                        <sf:errors path="addressInfo" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-latitude"
                                  path="latitude" placeholder="Latitude"/>
                        <sf:errors path="latitude" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-longitude" class="col-md-4 control-label">Longitude:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-longitude"
                                  path="longitude" placeholder="Longitude"/>
                        <sf:errors path="longitude" cssclass="error"></sf:errors>
                        <sf:hidden path="locationid"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Organization"/>
                    </div>
                </div>
            </sf:form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
