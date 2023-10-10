<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
     <%@ page import="model.TVSeries" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home | Movie Series </title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="styles.css" >
</head>
<body>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
    <h4>Hello, <%= request.getAttribute("name")%></h4>
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div >
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" style="color:white" aria-current="page" href="index">
             Home
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" style="color:white" href="account-overview">Account Overview</a>
        </li>
        <li class="nav-item">
	        <form action="<%= request.getContextPath() %>/login.jsp" method="post">
			  <input style="background-color: darkblue;color:white;border:none" class="my-2 link-opacity-10" type="submit" value="Logout" >
		    </form>
	     </li>
      </ul>
    </div>
  </div>
</nav>

<%
	String userName = null;
	Cookie[] cookies = request.getCookies();
	if(cookies !=null){
	  for(Cookie cookie : cookies){
		if(cookie.getName().equals("username")) userName = cookie.getValue();
	  }
	}

if(userName == null) response.sendRedirect("login.jsp");
%>

<div class="container my-2">
  
	
	 <div class="row">
         
          <% List <TVSeries> itemList = (List<TVSeries>) request.getAttribute("data");
        if (itemList != null) {
            for (TVSeries item : itemList) { %>
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card">
                        <img src="<%= item.getMainBanner() %>" class="card-img-top"
                             alt="<%= item.getTitle() %> Image">
                        <div class="card-body">
                            <h5 class="card-title"><%=item.getTitle() %></h5>
                            <p class="card-text"><%= item.getDescription() %></p>
                        </div>
                    </div>
                </div>
        <% }
        } %>
          
          
          
     </div>     

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>