<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Sign Up</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center min-vh-100">

    <div class="card shadow p-4" style="width: 450px;">

        <h2 class="text-center mb-4">Sign Up</h2>

        <form action="${pageContext.request.contextPath}/signup" method="post">

            <div class="mb-3">
                <label class="form-label">Full Name</label>
                <input type="text" class="form-control" name="fullName">
            </div>

            <div class="mb-3">
                <label class="form-label">Username</label>
                <input type="text" class="form-control" name="username">
            </div>

            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" class="form-control" name="email">
            </div>

            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password" class="form-control" name="password">
            </div>

            <div class="mb-3">
                <label class="form-label">Confirm Password</label>
                <input type="password" class="form-control" name="confirmPassword">
            </div>

            <div class="mb-3">
                <label class="form-label">Phone Number</label>
                <input type="tel" class="form-control" name="phoneNumber">
            </div>

            <button type="submit" class="btn btn-primary w-100">
                Sign Up
            </button>

        </form>

    </div>

</div>

</body>
</html>