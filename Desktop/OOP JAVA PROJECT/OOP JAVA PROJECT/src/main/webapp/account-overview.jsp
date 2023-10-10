<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Overview</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="styles.css" >
</head>
<body>
  
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
     <h4> Hello, <%= request.getAttribute("name")%></h4>
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div>
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" style="color:white" href="index">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" style="color:white" aria-current="page" href="account-overview">Account Overview</a>
        </li>
      </ul>
    </div>
  </div>
</nav>


<div class="container">
       <h1 class="mt-5">Profile Update</h1>  
       
       <br/>
   
 <%
String profileImageUrl = (String) request.getAttribute("profileImageUrl");
if (profileImageUrl != null && !profileImageUrl.isEmpty()) {
%>
    <img src="<%= profileImageUrl %>" alt="Profile Image" width="200" height="200">
<%
}
%>
   
        
        <!-- Profile Photo Upload -->
       <form id="profileURLForm" class="mt-4">
        <h2>Edit Profile Picture</h2>
        <div class="mb-3">
           <input type="text" class="form-control" id="profileUrl" placeholder="Enter Profile Path Image URL">
         </div>
         <button type="submit" class="btn btn-success">Save</button>
          </form>
        
       <form id="editNameForm" class="mt-4">
        <h2>Edit Name</h2>
        <div class="mb-3">
           <input type="text" class="form-control" id="name" placeholder="Enter your name">
         </div>
         <button type="submit" class="btn btn-success">Save</button>
          </form>
 </div>
 

 <script>
 document.getElementById('editNameForm').addEventListener('submit', function (e) {
     e.preventDefault();
     
     var newName = document.getElementById('name').value;

     // Make a PUT request to your server with the new name
     fetch(location.pathname, {
         method: 'PUT',
         headers: {
             'Content-Type': 'application/json'
         },
         body: JSON.stringify({ name: newName })
     })
     .then(response => {
    	 console.log(response);
         if (response.ok) {
             // Handle success, e.g., show a success message
             alert('Name updated successfully.');
             location.reload();
         } else {
             // Handle error, e.g., show an error message
             alert('Error updating name.');
         }
     })
     .catch(error => {
         // Handle network or other errors
         console.error('Error:', error);
     });
 });
 
 
 document.getElementById('profileURLForm').addEventListener('submit', function (e) {
     e.preventDefault();
     
     var path = document.getElementById('profileUrl').value;

     // Make a PUT request to your server with the new name
     fetch(location.pathname, {
         method: 'PUT',
         headers: {
             'Content-Type': 'application/json'
         },
         body: JSON.stringify({ imageURL: path })
     })
     .then(response => {
    	 console.log(response);
         if (response.ok) {
             // Handle success, e.g., show a success message
             alert('Profile Image updated successfully.');
             location.reload();
         } else {
             // Handle error, e.g., show an error message
             alert('Error updating name.');
         }
     })
     .catch(error => {
         // Handle network or other errors
         console.error('Error:', error);
     });
 });

</script>



</body>
</html>