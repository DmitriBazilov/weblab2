serverPoints = [];
board = null
pointsByRadius = {};

$(document).ready(function () {
    // let colors = ['#e196fa', '#3ad6bc', '#f0f022', '#0202ab', '#d15102'];
    board = JXG.JSXGraph.initBoard('jxgbox', {boundingbox: [-6, 6, 6, -6], axis: true, showCopyright: false});
    let figuresByRadius = {};
    var r_selector = $('input[name="formSend:r_value"]');
    console.log(r_selector);
    r_selector.each(function () {
        let value = this.value;
        console.log(value);
        pointsByRadius[value] = [];
        figuresByRadius[value] = [];
    });
    initialize_table(board, pointsByRadius);
    hideAllPoints(pointsByRadius);
    $('input[type="radio"]').on('click change', function(e){
        clearAllFigures(board, figuresByRadius);
        r_input = e.target;
        hideAllPoints(pointsByRadius);  //TODO realize function
        updatePoints();
        //TODO refactor change handler
        if (parseFloat(r_input.value) > 0 && r_input.checked) {
            let newRadius = r_input.value;
            console.log(newRadius);
            let layer = 6;      // 7 - $('input[name="R"]:checked').length;
            let color = '#e196fa';      // colors[$('input[name="R"]:checked').length - 1];
            let rectangle = createRectangle(board, newRadius, color, layer);
            let triangle = createTriangle(board, newRadius, color, layer);
            let circle = createCircle(board, newRadius, color, layer);
            figuresByRadius[newRadius] = [rectangle, triangle, circle];
            if (!pointsByRadius[newRadius]) pointsByRadius[newRadius] = []; 
            drawPointsByR(newRadius);
            //renderFiguresByRadius(figuresByRadius, colors);
        } else {
            console.log("DAUN");
            var alrt = document.getElementById('alert');
            alrt.innerHTML = "<strong>Incorrect R</strong>";
        }
    });
    //
    // $('#btnSend').click(function (event) {
    //     var x = document.querySelectorAll('input[type="checkbox"][name="X"]:checked');
    //     var y = document.getElementById('y_text');
    //     var all_r = document.querySelectorAll('input[type="checkbox"][name="R"]:checked');
    //     var r = -1;
    //     for (const checkbox of all_r) {
    //         if (parseFloat(checkbox.value)) {
    //             r = Math.max(r, checkbox.value);
    //         } else {
    //             r = "error";
    //             break;
    //         }
    //     }
    //     var result = validate_values(x, y, r);
    //     var alrt = document.getElementById('alert');
    //     if (result !== "") {
    //         alrt.innerHTML = "<strong>" + result + "</strong>";
    //     } else {
    //         x.forEach(function (xNumber) {
    //             sendForm(board, pointsByRadius, xNumber.value, y.value.replace(",", "."), r);
    //         });
    //     }
    // });
    //
    // $('#btnReset').click(function (event) {
    //     r_selector.each(function () {
    //         clearFigures(board, figuresByRadius[this.value])
    //         figuresByRadius[this.value] = [];
    //         let idxRadius = this.value;
    //         pointsByRadius[idxRadius].forEach(function (point) {
    //             point.hideElement();
    //         });
    //         renderFiguresByRadius(figuresByRadius, colors);
    //     });
    // });
    //
    // $('#btnClean').click(function (event) {
    //     r_selector.each(function () {
    //         let idxRadius = $(this).val();
    //         board.removeObject(pointsByRadius[idxRadius]);
    //         pointsByRadius[idxRadius] = [];
    //     });
    //     clean_table();
    // });

    board.on("down", function (event) {
        if (event.button === 2 || event.target.className === 'JXG_navigation_button') {
            return;
        }
        if (check_r()) {
            let coords = board.getUsrCoordsOfMouse(event);
            let choosed_r = document.querySelector('[name="formSend:r_value"]:checked');
            $('[id="formSend:x_value"]').val(coords[0].toFixed(2));
            $('[id="formSend:y_value"]').val(coords[1].toFixed(2));
            $('[id="formSend:r_value"]').val(choosed_r.value);
            $('#formSend input[type="submit"]').click();
        } else {
            var alrt = document.getElementById('alert');
            alrt.innerHTML = "<strong>You should choose correct R</strong>"
        }
        event.preventDefault();
    });
});

function clearAllFigures(board, figures) {

    let r_selector = document.querySelectorAll('input[name="formSend:r_value"]');
    let r_array = Array.from(r_selector);

    r_array.forEach(function (radius, index) {
        if (parseFloat(radius.value))
            clearFigures(board, figures[radius.value]);
        figures[radius.value] = [];
    });
}

function clearFigures(board, figures) {
    console.log(figures);
    for (const object of figures) {
        let points = object.ancestors;
        for (const point in points) board.removeObject(point);
        board.removeObject(object);
    }
    figures = [];
}

function hideAllPoints(radiusPoints) {
    for (var points in radiusPoints) {
        radiusPoints[points].forEach(point => {
            console.log(point);
            point.hideElement();
        });    
    }
}

function clearPoints() {
    hideAllPoints(pointsByRadius);
    for (var idx in pointsByRadius) {
        pointsByRadius[idx] = [];
    }
}

function drawPointsByR(radius) {
    if ($('[name="formSend:r_value"]:checked').length == 0) return; 
    console.log(radius);
    console.log(serverPoints);
    console.log(pointsByRadius);
    for (point in serverPoints) {
        if (serverPoints[point].r == radius) {
            p = (createPoint(board, serverPoints[point].x, serverPoints[point].y, serverPoints[point].hit));
            pointsByRadius[radius].push(p);            
        }
    }
}

function createRectangle(board, r, color, figLayer) {
    let rectanglePoint1 = board.create('point', [0, 0], {name: '', fixed: true, visible: false});
    let rectanglePoint2 = board.create('point', [-r, 0], {name: '', fixed: true, visible: false});
    let rectanglePoint3 = board.create('point', [-r, r / 2], {name: '', fixed: true, visible: false});
    let rectanglePoint4 = board.create('point', [0, r / 2], {name: '', fixed: true, visible: false});
    return board.create('polygon', [rectanglePoint1, rectanglePoint2, rectanglePoint3, rectanglePoint4], {
        fillColor: color, fillOpacity: 1, layer: figLayer, highlight: false,
        borders: {strokeColor: '#000', highlight: false, layer: 7}
    });
}

function createTriangle(board, r, color, figLayer) {
    let trianglePoint1 = board.create('point', [0, 0], {name: '', fixed: true, visible: false});
    let trianglePoint2 = board.create('point', [r, 0], {name: '', fixed: true, visible: false});
    let trianglePoint3 = board.create('point', [0, r / 2], {name: '', fixed: true, visible: false});
    return board.create('polygon', [trianglePoint1, trianglePoint2, trianglePoint3], {
        fillColor: color, fillOpacity: 1, layer: figLayer, highlight: false,
        borders: {strokeColor: '#000', highlight: false, layer: 7}
    });
}

function createCircle(board, r, color, figLayer) {
    let circlePoint1 = board.create('point', [r, 0], {name: '', fixed: true, visible: false});
    let circlePoint2 = board.create('point', [0, -r], {name: '', fixed: true, visible: false});
    let centerPoint = board.create('point', [0, 0], {name: '', fixed: true, visible: false});

    return board.create('sector', [centerPoint, circlePoint2, circlePoint1], {
        fillColor: color, fillOpacity: 1, layer: figLayer, highlight: false,
        strokeColor: "#000",
        borders: {strokeColor: '#000', highlight: false, layer: 7}
    });
}


function createPoint(board, x, y, hit) {
    let color = hit ? "#7ce57c" : "#dc4a4a";
    return board.create("point", [x, y], {
        name: '', fixed: true, visible: true, fillColor: color, fillOpacity: 1, strokewidth: 0
    });
}
