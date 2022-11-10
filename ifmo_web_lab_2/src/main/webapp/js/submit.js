function sendForm(board, points, x, y, r) {

	$.ajax({
		url: 'ControllerServlet',
		type: 'GET',
		data: {
			'x': x,
			'y': y,
			'r': r,
			'offset': new Date().getTimezoneOffset()
		},
		success: function(data) {
			console.log(data);
			let x = data.x, y = data.y, r = data.r, hit = data.hit;
			let point = createPoint(board, x, y, hit);
			point.showElement();
			points[r].push(point);
			addInTable(convertToHtmlTable(data));
		},
		error: function(jqXHR, textStatus, errorThrown) {
			var alrt = document.getElementById('alert');
			alrt.innerHTML = textStatus + errorThrown; 
		}
	});
}

function check_r() {
	var all_r = document.querySelectorAll('input[type="checkbox"][name="R"]:checked');
    all_r.forEach(function (r) {
        if (validate_r(r.value)) return false;
    });
    return true;
}
