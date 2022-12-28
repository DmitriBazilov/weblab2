function submitForm() {
    let x = $('[name="input-x"]:checked').val();
    let y = $('#y').val();
    let r = $('[name="input-r"]:checked').val();
    const offset = new Date().getTimezoneOffset() / 60;
    
    let validationFlag = validateForm(x, y, r);
    if (validationFlag) {
        $.ajax({
            type: "GET",
            url: "ControllerServlet",
            data: {
                "x": parseFloat(x), 
                "y": parseFloat(y),
                "r": parseFloat(r),
                "offset": offset
            },
            success: function (response) {
                console.log(response);
                addInTable(response);
                color = response.hit ? "#569E76" : "#B15E79";
                showDot(createDot(response.x, response.y, response.r, color));
            },
            error: function (response) {
                printError(response);
            }
        });
    }
}

function sendClick(x, y, r) {
    const offset = new Date().getTimezoneOffset() / 60;
    
    let validationFlag = validateForm(x, y, r);
    if (validationFlag) {
        $.ajax({
            type: "GET",
            url: "ControllerServlet",
            data: {
                "x": parseFloat(x), 
                "y": parseFloat(y),
                "r": parseFloat(r),
                "offset": offset
            },
            success: function (response) {
                console.log(response);
                addInTable(response);
                color = response.hit ? "#569E76" : "#B15E79";
                showDot(createDot(response.x, response.y, response.r, color));
            },
            error: function (response) {
                printError(response);
            }
        });
    }
}

function validateForm(x, y, r) {
    let flag = false;
    if (isNaN(x)) 
        printError("Пожалуйста введите корректный X");
    else if (isNaN(y))
        printError("Пожалуйста введите корректный Y");
    else if (isNaN(r))
        printError("Пожалуйста введите корректный R");
    else if (isNaN(parseFloat(x))) 
        printError("Неправильный X.");
    else if (isNaN(parseFloat(y)) || !(parseFloat(y) >= -3 && parseFloat(y) <= 5))
        printError("Неправильный Y. Введите число [-3, 5].");
    else if (isNaN(parseFloat(r)))
        printError("Неправильный R.");
    else flag = true;

    return flag;
}

function addInTable(row) {
    let newRow = '<tr>'
        + '<td>' + row.x + '</td>'
        + '<td>' + row.y + '</td>'
        + '<td>' + row.r + '</td>'
        + '<td>' + row.clientDate + '</td>'
        + '<td>' + row.scriptWorkingTime + '</td>'
        + '<td>' + (row.hit ? "Попал" : "Промах") + '</td>'
        + '</tr>';
    let table = $('.table tbody');
    let prev = table.html();
    table.html(newRow + prev);
}
