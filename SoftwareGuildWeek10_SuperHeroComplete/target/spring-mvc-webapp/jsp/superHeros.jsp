<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Super Heros</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Super Heros</h1>
            <hr/><!-- </hr> to <hr/> almost a typo -->
            <div class="navbar">
                <ul class ="nav nav-tabs"><!-- tab to tabs almost a typo -->
                    <li role="presentation"> 
                        <a href="${pageContext.request.contextPath}/index.jsp">
                            Home
                        </a>
                    </li>
                    <li role="presentation"
                        class="active"> <!-- active will move around for active page -->
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
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySightingsPage">
                            Sightings
                        </a>
                    </li>
                </ul>
            </div>
                            
            <div class="row">
                <div class="col-md-6">
                    <h2>Current Super Heros</h2>
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            <th width="23.3%">Super Hero Name</th>
                            <th width="23.3%">Description</th>
                            <th width="23.4%">Nature</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentSuperHero" items="${superHeroList}">
                            <tr>
                                <td>
                                    <a href="displaySuperHeroDetails?superHeroid=${currentSuperHero.superHeroid}">
                                        <c:out value="${currentSuperHero.superHeroName}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentSuperHero.description}"/>
                                </td>
                                <td>
                                    <c:out value="${currentSuperHero.nature}"/>
                                </td>
                                <td>
                                    <a href="displayEditSuperHeroForm?superHeroid=${currentSuperHero.superHeroid}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteSuperHero?superHeroid=${currentSuperHero.superHeroid}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                
                <div class="col-md-6">
                    <h2>Add New Super Hero</h2>
                    <form class="form-horizontal" 
                        role="form" method="POST" 
                        action="createSuperHero"> 
                        <div class="form-group">
                            <label for="add-superHero-name" class="col-md-4 control-label">Super Hero Name:</label>
                            <div class="col-md-8">
                              <input type="text" class="form-control" name="superHeroName" placeholder="Super Hero Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="description" placeholder="Description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-nature" class="col-md-4 control-label">Nature:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="nature" placeholder="Nature"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-superPower" class="col-md-4 control-label">SuperPower:</label>
                            <div class="col-md-8">
                                <select name="superPower">
                                    <c:forEach var="currentSuperPower" items="${superPowerList}">
                                        <option value="${currentSuperPower.superPowerid}">${currentSuperPower.powerName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-organization" class="col-md-4 control-label">Organization:</label>
                            <div class="col-md-8">
                                <select name="organization">
                                    <c:forEach var="currentOrganization" items="${organizationList}">
                                        <option value="${currentOrganization.organizationid}">${currentOrganization.organizationName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Super Hero"/>
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
