$(window).on("load", function() {

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