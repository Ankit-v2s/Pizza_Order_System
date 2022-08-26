$(window).on("load", function() {

});

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
			url: uri+"/employee/"+employeeId,
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
