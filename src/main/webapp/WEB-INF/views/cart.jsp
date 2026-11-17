<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>My Cart</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body class="bg-light">

<div class="container py-5">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Shopping Cart</h2>
        <a href="${pageContext.request.contextPath}/home"
           class="btn btn-outline-secondary">
            Continue Shopping
        </a>
    </div>

    <div class="row">

        <!-- Cart Products -->
        <div class="col-lg-8">

            <c:choose>

                <c:when test="${empty cart.cartItem}">

                    <div class="card shadow-sm p-5 text-center">
                        <h4>Your cart is empty</h4>

                        <p class="text-secondary">
                            Add some products to your cart.
                        </p>

                        <a href="${pageContext.request.contextPath}/home"
                           class="btn btn-primary">
                            Continue Shopping
                        </a>
                    </div>

                </c:when>

                <c:otherwise>

                    <c:forEach var="item" items="${cart.cartItem}">
                        <%@ include file="components/cart-card.jsp" %>
                    </c:forEach>

                </c:otherwise>

            </c:choose>

        </div>

        <!-- Order Summary -->
        <div class="col-lg-4">

            <div class="card shadow-sm">

                <div class="card-body">

                    <h5 class="mb-4">Order Summary</h5>

                    <div class="d-flex justify-content-between mb-3">
                        <span>Total Items</span>
                        <span>${cart.totalItems}</span>
                    </div>

                    <hr>

                    <div class="d-flex justify-content-between mb-4">
                        <h5>Total</h5>
                        <h5 class="text-primary">
                            Rs. ${cart.totalPrice}
                        </h5>
                    </div>

                    <button class="btn btn-primary w-100"
                            type="button"
                            onclick="checkout()">
                        Proceed to Checkout
                    </button>

                    <button class="btn btn-outline-danger w-100 mt-2"
                            type="button"
                            onclick="clearCart()">
                        Clear Cart
                    </button>

                </div>

            </div>

        </div>

    </div>

</div>

<script>

    const apiUrl = "${pageContext.request.contextPath}/cart-api";

    function getToken() {
        return localStorage.getItem("token");
    }

    function getHeaders() {

        const token = getToken();

        // The JSP login flow authenticates with a session cookie, so the
        // request works without a header. Send the token when one exists.
        return token
            ? { "Authorization": "Bearer " + token }
            : {};
    }

    async function updateQuantity(cartItemId, quantity) {

        if (quantity < 1) {
            return;
        }

        const headers = getHeaders();

        try {

            const response = await fetch(
                apiUrl + "/" + cartItemId + "/" + quantity,
                {
                    method: "PUT",
                    headers: headers
                }
            );

            if (response.ok) {
                location.reload();
            } else {
                alert("Unable to update quantity.");
            }

        } catch (error) {

            console.error("Update quantity error:", error);
            alert("Something went wrong.");

        }
    }

    async function removeFromCart(cartItemId) {

        if (!confirm("Remove this product from your cart?")) {
            return;
        }

        const headers = getHeaders();

        try {

            const response = await fetch(
                apiUrl + "/" + cartItemId,
                {
                    method: "DELETE",
                    headers: headers
                }
            );

            if (response.ok) {
                location.reload();
            } else {
                alert("Unable to remove product.");
            }

        } catch (error) {

            console.error("Remove product error:", error);
            alert("Something went wrong.");

        }
    }

    async function clearCart() {

        if (!confirm("Are you sure you want to clear your cart?")) {
            return;
        }

        const headers = getHeaders();

        try {

            const response = await fetch(
                apiUrl + "/clear",
                {
                    method: "DELETE",
                    headers: headers
                }
            );

            if (response.ok) {
                location.reload();
            } else {
                alert("Unable to clear cart.");
            }

        } catch (error) {

            console.error("Clear cart error:", error);
            alert("Something went wrong.");

        }
    }

    function checkout() {
        alert("Checkout functionality is not implemented yet.");
    }

</script>

</body>
</html>
