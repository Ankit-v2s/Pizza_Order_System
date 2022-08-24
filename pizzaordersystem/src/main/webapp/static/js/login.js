$(window).on("load", function() {

});
var flag = true;

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
			url: "http://192.168.20.184:8080/pizzaordersystem/login",
			type: 'POST',
			data: JSON.stringify(loginCredentials),
			contentType: 'application/json',
			success: function(response) {
				window.location.href = "/pizzaordersystem/" + response;
				$('#credentialError').hide();
			},
			error: function(response) {
				$("#usernameError").html(response.responseJSON.userName);
				$('#usernameError').show();
				$("#passwordError").html(response.responseJSON.password);
				$('#passwordError').show();
				$("#credentialError").html(response.responseJSON.message);
				$('#credentialError').show();
			}
		});
	}
});

function getCityDetails() {
	var city = $("#city").val();
	$.ajax({
		url: "http://192.168.20.184:8080/pizzaordersystem/city/" + city,
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
			url: "http://192.168.20.184:8080/pizzaordersystem/add/customer",
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

	$.ajax({
		url: "http://192.168.20.184:8080/pizzaordersystem/add/pizza",
		type: 'POST',
		data: JSON.stringify(pizzaMenu),
		contentType: 'application/json',
		success: function(data) {
			$("body").html(data);
			console.log("success");
		}
	});
});

function editPizzaData(pizzaId) {
	$.ajax({
		type: "GET",
		url: "http://192.168.20.184:8080/pizzaordersystem/pizza/" + pizzaId,
		success: function(response) {
			$("#pizzaId").val(response.pizzaId);
			$("#pizzaname").val(response.pizzaName);
			$("#price").val(response.price);
		},
		error: function() {
			console.log("Error");
		}
	});
}

function addPizza() {
	$("#pizzaId").val("");
	$("#pizzaname").val("");
	$("#price").val("");
}


function deletePizzaData(pizzaId) {

	$("#confirmDeletePizza").on("click", function() {
		$.ajax({
			url: "http://192.168.20.184:8080/pizzaordersystem/delete/pizza/" + pizzaId,
			type: 'DELETE',
			success: function() {
				location.reload(true);
				console.log("success");
			},
			error: function() {
				console.log("error");
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
		url: "http://192.168.20.184:8080/pizzaordersystem/coupon/" + couponId,
		success: function(response) {
			$("#couponId").val(response.couponId);
			$("#couponcode").val(response.couponCode);
			$("#discount").val(response.discount);
		},
		error: function() {
			console.log("Error");
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
	$.ajax({
		url: "http://192.168.20.184:8080/pizzaordersystem/add/coupon",
		type: 'POST',
		data: JSON.stringify(coupon),
		contentType: 'application/json',
		success: function(data) {
			$("body").html(data);
			console.log("success");
		}
	});
});


function deleteCouponData(couponId) {

	$("#confirmDeleteCoupon").on("click", function() {
		$.ajax({
			url: "http://192.168.20.184:8080/pizzaordersystem/delete/coupon/" + couponId,
			type: 'DELETE',
			success: function() {
				location.reload(true);
				console.log("success");
			},
			error: function() {
				console.log("error");
			}
		});
	});
}

function filterOrderByStatus() {
	var statusType = $("#orderstatus").val();
	var url;
	if (statusType == "") {
		url = "http://192.168.20.184:8080/pizzaordersystem/orders";
	}
	else {
		url = "http://192.168.20.184:8080/pizzaordersystem/order/" + statusType;
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
		url: "http://192.168.20.184:8080/pizzaordersystem/order/date/" + dateOfOrder,
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
		url: "http://192.168.20.184:8080/pizzaordersystem/payment/" + paymentMode,
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
			url: "http://192.168.20.184:8080/pizzaordersystem/employee/update",
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
			url: "http://192.168.20.184:8080/pizzaordersystem/customer/update",
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
			url: "http://192.168.20.184:8080/pizzaordersystem/add/feedback",
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
		$.ajax({
			url: "http://192.168.20.184:8080/pizzaordersystem/add/item",
			type: 'POST',
			data: JSON.stringify(pizza),
			contentType: 'application/json',
			success: function() {
				$("#pizzaOrder").show();
				$("#pizzaName").val("");
				$("#quantity").val("");
				$("#itemAddedSuccess").show();
				$("#itemAddedSuccess").delay(8000).fadeOut("slow");
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
		url: "http://192.168.20.184:8080/pizzaordersystem/order/pizza",
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
	$.ajax({
		url: "http://192.168.20.184:8080/pizzaordersystem/order/pizza/discount",
		type: 'POST',
		data: JSON.stringify(pizzaOrder),
		contentType: 'application/json',
		success: function(response) {
			$("#totalAmount").val(response);
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
			url: "http://192.168.20.184:8080/pizzaordersystem/pay/order",
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

