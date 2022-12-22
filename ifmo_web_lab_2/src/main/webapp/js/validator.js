function checkY() {
    field = $('[name="formSend:y_value"]');
    string_field = field.val();
    string_field = string_field.replace(',', '.');
    console.log(string_field);
    field.val(string_field);
}

function validate_r(r) {
	if (parseFloat(r) && r >= 0)
		return "";
	return "Please, choose correct R.";
}

