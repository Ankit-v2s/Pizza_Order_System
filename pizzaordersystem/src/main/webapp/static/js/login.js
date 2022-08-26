$(window).on("load", function() {

});
const uri = "http://192.168.20.184:8080/pizzaordersystem";

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
