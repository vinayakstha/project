<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="card shadow-sm mb-3">

    <div class="card-body">

        <div class="row align-items-center">

            <!-- Product Image -->
            <div class="col-md-3 text-center">

                <c:choose>
                    <c:when test="${not empty item.productImage}">
                        <img src="${item.productImage}"
                             class="img-fluid rounded"
                             alt="${item.productName}"
                             style="max-height: 150px; object-fit: contain;">
                    </c:when>

                    <c:otherwise>
                        <div class="bg-light rounded p-4">
                            <span class="text-secondary">
                                No Image
                            </span>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>

            <!-- Product Details -->
            <div class="col-md-5">

                <h5 class="mb-2">
                    ${item.productName}
                </h5>

                <p class="text-secondary mb-2">
                    Rs. ${item.productPrice}
                </p>

                <p class="fw-semibold mb-0">
                    Subtotal: Rs. ${item.subTotal}
                </p>

            </div>

            <!-- Quantity and Actions -->
            <div class="col-md-4">

                <label class="form-label fw-semibold">
                    Quantity
                </label>

                <div class="d-flex align-items-center gap-2">

                    <c:choose>
                        <c:when test="${item.quantity <= 1}">
                            <button type="button"
                                    class="btn btn-outline-secondary btn-sm"
                                    disabled>
                                -
                            </button>
                        </c:when>

                        <c:otherwise>
                            <button type="button"
                                    class="btn btn-outline-secondary btn-sm"
                                    onclick="updateQuantity(${item.cartItemId}, ${item.quantity - 1})">
                                -
                            </button>
                        </c:otherwise>
                    </c:choose>

                    <span class="fw-semibold">
                        ${item.quantity}
                    </span>

                    <button type="button"
                            class="btn btn-outline-secondary btn-sm"
                            onclick="updateQuantity(${item.cartItemId}, ${item.quantity + 1})">
                        +
                    </button>

                </div>

                <button type="button"
                        class="btn btn-outline-danger btn-sm mt-3"
                        onclick="removeFromCart(${item.cartItemId})">
                    Remove
                </button>

            </div>

        </div>

    </div>

</div>
