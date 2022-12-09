function initialize_table(board, points) {
    $('[name="formSend:x_value"]').val($('#x_slider').val());

    let tBody = document.querySelector('#result_table > tbody');
    console.log(tBody);
    for (let row of tBody.rows) {
        var x = parseFloat(row.cells.item(0).innerText.trim());
        var y = parseFloat(row.cells.item(1).innerText.trim());
        var r = parseInt(row.cells.item(2).innerText.trim());
        console.log(r);
        var hit = row.cells.item(5).innerText.trim() === 'true';
        if (r)
            points[r].push(createPoint(board, x, y, hit));
    }
}

function clean_table() {
    $.ajax({
        type: "POST",
        url: "ControllerServlet",
        data: {"clean": 'true'},
        success: function (response) {
            let tBody = document.querySelector('#table_body');
            tBody.innerHTML = '';
        },
        error: function (response) {
			var alrt = document.getElementById('alert');
			alrt.innerHTML = textStatus + errorThrown;
        }
    });
}

function addInTable(data) {
    $('#table_body').append(data);
}

function convertToHtmlTable(data) {
    return "<tr>" +
        "<td>" + data.x + "</td>" +
        "<td>" + data.y + "</td>" +
        "<td>" + data.r + "</td>" +
        "<td>" + data.clientDate + "</td>" +
        "<td>" + data.scriptWorkingTime + " ms</td>" +
        "<td>" + data.hit + "</td>" +
        "</tr>";
}
