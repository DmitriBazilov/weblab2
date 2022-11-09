$(document).ready(function () {
    let board = JXG.JSXGraph.initBoard('jxgbox', {boundingbox: [-8, 6, 8, -6], axis: true, showCopyright: false});
    let pointsByRadius = {};
    let figures = [];

    var r_selector = $('#r_select option');
    r_selector.each(function () {
        let value = $(this).val();
        pointsByRadius[value] = [];
    });
    initialize_table(board, pointsByRadius);
    $('#r_select').on('change', function () {
        console.log(this.value);
        clearFigures(board, figures);
        let newRadius = this.value;
        let rectangle = createRectangle(board, newRadius);
        let triangle = createTriangle(board, newRadius);
        let circle = createCircle(board, newRadius);
        figures = [rectangle, triangle, circle];

        r_selector.each(function () {
            let idxRadius = $(this).val();
            pointsByRadius[idxRadius].forEach(function (point) {
                if (idxRadius === newRadius) point.showElement(); else point.hideElement();
            });
        });
    });

    $('#btnSend').click(function (event) {
        var x = document.querySelectorAll('input[type="checkbox"]:checked');
        var y = document.getElementById('y_text');
        var r = document.querySelector('option:checked');
        var result = validate_values(x, y, r);
        var alrt = document.getElementById('alert');
        if (result !== "") {
            alrt.innerHTML = "<strong>" + result + "</strong>";
        } else {
            x.forEach(function (xNumber) {
                sendForm(board, pointsByRadius, xNumber.value, y.value.replace(",", "."), r.value);
            });
        }
    });

    $('#btnReset').click(function (event) {
        clearFigures(board, figures);
        r_selector.each(function () {
            let idxRadius = $(this).val();
            pointsByRadius[idxRadius].forEach(function (point) {
                point.hideElement();
            });
        });
    });

    $('#btnClean').click(function (event) {
        r_selector.each(function () {
            let idxRadius = $(this).val();
            board.removeObject(pointsByRadius[idxRadius]);
            pointsByRadius[idxRadius] = [];
        });
        clean_table();
    });

    board.on("down", function (event) {
        if (event.button === 2 || event.target.className === 'JXG_navigation_button') {
            return;
        }
        if (check_r()) {
            let coords = board.getUsrCoordsOfMouse(event);
            sendForm(board, pointsByRadius, coords[0], coords[1], document.querySelector('option:checked').value);
        } else {
            var alrt = document.getElementById('alert');
            alrt.innerHTML = "<strong>You should choose R</strong>"
        }
    });
});

function clearFigures(board, figures) {
    for (const object of figures) {
        let points = object.ancestors;
        for (const point in points) board.removeObject(point);
        board.removeObject(object);
    }
}

function createRectangle(board, r) {
    let rectanglePoint1 = board.create('point', [0, 0], {name: '', fixed: true, visible: false});
    let rectanglePoint2 = board.create('point', [-r, 0], {name: '', fixed: true, visible: false});
    let rectanglePoint3 = board.create('point', [-r, -r / 2], {name: '', fixed: true, visible: false});
    let rectanglePoint4 = board.create('point', [0, -r / 2], {name: '', fixed: true, visible: false});
    return board.create('polygon', [rectanglePoint1, rectanglePoint2, rectanglePoint3, rectanglePoint4], {
        fillColor: '#e196fa', fillOpacity: 1
    });
}

function createTriangle(board, r) {
    let trianglePoint1 = board.create('point', [0, 0], {name: '', fixed: true, visible: false});
    let trianglePoint2 = board.create('point', [-r / 2, 0], {name: '', fixed: true, visible: false});
    let trianglePoint3 = board.create('point', [0, r / 2], {name: '', fixed: true, visible: false});
    return board.create('polygon', [trianglePoint1, trianglePoint2, trianglePoint3], {
        fillColor: '#e196fa', fillOpacity: 1
    });
}

function createCircle(board, r) {
    let circlePoint1 = board.create('point', [r / 2, 0], {name: '', fixed: true, visible: false});
    let circlePoint2 = board.create('point', [0, r / 2], {name: '', fixed: true, visible: false});
    let centerPoint = board.create('point', [0, 0], {name: '', fixed: true, visible: false});

    return board.create('sector', [centerPoint, circlePoint1, circlePoint2], {fillColor: '#e196fa', fillOpacity: 1});
}


function createPoint(board, x, y, hit) {
    let color = hit ? "#7ce57c" : "#dc4a4a";
    return board.create("point", [x, y], {
        name: '', fixed: true, visible: false, fillColor: color, fillOpacity: 1, strokewidth: 0
    });

}
