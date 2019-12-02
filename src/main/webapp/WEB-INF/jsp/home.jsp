<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="bootstrap.css" rel="stylesheet">
<link href="style.css" rel="stylesheet">
<html>
<head>
</head>

<body>
        <div class="jumbotron text-center ">
            Welcome <c:out value= "${name}"/>!
            </br>
            <c:choose>
                <c:when test="${isApproved != 1}">
                    Team: Name waiting admin approval
                </c:when>
                <c:otherwise>
                    Team: <c:out value = "${userTeam.teamName}"/>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="container">
                <div class="row">
                    <div class="col-sm">
                      <h4>Distance Swam:</h4>
                      <p><c:out value = "${userTeam.distanceSwam}"/> Miles / 2.4 Miles <br>
                          <c:choose>
                              <c:when test = "${userTeam.distanceSwam >= 2.4}">
                                  You have finished the swimming portion of the race! <br>
                              </c:when>
                              <c:otherwise>
                                  </br>
                              </c:otherwise>
                          </c:choose>

                      </p>
                    </div>
                    <div class="col-sm">
                      <h4>Distance Biked:</h4>
                      <p><c:out value = "${userTeam.distanceBiked}"/> Miles / 112.0 Miles <br>
                          <c:choose>
                              <c:when test = "${userTeam.distanceBiked >= 112.0}">
                                  You have finished the biking portion of the race! <br>
                              </c:when>
                              <c:otherwise>
                                  </br>
                              </c:otherwise>
                          </c:choose>
                      </p>
                    </div>
                    <div class="col-sm">
                      <h4>Distance Ran:</h4>
                      <p><c:out value = "${userTeam.distanceRan}"/> Miles / 26.2 Miles <br>
                          <c:choose>
                              <c:when test = "${userTeam.distanceRan >= 26.2}">
                                  You have finished the running portion of the race! <br>
                              </c:when>
                              <c:otherwise>
                                  </br>
                              </c:otherwise>
                          </c:choose>
                      </p>
                    </div>
                </div>
                <div class="row text-center">
                    <div class="col">
                        <form action="/log">
                            <button class = "button">Enter new race log</button>
                        </form>
                    </div>
                </div>
                <div class="row text-center">
                    <div class="col">
                        <form action="/logout">
                            <button class="button">Logout</button>
                        </form>
                    </div>
                </div>
        </div>
</body>
</html>