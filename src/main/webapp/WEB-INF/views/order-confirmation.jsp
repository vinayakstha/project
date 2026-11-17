
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Order Confirmation</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body class="bg-light">

<div class="container py-5">

    <div class="card shadow-sm mx-auto text-center p-5"
         style="max-width: 550px;">

        <h1 class="text-success mb-3">
            Order Placed Successfully!
        </h1>

        <p class="text-secondary">
            Thank you for shopping with us.
            Your order has been received.
        </p>

        <a href="${pageContext.request.contextPath}/home"
           class="btn btn-primary mt-3">
            Continue Shopping
        </a>

    </div>

</div>

</body>
</html>
