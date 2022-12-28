circles = {};

function init() {

    $('[name="input-r"]').each(function() {
        circles[$(this).val()] = [];
    })

    document.getElementById('svg-graph').addEventListener('click', function (event) {
        r = $('[name="input-r"]:checked');
        if (r.length != 1) {
            printError("Выберите радиус"); 
            return;
        }
        
        let rValue = r.val();

        let target = this.getBoundingClientRect();
        let x = event.clientX - target.left;
        let y = event.clientY - target.top;
        let xValue = ((x - 150) / (100 / rValue)).toFixed(2);
        let yValue = ((y - 150) / (-100 / rValue)).toFixed(2);

        //console.log(xValue, yValue);

        sendClick(xValue, yValue, rValue);
    });

    $("[name='input-x']").change(function() {
        $("[name='input-x']").not(this).prop('checked', false);
    });

    $("[name='input-r']").change(function() {
        $("[name='input-r']").not(this).prop('checked', false);
        console.log($(this).val());
        deleteAllDots();
        recreateDots($(this).val());
    });

    getTable();
}

function getTable() {
    $.ajax({
        type: "GET",
        url: "ControllerServlet",
        data: {
            getTable: "true"
        },
        success: function (response) {
            response.forEach(function (point) {
                console.log(point);
                addInTable(point);
                createDot(point.x, point.y, point.r, point.hit ? "#569E76" : "#B15E79");
            });
        },
        error: function (response) {
            printError(response);
        }
    });
}

function cleanTable() {
    $.ajax({
        type: "POST",
        url: "ControllerServlet",
        data: {
            clean: "true"
        },
        success: function (response) {
            let table = $('.table tbody');
            table.html("");
            $('[name="input-r"]').each(function () {
                circles[$(this).val()].forEach(dot => {
                        dot.remove();
                });
                circles[$(this).val()] = [];
            });
        },
        error: function (response) {
            printError(response);
        }
    })
}

function printError(message) {
    let alrt = $('#alert');
    alrt.text(message);
}

