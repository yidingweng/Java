<%-- 
    Document   : editSuperHeroForm
    Created on : Jul 17, 2017, 9:58:15 AM
    Author     : yidingweng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <title>Super Heros</title>
    </head>
    <body>
        <div class="container">
            <h1>Edit Super Hero </h1>
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
            
            <sf:form class="form-horizontal" role="form" modelAttribute="superHero" action="editSuperHero" method="POST">
                <div class="form-group">
                    <label for="add-superHero-name" class="col-md-4 control-label">Super Hero Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-superHero-name"
                                  path="superHeroName" placeholder="Super Hero Name"/>
                        <sf:errors path="superHeroName" cssclass="error"></sf:errors>
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
                    <label for="add-nature" class="col-md-4 control-label">Nature:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-nature"
                                  path="nature" placeholder="Nature"/>
                        <sf:errors path="nature" cssclass="error"></sf:errors>
                        <sf:hidden path="superHeroid"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-superPower" class="col-md-4 control-label">Super Power:</label>
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
                        <input type="submit" class="btn btn-default" value="Update Super Hero"/>
                    </div>
                </div>
            </sf:form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
