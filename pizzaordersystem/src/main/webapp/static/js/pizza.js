$(window).on("load", function() {

});

$("#addEditPizza").on("click", function() {
	var pizzaId = $("#pizzaId").val();
	var pizzaname = $("#pizzaname").val();
	var price = $("#price").val();
	var pizzaMenu = {
		pizzaId: pizzaId,
		pizzaName: pizzaname,
		price: price
	}

	var pizzaNameFlag = required(pizzaname, "pizzaname");
	var priceFlag = required(price, "price");

	if (pizzaNameFlag && priceFlag) {
		$("#pizzanameError").html("");
		$("#priceError").html("");
		$.ajax({
			url: uri+"/add/pizza",
			type: 'POST',
			data: JSON.stringify(pizzaMenu),
			contentType: 'application/json',
			success: function(data) {
				$("body").html(data);
				$('.close').click();
			}, error: function(response) {
				$("#pizzanameError").html(response.responseJSON.pizzaName);
				$('#pizzanameError').show();
				$("#priceError").html(response.responseJSON.price);
				$('#priceError').show();
			}
		});
	}
});

function editPizzaData(pizzaId) {
	$("#pizzanameError").html("");
	$("#priceError").html("");
	$.ajax({
		type: "GET",
		url: uri+"/pizza/" + pizzaId,
		success: function(response) {
			$("#pizzaId").val(response.pizzaId);
			$("#pizzaname").val(response.pizzaName);
			$("#price").val(response.price);
		},
		error: function() {
			$("#problemPizza").show();
			$("#problemPizza").delay(8000).fadeOut("slow");
		}
	});
}

function addPizza() {
	$("#pizzaId").val("");
	$("#pizzaname").val("");
	$("#price").val("");
	$("#pizzanameError").html("");
	$("#priceError").html("");
}


function deletePizzaData(pizzaId) {

	$("#confirmDeletePizza").on("click", function() {
		$.ajax({
			url: uri+"/delete/pizza/" + pizzaId,
			type: 'DELETE',
			success: function() {
				location.reload(true);
			},
			error: function() {
				$("#problemPizza").show();
				$("#problemPizza").delay(8000).fadeOut("slow");
			}
		});
	});
}

function addCoupon() {
	$("#couponId").val("");
	$("#couponcode").val("");
	$("#discount").val("");
}