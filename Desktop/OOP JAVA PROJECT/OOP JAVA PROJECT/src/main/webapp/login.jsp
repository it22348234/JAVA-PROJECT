<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="styles.css" >
</head>
<body>


<div align="center">
 
  <%
        // Cookie name to be removed
        String cookieName = "username";

        // Get all cookies from the request
        Cookie[] cookies = request.getCookies();

        // Check if cookies exist
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // Check if the current cookie matches the name we want to remove
                if (cookie.getName().equals(cookieName)) {
                    // Set the max age of the cookie to 0 to delete it
                    cookie.setMaxAge(0);
                    // Add the modified cookie to the response
                    response.addCookie(cookie);
                    out.println("Cookie '" + cookieName + "' has been removed.");
                    break; // No need to continue searching
                }
            }
        }
    %>
  
  
  <div class="form-body">
        <div class="row">
            <div class="form-holder">
                <div class="form-content">
                    <div class="form-items">
                    
                    <%
						String error = (String) request.getAttribute("errorMessage");
						if (error != null && !error.isEmpty()) {
							%>
 						   <p style="color:red"><%= error %></p>
							<%
							}
						%>
                        
                        <p>Fill in the data below.</p>
                        <form action="<%= request.getContextPath() %>/login" method="post">

                            <div class="col-md-12">
                            	<input placeholder="User Name" required class="form-control"  type="text" name="username" />
                            </div>

                            <div class="col-md-12">
                                  <input class="form-control" type="password" name="password" placeholder="Password" required>
                            </div>

                            <div class="form-button mt-3">
                                <button id="submit" type="submit" class="btn btn-primary">Log In</button>
                            </div>
                        </form>
                        
                        <a class="mt-2 icon-link icon-link-hover" href="registration.jsp">
  							Register Now 
						</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
  
  
</div>
 
 
 
</body>
</html>