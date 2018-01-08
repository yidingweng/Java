<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Super Hero World</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" 
                        class="active">
                        <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
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
            <h2>Welcome to Super Hero World!</h2>
            <div class="row">
            <div class="col-md-6">
                <h2>Current Sightings</h2>
                <table id="contactTable" class="table table-hover">
                    <tr>
                        <th width="50%">Date</th>
                        <th width="50%">Location</th>
                    </tr>
                    <c:forEach var="currentSighting" items="${topTenSightings}">
                        <tr>
                            <td>
                                <c:out value="${currentSighting.sightingDate}"/>
                            </td>
                            <td>
                                <c:out value="${currentSighting.location.getLocationName()}"/>
                            </td>
                            
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="col-md-6">
                <form method = "get" action = "displayTopTenSightings">
                    <button class = "btn-default" type = "submit"class="col-md-6">
                        <h5>Display Newest Sightings</h5>
                    </button>
                </form>

            </div>
        </div>
            
        </div>
        

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

