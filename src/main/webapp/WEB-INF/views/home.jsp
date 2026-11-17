<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
              rel="stylesheet">

    <title>Products</title>
</head>
<body>

<div class="container mt-5">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <h1 class="mb-0">Products</h1>

        <a href="${pageContext.request.contextPath}/cart"
           class="btn btn-primary">
            My Cart
        </a>

    </div>

    <div class="row g-4">

        <c:forEach var="product" items="${products}">

            <%@ include file="components/product-card.jsp" %>

        </c:forEach>

    </div>

</div>

</body>
</html>