<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title>Product Management</title>
<jsp:include page="../common/head.jsp" />
<link rel="stylesheet" href="<c:url value='/css/product.css'/>">
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<div class="container">
		<div class="sub-header">
			<div class="float-left sub-title">Product Management</div>	
			<div class="float-right">
				<a class="btn btn-success add-btn as" id="addProductInfoModal"><i
					class="fas fa-plus-square"></i> Add Product</a>
			</div>
		</div>

		<div class="search-area">
			<div class="search-product form-group">
				<div class="search-product__name">
					<input type="text" class="form-control search-brand"
						placeholder="Product Name or Brand Name" id="keyword">
				</div>
				<div class="search-product__price">
					<label class="price-labe price-labe-from" for="priceFrom">Price
						From </label> <input type="text" class="price priceFrom form-control"
						name="priceForm" id="priceFrom" pattern="^d{1,3}(,\d{3})?₫"
						value="" data-type="currency" placeholder="1,000,000.00₫">
					<label class="price-labe" for="toPrice">Price To </label> <input
						type="text" class="price priceTo form-control" name="priceTo"
						id="priceTo" pattern="^d{1,3}(,\d{3})?₫" value=""
						data-type="currency" placeholder="1,000,000.00₫">
				</div>
				<div class="btn-search">
					<button type="submit" id="searchByPrice" class="btn btn-success">Search</button>
					<button type="button" class="reset-page btn btn-secondary rs"
						id="restPage">Reset</button>
				</div>
			</div>
		</div>
			<div id="resultSearch"> 
				<p></p>
			</div>
		
		<table class="table table-bordered" id="productInfoTable">
			<thead>
				<tr class="text-center">
					<th scope="col">#</th>
					<th scope="col">Product</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Brand</th>
					<th scope="col">Sale Date</th>
					<th scope="col">Image</th>
					<th scope="col">Description</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<div class="d-flex justify-content-center">
			<ul class="pagination">
			</ul>
		</div>
	</div>
	<!-- Modal form add new product and Edit product -->
	<div class="modal fade" id="productInfoModal">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<form id="productInfoForm" role="form" enctype="multipart/form-data">
					<div class="modal-header">
						<h5 class="modal-title">Add New Product</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group d-none">
							<label for="productId">Product ID</label> <input type="text"
								class="form-control" name="productId" id="productId"
								placeholder="Product ID" readonly="readonly">
						</div>
						<div class="form-group product-name" id="product-name">
							<label for="productName">Product name <span
								class="required-field">(*)</span></label> <input type="text"
								class="form-control" name="productName" id="productName"
								placeholder="Name of the product"
								aria-describedby="basic-addon1">
						</div>
						<div class="group-quantity-price">
							<div class="form-group quantity-form">
								<label for="quantity">Quantity <span
									class="required-field">(*)</span></label> <input type="number"
									class="form-control quantity-input" name="quantity"
									id="quantity" placeholder="Quantity of product">
							</div>
							<div class="form-group price-form">
								<label for="price">Price <span class="required-field">(*)</span></label>
								<input type="text" class="form-control" name="price" id="price"
									placeholder="Price of product">
							</div>
						</div>
						<!-- Brand name -->
						<div class="group-quantity-price">
							<div class="form-group brand-logo">
								<label for="brandId">Brand Name</label> <select
									class="form-control" id="brandId" name="brandEntity.brandId">
									<c:forEach items="${listBrand}" var="brand">
										<option value="${brand.brandId}" class="form-select">${brand.brandName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group saleDate">
								<label for="saleDate">Sale Date <span
									class="required-field">(*)</span></label> <input type="date"
									class="form-control" name="saleDate" id="saleDate" value="">
							</div>
						</div>
						<div class="group-img-description">
							<div class="form-group img-product" id="productImage">
								<label for="image">Image <span class="required-mask">(*)</span></label>
								<div class="preview-image-upload" id="logoImg">
									<img src="<c:url value='/images/image-demo.png'/>" alt="image">
								</div>
								<input type="file" class="form-control upload-image"
									name="imageFiles" accept="image/*" /> <input type="hidden"
									class="old-img" id="image" name="image">
							</div>
							<div class="form-group description">
								<label for="description">Description </label>
								<textarea cols="30" rows="3" class="form-control"
									name="description" id="description"
									placeholder="Description of product"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancel</button>
						<button type="submit" class="btn btn-primary" id="saveProductBtn">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Modal confirm deleting product -->
	<div class="modal fade" id="confirmDeleteModal">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Delete Product</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>
						Do you want to delete <b id="deletedProductName"></b>?
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="deleteSubmitBtn">Confirm</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp" />
	<script src="<c:url value='/js/product.js'/>"></script>
</body>
</html>