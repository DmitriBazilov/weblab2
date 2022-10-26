function initialize_table(board, points) {
    // $.ajax({
    //     type: "GET",
    //     url: "ControllerServlet",
    //     data: {'getTable': 'true'},
    //     success: function (response) {
    //         Array.from(response).forEach(function (element) {
    //             let x = element.x, y = element.y, r = element.r, hit = element.hit;
    //             points[r].push(createPoint(board, x, y, hit));
    //             addInTable(convertToHtmlTable(element));
    //         });
    //     },
    //     error: function (response) {
    //         alert(response);
    //     }
    // });

    let tBody = document.getElementById('table_body');
    for (let row of tBody.rows) {
        var x = parseFloat(row.cells.item(0).innerText);
        var y = parseFloat(row.cells.item(1).innerText);
        var r = parseInt(row.cells.item(2).innerText);
        var hit = row.cells.item(5).innerText === 'true';
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
            alert(response);
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
