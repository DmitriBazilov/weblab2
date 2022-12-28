function createDot(x, y, r, color) {
    let svgns = "http://www.w3.org/2000/svg";

    let circle = document.createElementNS(svgns, 'circle');
    circle.setAttributeNS(null, 'cx', 150 + 100 * (x / r));
    circle.setAttributeNS(null, 'cy', 150 - 100 * (y / r));
    circle.setAttributeNS(null, 'r', 4);
    circle.setAttributeNS(null, 'style', `fill: ${color}; stroke: black`);
    circle.classList.add("dot");
    circles[r].push(circle);
    return circle;
}

function showDot(dot) {
    let svg = document.querySelector("svg");
    svg.appendChild(dot);
}

function deleteAllDots() {
    $('.dot').remove();
}

function recreateDots(r) {
    circles[r].forEach(
        circle => showDot(circle)
    );
}
