$(window).on("load", function() {

});

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