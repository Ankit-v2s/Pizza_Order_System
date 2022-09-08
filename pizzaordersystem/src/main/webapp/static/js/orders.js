$(window).on("load", function() {

});

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
