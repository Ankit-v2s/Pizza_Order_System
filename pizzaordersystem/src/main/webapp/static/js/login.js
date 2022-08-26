$(window).on("load", function() {

});
var flag = true;
const uri = "http://192.168.20.184:8080/pizzaordersystem";

function required(field, str) {
	if (field === "" || field == null) {
		$("#" + str + "Error").html(str + " is Required");
		$("#" + str + "Error").show();
		flag = false;
	} else {
		$("#" + str + "Error").hide();
		flag = true;
	}
	return flag;
}

function phoneLength(field) {
	if (field.length < 10) {
		$("#phoneError").html("Phone Number should be of minimum 10 digits");
		$("#phoneError").show();
		flag = false;
	} else {
		$("#phoneError").hide();
		flag = true;
	}
	return flag;
}

function phoneFormat(field) {
	var phoneRegex = new RegExp('^(0|[1-9][0-9]*)$');
	if (!phoneRegex.test(field)) {
		$("#phoneError").html("Phone Number should only have numbers");
		$("#phoneError").show();
		flag = false;
	} else {
		$("#phoneError").hide();
		flag = true;
	}
	return flag;
}

function emailFormat(field) {
	var emailRegex = new RegExp('^[a-z]+[a-z0-9.+]+@[A-Za-z]+[.]{1}[A-Za-z]{2,}$');
	if (!emailRegex.test(field)) {
		$("#emailError").html("Email should be in proper format");
		$("#emailError").show();
		flag = false;
	} else {
		$("#phoneError").hide();
		flag = true;
	}
	return flag;
}

function onlyAlphabetsAndSpaces(field) {
	var alphabetRegex = new RegExp('^[a-zA-Z ]*$');
	if (!alphabetRegex.test(field)) {
		$("#nameError").html("Name should be only contain alphabets");
		$("#nameError").show();
		flag = false;
	} else {
		$("#nameError").hide();
		flag = true;
	}
	return flag;
}

$("#login").on("click", function() {
	var username = $("#userName").val();
	var password = $("#password").val();
	var loginCredentials = {
		userName: username,
		password: password
	}

	var usernameFlag = required(username, "username");
	var passwordFlag = required(password, "password");

	if (usernameFlag && passwordFlag) {
		$('#usernameError').html("");
		$('#passwordError').html("");
		$.ajax({
			url: uri+"/login",
			type: 'POST',
			data: JSON.stringify(loginCredentials),
			contentType: 'application/json',
			success: function(response) {
				window.location.href = "/pizzaordersystem/" + response;
				$('#credentialError').hide();
			},
			error: function(response) {
				$("#credentialError").html(response.responseJSON.message);
				$('#credentialError').show();
				$("#usernameError").html(response.responseJSON.userName);
				$('#usernameError').show();
				$("#passwordError").html(response.responseJSON.password);
				$('#passwordError').show();
			}
		});
	}
});

function getCityDetails() {
	var city = $("#city").val();
	$.ajax({
		url: uri+"/city/" + city,
		type: 'GET',
		contentType: 'application/json',
		success: function(response) {
			$("#state").val(response.stateName);
			$("#country").val(response.countryName);
		}
	});
}

$("#addCustomer").on("click", function() {
	var name = $("#name").val();
	var address1 = $("#addressLine1").val();
	var address2 = $("#addressLine2").val();
	var city = $("#city").val();
	var state = $("#state").val();
	var country = $("#country").val();
	var email = $("#email").val();
	var gender = $("#gender").val();
	var phoneNumber = $("#phone").val();
	var username = $("#username").val();
	var password = $("#password").val();
	var register = {
		name: name,
		address1: address1,
		address2: address2,
		city: city,
		state: state,
		country: country,
		email: email,
		gender: gender,
		phoneNumber: phoneNumber,
		userName: username,
		password: password
	}

	var nameFLag = required(name, "name");
	if (nameFLag) {
		var nameFormatFlag = onlyAlphabetsAndSpaces(name);
	}
	var address1FLag = required(address1, "address1");
	var address2FLag = required(address2, "address2");
	var cityFLag = required(city, "city");
	var stateFLag = required(state, "state");
	var countryFLag = required(country, "country");
	var emailFLag = required(email, "email");
	if (emailFLag) {
		var emailFormatFlag = emailFormat(email);
	}
	var phoneNumberFLag = required(phoneNumber, "phone");
	if (phoneNumberFLag) {
		var phoneFormatFlag = phoneFormat(phoneNumber);
		if (phoneFormatFlag) {
			var phoneLengthFlag = phoneLength(phoneNumber);
		}
	}
	var usernameFLag = required(username, "username");
	var passwordFLag = required(password, "password");

	if (nameFLag && address1FLag && address2FLag && cityFLag && stateFLag && countryFLag && emailFLag && phoneNumberFLag
		&& usernameFLag && passwordFLag && phoneLengthFlag && emailFormatFlag && nameFormatFlag && phoneFormatFlag) {
		$("#nameError").html("");
		$("#address1Error").html("");
		$("#address2Error").html("");
		$("#cityError").html("");
		$("#stateError").html("");
		$("#countryError").html("");
		$("#emailError").html("");
		$("#phoneError").html("");
		$("#usernameError").html("");
		$("#passwordError").html("");
		$.ajax({
			url: uri+"/add/customer",
			type: 'POST',
			data: JSON.stringify(register),
			contentType: 'application/json',
			success: function() {
				window.location.href = "/pizzaordersystem/";
			},
			error: function(response) {
				$("#nameError").html(response.responseJSON.name);
				$('#nameError').show();
				$("#address1Error").html(response.responseJSON.address1);
				$('#address1Error').show();
				$("#address2Error").html(response.responseJSON.address2);
				$('#address2Error').show();
				$("#cityError").html(response.responseJSON.city);
				$('#cityError').show();
				$("#stateError").html(response.responseJSON.state);
				$('#stateError').show();
				$("#countryError").html(response.responseJSON.country);
				$('#countryError').show();
				$("#emailError").html(response.responseJSON.email);
				$('#emailError').show();
				$("#phoneError").html(response.responseJSON.phoneNumber);
				$('#phoneError').show();
				$("#usernameError").html(response.responseJSON.userName);
				$('#usernameError').show();
				$("#passwordError").html(response.responseJSON.password);
				$('#passwordError').show();
			}
		});
	}

});

$("#backLogin").on("click", function() {
	window.location.href = "/pizzaordersystem/";
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

function editCouponData(couponId) {
	$.ajax({
		type: "GET",
		url: uri+"/coupon/" + couponId,
		success: function(response) {
			$("#couponId").val(response.couponId);
			$("#couponcode").val(response.couponCode);
			$("#discount").val(response.discount);
		},
		error: function() {
			$("#problemCoupon").show();
			$("#problemCoupon").delay(8000).fadeOut("slow");
		}
	});
}

$("#addEditCoupon").on("click", function() {
	var couponId = $("#couponId").val();
	var couponcode = $("#couponcode").val();
	var discount = $("#discount").val();
	var coupon = {
		couponId: couponId,
		couponCode: couponcode,
		discount: discount
	}

	var couponcodeFlag = required(couponcode, "couponcode");
	var discountFlag = required(discount, "discount");

	if (couponcodeFlag && discountFlag) {
		$("#couponcodeError").html("");
		$("#discountError").html("");
		$.ajax({
			url: uri+"/add/coupon",
			type: 'POST',
			data: JSON.stringify(coupon),
			contentType: 'application/json',
			success: function() {
				location.reload(true);
			}, error: function(response) {
				$("#couponcodeError").html(response.responseJSON.couponCode);
				$("#couponcodeError").show()
				$("#discountError").html(response.responseJSON.discount);
				$("#couponcodeError").show();
			}
		});
	}
});


function deleteCouponData(couponId) {

	$("#confirmDeleteCoupon").on("click", function() {
		$.ajax({
			url: uri+"/delete/coupon/" + couponId,
			type: 'DELETE',
			success: function() {
				location.reload(true);
			},
			error: function() {
				$("#problemCoupon").show();
				$("#problemCoupon").delay(8000).fadeOut("slow");
			}
		});
	});
}

function filterOrderByStatus() {
	var statusType = $("#orderstatus").val();
	var url;
	if (statusType == "") {
		url = uri+"/orders";
	}
	else {
		url = uri+"/order/" + statusType;
	}
	$.ajax({
		url: url,
		type: 'GET',
		success: function(data) {
			$("body").html(data);
		},
		error: function() {
			console.log("error");
		}
	});
}

function filterOrderByDate() {
	var dateOfOrder = $("#dateOfOrder").val();
	console.log(dateOfOrder);
	$.ajax({
		url: uri+"/order/date/" + dateOfOrder,
		type: 'GET',
		success: function(data) {
			$("body").html(data);
		},
		error: function() {
			console.log("error");
		}
	});
}

function filterPaymentByMode() {
	var paymentMode = $("#paymentmode").val();
	$.ajax({
		url: uri+"/payment/" + paymentMode,
		type: 'GET',
		success: function(data) {
			$("body").html(data);
		},
		error: function() {
			console.log("error");
		}
	});
}

$("#updateEmployee").on("click", function() {
	var employeeId = $("#employeeId").val();
	var email = $("#email").val();
	var address1 = $("#address1").val();
	var address2 = $("#address2").val();
	var city = $("#city").val();
	var state = $("#state").val();
	var country = $("#country").val();
	var phoneNumber = $("#phoneNumber").val();
	var employeeDetails = {
		employeeId: employeeId,
		email: email,
		addressLine1: address1,
		addressLine2: address2,
		cityName: city,
		stateName: state,
		countryName: country,
		phoneNumber: phoneNumber
	}

	var emailFlag = required(email, "email");
	if (emailFlag) {
		var emailFormatFlag = emailFormat(email);
	}
	var address1Flag = required(address1, "address1");
	var address2Flag = required(address2, "address2");
	var cityFlag = required(city, "city");
	var stateFlag = required(state, "state");
	var countryFlag = required(country, "country");
	var phoneFlag = required(phoneNumber, "phone");
	if (phoneFlag) {
		var phoneFormatFlag = phoneFormat(phoneNumber);
		if (phoneFormatFlag) {
			var phoneLengthFlag = phoneLength(phoneNumber);
		}
	}

	if (emailFlag && address1Flag && address2Flag && cityFlag && stateFlag && countryFlag && phoneFlag && emailFormatFlag && phoneFormatFlag && phoneLengthFlag) {
		$("#address1Error").html("");
		$("#address2Error").html("");
		$("#cityError").html("");
		$("#stateError").html("");
		$("#countryError").html("");
		$("#emailError").html("");
		$("#phoneError").html("");
		$.ajax({
			url: uri+"/employee/update",
			type: 'PUT',
			data: JSON.stringify(employeeDetails),
			contentType: 'application/json',
			success: function() {
				location.reload(true);
				$('.close').click();
			},
			error: function(response) {
				$("#address1Error").html(response.responseJSON.addressLine1);
				$('#address1Error').show();
				$("#address2Error").html(response.responseJSON.addressLine2);
				$('#address2Error').show();
				$("#cityError").html(response.responseJSON.city);
				$('#cityError').show();
				$("#stateError").html(response.responseJSON.state);
				$('#stateError').show();
				$("#countryError").html(response.responseJSON.country);
				$('#countryError').show();
				$("#emailError").html(response.responseJSON.email);
				$('#emailError').show();
				$("#phoneError").html(response.responseJSON.phoneNumber);
				$('#phoneError').show();
			}
		});
	}
});

$("#updateCustomer").on("click", function() {
	var customerId = $("#customerId").val();
	var email = $("#email").val();
	var address1 = $("#address1").val();
	var address2 = $("#address2").val();
	var city = $("#city").val();
	var state = $("#state").val();
	var country = $("#country").val();
	var phoneNumber = $("#phoneNumber").val();
	var customerdetails = {
		customerId: customerId,
		email: email,
		address1: address1,
		address2: address2,
		city: city,
		state: state,
		country: country,
		phoneNumber: phoneNumber
	}

	var emailFlag = required(email, "email");
	if (emailFlag) {
		var emailFormatFlag = emailFormat(email);
	}
	var address1Flag = required(address1, "address1");
	var address2Flag = required(address2, "address2");
	var cityFlag = required(city, "city");
	var stateFlag = required(state, "state");
	var countryFlag = required(country, "country");
	var phoneFlag = required(phoneNumber, "phone");
	if (phoneFlag) {
		var phoneFormatFlag = phoneFormat(phoneNumber);
		if (phoneFormatFlag) {
			var phoneLengthFlag = phoneLength(phoneNumber);
		}
	}

	if (emailFlag && address1Flag && address2Flag && cityFlag && stateFlag && countryFlag && phoneFlag && emailFormatFlag && phoneFormatFlag && phoneLengthFlag) {
		$("#address1Error").html("");
		$("#address2Error").html("");
		$("#cityError").html("");
		$("#stateError").html("");
		$("#countryError").html("");
		$("#emailError").html("");
		$("#phoneError").html("");
		$.ajax({
			url: uri+"/customer/update",
			type: 'PUT',
			data: JSON.stringify(customerdetails),
			contentType: 'application/json',
			success: function() {
				location.reload(true);
				$('.close').click();
			},
			error: function(response) {
				$("#address1Error").html(response.responseJSON.address1);
				$('#address1Error').show();
				$("#address2Error").html(response.responseJSON.address2);
				$('#address2Error').show();
				$("#cityError").html(response.responseJSON.city);
				$('#cityError').show();
				$("#stateError").html(response.responseJSON.state);
				$('#stateError').show();
				$("#countryError").html(response.responseJSON.country);
				$('#countryError').show();
				$("#emailError").html(response.responseJSON.email);
				$('#emailError').show();
				$("#phoneError").html(response.responseJSON.phoneNumber);
				$('#phoneError').show();
			}
		});
	}
});

$("#addfeedback").on("click", function() {
	var feedbackStatus = $("#feedbackStatus").val();
	var comments = $("#comments").val();
	var feedback = {
		feedbackStatusType: feedbackStatus,
		comments: comments,
	}

	var feedbackStatusFlag = required(feedbackStatus, "feedbackStatus");
	var commentsFlag = required(comments, "comments");

	if (feedbackStatusFlag && commentsFlag) {
		$("#feedbackStatusError").html("");
		$("#commentsError").html("");
		$.ajax({
			url: uri+"/add/feedback",
			type: 'POST',
			data: JSON.stringify(feedback),
			contentType: 'application/json',
			success: function() {
				$("#addSuccess").show();
				$("#addSuccess").delay(8000).fadeOut("slow");
				$("#feedbackStatus").val("");
				$("#comments").val("");
			},
			error: function(response) {
				$("#feedbackStatusError").html(response.responseJSON.feedbackStatusType);
				$('#feedbackStatusError').show();
				$("#commentsError").html(response.responseJSON.comments);
				$('#commentsError').show();
			}
		});
	}
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

