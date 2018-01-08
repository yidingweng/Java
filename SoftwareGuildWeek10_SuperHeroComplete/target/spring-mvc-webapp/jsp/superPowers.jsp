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
            <h1>Super Powers</h1>
            <hr/>
            <div class="navbar">
                <ul class ="nav nav-tabs">
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
                    <li role="presentation"
                        class="active">
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
                    <h2>Current Super Powers</h2>
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            <th width="40%">Super Power Name</th>
                            <th width="30%">Description</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentSuperPower" items="${superPowerList}">
                            <tr>
                                <td>
                                    <a href="displaySuperPowerDetails?superPowerid=${currentSuperPower.superPowerid}">
                                        <c:out value="${currentSuperPower.powerName}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentSuperPower.description}"/>
                                </td>
                                <td>
                                    <a href="displayEditSuperPowerForm?superPowerid=${currentSuperPower.superPowerid}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteSuperPower?superPowerid=${currentSuperPower.superPowerid}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <div class="col-md-6">
                    <h2>Add New Super Power</h2>
                    <form class="form-horizontal" 
                        role="form" method="POST" 
                        action="createSuperPower"> 
                        <div class="form-group">
                            <label for="add-superPower-name" class="col-md-4 control-label">Super Power Name:</label>
                            <div class="col-md-8">
                              <input type="text" class="form-control" name="superPowerName" placeholder="Super Power Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="description" placeholder="Description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Super Power"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        </div>
        
    </body>
</html>
