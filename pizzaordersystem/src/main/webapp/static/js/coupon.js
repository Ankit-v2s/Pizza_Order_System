$(window).on("load", function() {

});

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