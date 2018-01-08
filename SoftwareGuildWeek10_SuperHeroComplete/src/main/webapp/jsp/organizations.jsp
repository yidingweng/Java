<%-- 
    Document   : organizations
    Created on : Jul 16, 2017, 1:36:09 AM
    Author     : yidingweng
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <head>
        <title>Organizations</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    </head>
    <body>
        <div class="container">
            <h1>Organizations</h1>
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
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySuperPowersPage">
                            Super Powers
                        </a>
                    </li>
                    <li role="presentation"
                        class="active">
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
                    <h2>Current Organizations</h2>
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            <th width="20%">Organization Name</th>
                            <th width="20%">Description</th>
                            <th width="20%">Address</th>
                            <th width="20%">Email</th>
                            <th width="10%"></th>
                            <th width="10%"></th>
                            
                        </tr>
                        <c:forEach var="currentOrganization" items="${organizationList}">
                            <tr>
                                <td>
                                    <a href="displayOrganizationDetails?organizationid=${currentOrganization.organizationid}">
                                        <c:out value="${currentOrganization.organizationName}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentOrganization.description}"/>
                                </td>
                                <td>
                                    <c:out value="${currentOrganization.address}"/>
                                </td>
                                <td>
                                    <c:out value="${currentOrganization.email}"/>
                                </td>
                                <td>
                                    <a href="displayEditOrganizationForm?organizationid=${currentOrganization.organizationid}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteOrganization?organizationid=${currentOrganization.organizationid}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                
                <div class="col-md-6">
                    <h2>Add New Organization</h2>
                    <form class="form-horizontal" 
                        role="form" method="POST" 
                        action="createOrganization"> 
                        <div class="form-group">
                            <label for="add-organization-name" class="col-md-4 control-label">Organization Name:</label>
                            <div class="col-md-8">
                              <input type="text" class="form-control" name="organizationName" placeholder="Organization Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="description" placeholder="Description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-address" class="col-md-4 control-label">Address:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="address" placeholder="Address"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-email" class="col-md-4 control-label">Email:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="email" placeholder="Email"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Organization"/>
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
