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
	let newY = y.value.replace(",", ".");
	if (isNaN(newY) || isNaN(parseFloat(newY))) {
		return "Y must be a number <br>";
	} else if (parseFloat(newY) > 3 || parseFloat(newY) < -5) {
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

