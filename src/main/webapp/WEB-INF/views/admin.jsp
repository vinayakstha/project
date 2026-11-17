<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Admin Dashboard</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body>

<div class="container mt-5">

    <h2 class="mb-4">Admin Dashboard</h2>

    <h4 class="mb-3">Customer Orders</h4>

    <div class="table-responsive">

        <table class="table table-bordered table-striped">

            <thead class="table-dark">
            <tr>
                <th>Order ID</th>
                <th>Customer Name</th>
                <th>Customer Email</th>
                <th>Products</th>
                <th>Total Price</th>
                <th>Status</th>
            </tr>
            </thead>

            <tbody>

            <c:forEach var="order" items="${orders}">

                <tr>

                    <td>${order.orderId}</td>

                    <td>${order.customerName}</td>

                    <td>${order.customerEmail}</td>

                    <td>
                        <c:forEach var="item" items="${order.orderItems}">

                            <div class="mb-2">
                                <strong>${item.productName}</strong>
                                <br>
                                Quantity: ${item.quantity}
                                <br>
                                Price: Rs. ${item.price}
                            </div>

                        </c:forEach>
                    </td>

                    <td>Rs. ${order.price}</td>

                    <td>
                        <span class="badge bg-primary">
                            ${order.orderStatus}
                        </span>
                    </td>

                </tr>

            </c:forEach>

            <c:if test="${empty orders}">
                <tr>
                    <td colspan="6" class="text-center">
                        No orders found.
                    </td>
                </tr>
            </c:if>

            </tbody>

        </table>

    </div>

</div>

</body>
</html>
