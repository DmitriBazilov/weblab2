$(document).ready(function () {
    let board = JXG.JSXGraph.initBoard('jxgbox', {boundingbox: [-4, 4, 4, -4], axis: true, showCopyright: false});
    let colors = ['#e196fa', '#3ad6bc', '#f0f022', '#0202ab', '#d15102'];
    let pointsByRadius = {};
    let figuresByRadius = {};
    
    var r_selector = $('input[name="R"]');
    console.log(r_selector);
    r_selector.each(function () {
        let value = this.value;
        pointsByRadius[value] = [];
        figuresByRadius[value] = [];
    });
    
    initialize_table(board, pointsByRadius);
    $('input[type="checkbox"][name="R"]').change(function(){
        if (parseFloat(this.value) > 0) {
            if (this.checked) {
                console.log(this.value);
                let newRadius = this.value;
                let layer = 7 - $('input[name="R"]:checked').length;
                let color = colors[$('input[name="R"]:checked').length - 1];
                let rectangle = createRectangle(board, newRadius, color, layer);
                let triangle = createTriangle(board, newRadius, color, layer);
                let circle = createCircle(board, newRadius, color, layer);
                figuresByRadius[newRadius] = [rectangle, triangle, circle];
                if (!pointsByRadius[newRadius]) pointsByRadius[newRadius] = []; 
                pointsByRadius[newRadius].forEach(function (point) {
                    point.showElement();
                });
                renderFiguresByRadius(figuresByRadius, colors);
            } else {
                console.log(this.value);
                let newRadius = this.value;
                clearFigures(board, figuresByRadius[newRadius]);
                figuresByRadius[newRadius] = [];
                pointsByRadius[newRadius].forEach(function (point) {
                    point.hideElement();
                });
                renderFiguresByRadius(figuresByRadius, colors);
            }
        } else {
            var alrt = document.getElementById('alert');
            alrt.innerHTML = "<strong>Incorrect R</strong>";
        }
    });

    $('#btnSend').click(function (event) {
        var x = document.querySelectorAll('input[type="checkbox"][name="X"]:checked');
        var y = document.getElementById('y_text');
        var all_r = document.querySelectorAll('input[type="checkbox"][name="R"]:checked');
        var r = -1;
        for (const checkbox of all_r) {
            if (parseFloat(checkbox.value)) {
                r = Math.max(r, checkbox.value);
            } else {
                r = "error";
                break;
            }
        }
        var result = validate_values(x, y, r);
        var alrt = document.getElementById('alert');
        if (result !== "") {
            alrt.innerHTML = "<strong>" + result + "</strong>";
        } else {
            x.forEach(function (xNumber) {
                sendForm(board, pointsByRadius, xNumber.value, y.value.replace(",", "."), r);
            });
        }
    });

    $('#btnReset').click(function (event) {
        r_selector.each(function () {
            clearFigures(board, figuresByRadius[this.value])
            figuresByRadius[this.value] = [];
            let idxRadius = this.value;
            pointsByRadius[idxRadius].forEach(function (point) {
                point.hideElement();
            });
            renderFiguresByRadius(figuresByRadius, colors);
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
            let all_r = document.querySelectorAll('input[type="checkbox"][name="R"]:checked');
            console.log(all_r);
            all_r.forEach(function (r) {
                sendForm(board, pointsByRadius, coords[0], coords[1], r.value);
            });
        } else {
            var alrt = document.getElementById('alert');
            alrt.innerHTML = "<strong>You should choose correct R</strong>"
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

function createRectangle(board, r, color, figLayer) {
    let rectanglePoint1 = board.create('point', [0, 0], {name: '', fixed: true, visible: false});
    let rectanglePoint2 = board.create('point', [r, 0], {name: '', fixed: true, visible: false});
    let rectanglePoint3 = board.create('point', [r, r / 2], {name: '', fixed: true, visible: false});
    let rectanglePoint4 = board.create('point', [0, r / 2], {name: '', fixed: true, visible: false});
    return board.create('polygon', [rectanglePoint1, rectanglePoint2, rectanglePoint3, rectanglePoint4], {
        fillColor: color, fillOpacity: 1, layer: figLayer
    });
}

function createTriangle(board, r, color, figLayer) {
    let trianglePoint1 = board.create('point', [0, 0], {name: '', fixed: true, visible: false});
    let trianglePoint2 = board.create('point', [-r, 0], {name: '', fixed: true, visible: false});
    let trianglePoint3 = board.create('point', [0, -r], {name: '', fixed: true, visible: false});
    return board.create('polygon', [trianglePoint1, trianglePoint2, trianglePoint3], {
        fillColor: color, fillOpacity: 1, layer: figLayer
    });
}

function createCircle(board, r, color, figLayer) {
    let circlePoint1 = board.create('point', [r / 2, 0], {name: '', fixed: true, visible: false});
    let circlePoint2 = board.create('point', [0, -r / 2], {name: '', fixed: true, visible: false});
    let centerPoint = board.create('point', [0, 0], {name: '', fixed: true, visible: false});

    return board.create('sector', [centerPoint, circlePoint2, circlePoint1], {fillColor: color, fillOpacity: 1, layer: figLayer});
}


function createPoint(board, x, y, hit) {
    let color = hit ? "#7ce57c" : "#dc4a4a";
    return board.create("point", [x, y], {
        name: '', fixed: true, visible: false, fillColor: color, fillOpacity: 1, strokewidth: 0
    });
}

function renderFiguresByRadius(figuresByRadius, colors) {
    console.log(figuresByRadius);
    let r_selector = document.querySelectorAll('input[name="R"]');
    let r_array = Array.from(r_selector);
    let figLayer = 7;

    r_array.forEach(function (radius, index) {
        console.log(figuresByRadius[radius.value]);
        if (!figuresByRadius[radius.value]) figuresByRadius[radius.value] = [];
        figuresByRadius[radius.value].forEach(function (figure) {
            figure.setAttribute({fillColor: colors[index],
                fillOpacity: 1,
                layer: figLayer,
                strokeColor: "#000",
                strokeWidth: "1px",
                strokeOpacity: 1,
                borders: {
                    withLabel: false,
                    strokeColor: "#000",
                    strokeWidth: "1px",
                    strokeOpacity: 1,
                    strokeWidth: 1,
                    highlight: false,
                    layer: 8
                },
                highlight: false
            });
        });
        figLayer--;
    });
}
