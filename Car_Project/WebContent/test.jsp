<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<script src="jquery-3.3.1.min.js"></script>
	<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
	
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="./styles/style.css">
	
    <meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
      
    	 
	    	<jsp:include page='./components/header.jsp'></jsp:include>
	    
      
	  <div class="container-fluid text-center" style="background-image: url('https://cdn.pixabay.com/photo/2016/06/18/17/42/image-1465348_960_720.jpg')">
    		 <h1 class="display-4">Fluid jumbotron</h1>
    		 <p class="lead">This is a modified jumbotron that occupies the entire horizontal space of its parent.</p>
      </div>
    	
   		 <div class="jumbotron">
   		 
   		 
   		 </div>
    	
    



    <%-- <c:forEach var = "item" items="${listCategory}">
	<td>
        <p>${item}</p>
    </td>     
    </c:forEach>

<form action="Basic" method="post">
    <table>
        <tr>
            <td><p>Enter your name:</p></td>
            <td><input name="user" face="verdana" size="2px" type="text"></td>
        </tr>
        <tr>
            <td><p>Enter your password:</p></td>
            <td><input face="verdana" size="2px" name="password" type="password"></td>
        </tr>
    </table>

    <input type="submit" value="Login">
    
</form> --%>

   
</body>

<script>
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
  if (document.body.scrollTop > 80 || document.documentElement.scrollTop > 80) {
    document.getElementById("navbar").style.padding = "30px 10px";
    document.getElementById("logo").style.fontSize = "25px";
  } else {
    document.getElementById("navbar").style.padding = "80px 10px";
    document.getElementById("logo").style.fontSize = "35px";
  }
}

</script>

</html>