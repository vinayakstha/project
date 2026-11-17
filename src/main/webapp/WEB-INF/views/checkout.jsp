
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Checkout</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body class="bg-light">

<div class="container py-5">

    <!-- Page Heading -->
    <div class="mb-4">
        <h2>Checkout</h2>
        <p class="text-secondary">
            Review your order before placing it.
        </p>
    </div>

    <div class="row justify-content-center">

        <div class="col-lg-8">

            <div class="card shadow-sm">

                <div class="card-body p-4">

                    <h5 class="mb-4">Order Summary</h5>

                    <!-- Cart Products -->
                    <c:forEach var="item" items="${cart.cartItem}">

                        <div class="d-flex justify-content-between align-items-center mb-3">

                            <div>

                                <strong>
                                    ${item.productName}
                                </strong>

                                <p class="text-secondary mb-0">
                                    Quantity: ${item.quantity}
                                </p>

                            </div>

                            <span class="fw-semibold">
                                Rs. ${item.subTotal}
                            </span>

                        </div>

                    </c:forEach>

                    <hr>

                    <!-- Total Items -->
                    <div class="d-flex justify-content-between mb-3">

                        <span>Total Items</span>

                        <span>
                            ${cart.totalItems}
                        </span>

                    </div>

                    <!-- Total Price -->
                    <div class="d-flex justify-content-between mb-4">

                        <h5>Total Amount</h5>

                        <h5 class="text-primary">
                            Rs. ${cart.totalPrice}
                        </h5>

                    </div>

                    <!-- Place Order -->
                    <form action="${pageContext.request.contextPath}/place-order"
                          method="post">

                        <button type="submit"
                                class="btn btn-primary w-100">

                            Place Order

                        </button>

                    </form>

                    <!-- Back to Cart -->
                    <a href="${pageContext.request.contextPath}/cart-jsp"
                       class="btn btn-outline-secondary w-100 mt-2">

                        Back to Cart

                    </a>

                </div>

            </div>

        </div>

    </div>

</div>

</body>
</html>
