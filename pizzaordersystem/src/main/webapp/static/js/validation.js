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