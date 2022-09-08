$(window).on("load", function() {

});

$("#addItem").on("click", function() {
	var pizzaName = $("#pizzaName").val();
	var quantity = $("#quantity").val();
	var pizza = {
		pizzaName: pizzaName,
		quantity: quantity
	}

	var pizzaNameFlag = required(pizzaName, "pizzaname");
	var quantityFlag = required(quantity, "quantity");

	if (pizzaNameFlag && quantityFlag) {
		$("#pizzanameError").html("");
		$("#quantityError").html("");
		$("#cart").html("");
		$.ajax({
			url: uri+"/add/item",
			type: 'POST',
			data: JSON.stringify(pizza),
			contentType: 'application/json',
			success: function(response) {
				$("#cart-table").show();
				$("#pizzaOrder").show();
				$("#pizzaName").val("");
				$("#quantity").val("");
				$("#itemAddedSuccess").show();
				$("#itemAddedSuccess").delay(8000).fadeOut("slow");
				for (res in response) {
					$("#cart").append("<tr><td>" + response[res].pizzaName + "</td><td>" + response[res].quantity + "</td></tr>");
				}
			},
				error: function(response) {
					$("#pizzanameError").html(response.responseJSON.pizzaName);
					$('#pizzanameError').show();
					$("#quantityError").html(response.responseJSON.quantity);
					$('#quantityError').show();
				}
			});
	}
});

$("#pizzaOrder").on("click", function() {
	$.ajax({
		url: uri+"/order/pizza",
		type: 'GET',
		success: function(response) {
			$("#amount").val(response);
			$("#totalAmount").val(response);

		}
	});
});

function applyCoupons() {
	var amount = $("#amount").val();
	var coupon = $("#coupon").val();
	var pizzaOrder = {
		amount: amount,
		couponCode: coupon
	}
	$("#couponError").html("");
	$.ajax({
		url: uri+"/order/pizza/discount",
		type: 'POST',
		data: JSON.stringify(pizzaOrder),
		contentType: 'application/json',
		success: function(response) {
			$("#totalAmount").val(response);
		},
		error: function(response) {
			$("#couponError").html(response.responseJSON.message);
		}
	});
}

$("#pay").on("click", function() {
	var mode = $("#mode").val();
	var coupon = $("#coupon").val();
	var amount = $("#totalAmount").val();
	var payment = {
		mode: mode,
		couponCode: coupon,
		amount: amount
	}

	var modeFlag = required(mode, "paymentmode");

	if (modeFlag) {
		$.ajax({
			url: uri+"/pay/order",
			type: 'POST',
			data: JSON.stringify(payment),
			contentType: 'application/json',
			success: function() {
				$("#pizzaName").val("");
				$("#quantity").val("");
				$("#coupon").val("");
				$("#mode").val("");
				$('.close').click();
				$("#pizzaOrder").hide();
				$("#cart-table").hide();
				$("#cart").html("");
				$("#paymentSuccess").show();
				$("#paymentSuccess").delay(8000).fadeOut("slow");
			},
			error: function(response) {
				$("#paymentmodeError").html(response.responseJSON.pizzaName);
				$('#paymentmodeError').show();
			}
		});
	}
});
