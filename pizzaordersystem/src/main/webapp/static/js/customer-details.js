$(window).on("load", function() {

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
			url: uri+"/customer/"+customerId,
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