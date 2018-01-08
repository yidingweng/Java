<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    
    <body>
        <div class="container">
            <h1 id="mainHeading" class = "text-center">Vending Machine</h1>
            <hr width="1296" >
            <div id = "snacksTable" class= "col-md-9 text-center">
                
                <c:forEach var="currentSnack" items="${snacksList}">
                    <form method="post" action="getUserChoice/${currentSnack.name}">
                        <button type ="submit" class="col-md-4 snackButton" value="${currentSnack.index}">
                        
                            <h4><c:out value="${currentSnack.name}"/></h4>
                            <p>price <c:out value="${currentSnack.price}"/> $ </p>
                            <hr>
                            <p>Quantity Left: <c:out value="${currentSnack.amount}"/></p>
                        </button>
                    </form>
                </c:forEach>
            </div>


            <div class= "col-md-3 text-center">

                <h4>Total $ In</h4>

                <div id = "increment" class="display">${total}
                </div>

                <div class="row">
                    <form method = "post" action = "takeMoney/100">
                        <button class = "btn-default" type = "submit" class="col-md-6">
                          <h5>Add Dollar</h5>
                        </button>
                    </form>
             
                    <form method = "post" action = "takeMoney/25">
                        <button class = "btn-default" type = "submit" class="col-md-6">
                          <h5>Add Quarter</h5>
                        </button>
                    </form>
                </div>
                
                <div class="row">
                    <form method = "post" action = "takeMoney/10">
                          <button class = "btn-default" type = "submit"class="col-md-6">
                          <h5>Add Dime</h5>
                        </button>
                    </form>
               

                    <form method = "post" action = "takeMoney/5">
                        <button class = "btn-default" type = "submit" class="col-md-6">
                          <h5>Add Nickel</h5>
                        </button>
                    </form>
                </div>

                <hr>
                <h4>Messages</h4>

                <div id = "displayInfo" class="display">${messageInfo}
                </div>

                <div id = "itemChoice" class="display">Item: ${itemChoice}
                    <div id = "choiceNumber"></div>
                </div>

                
                <form method = "post" action = "makePurchase">
                   <button id = "makePurchase" type = "submit" class="Center" >
                      <h5>Make Purchase</h5>
                    </button>
                </form>
                
                <hr>

                <form method = "post" action = "changeReturn">
                    <button id = "changeReturn" type="submit" class="Center">
                        <h5>Change Return</h5>
                    </button>
                </form>
                
                <div id = "change" class="display">
                    <h4>${changeInfo}</h4>
                </div>
                
            </div>
        </div>
        


        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

