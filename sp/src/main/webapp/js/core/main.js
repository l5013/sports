$(function() {
	$(".listTable").trColorChange();
	$("a.delete").confirmOperator();
	$(".datepicker").datepicker({
		showAnim : "drop",
		changeMonth : true,
		changeYear : true
	});
	$(".spinner").spinner();
});