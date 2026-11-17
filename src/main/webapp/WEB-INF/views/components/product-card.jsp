<div class="col-md-4 col-lg-3">

    <div class="card h-100 shadow-sm">

        <img src="${product.productImage}"
             class="card-img-top"
             alt="${product.productName}"
             style="height: 200px; object-fit: cover;">

        <div class="card-body">

            <h5 class="card-title">
                ${product.productName}
            </h5>

            <p class="card-text text-secondary">
                ${product.productDescription}
            </p>

            <h5 class="text-primary">
                Rs. ${product.productPrice}
            </h5>

            <form action="${pageContext.request.contextPath}/cart/${product.productId}/1"
                  method="post">

                <button type="submit" class="btn btn-primary w-100">
                    <i class="bi bi-cart-plus"></i> Add to Cart
                </button>

            </form>

        </div>

    </div>

</div>