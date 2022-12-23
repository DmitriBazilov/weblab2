function validate_values(x, y, r) {

	result = "";
	result += validate_x(x)	+ validate_y(y) + validate_r(r);
	return result;
}

function validate_x(x) {
	if (x.length === 0) {
		return "Choose X please. <br>";
	}
    // console.log(x);
    let flag = false;
    x.forEach(function (element) {
        console.log(element.value);
        if (!parseFloat(element.value) && element.value !== "0") flag = true;
    });
    if (flag) return "Choose correct X. <br>";
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
	if (parseFloat(r) && r >= 0)
		return "";
	return "Please, choose correct R.";
}

function checkY() {
	let y = $('[id="formSend:y_value"]');
	y.val(y.val().replace(',', '.'));
}

