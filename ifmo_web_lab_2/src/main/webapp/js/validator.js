function validate_values(x, y, r) {

	result = "";
	result += validate_x(x)	+ validate_y(y) + validate_r(r);
	return result;
}

function validate_x(x) {
	if (x.length === 0) {
		return "Choose X please. <br>";
	}
	return "";
}

function validate_y(y) {
	if (isNaN(y.value) || isNaN(parseFloat(y.value))) {
		return "Y must be a number <br>";
	} else if (parseFloat(y.value) > 3 || parseFloat(y.value) < -5) {
		return "Y must be in [-5; 3] <br>";
	} else {
		return "";
	}
}

function validate_r(r) {
	if (parseInt(r.value))
		return "";
	return "Please, choose R.";
}

