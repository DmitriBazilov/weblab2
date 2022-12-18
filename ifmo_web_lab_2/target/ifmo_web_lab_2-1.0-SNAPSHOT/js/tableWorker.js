function initialize_table(board, points) {
    $('[name="formSend:x_value"]').val($('#x_slider').val());

    let tBody = document.querySelector('#result_table > tbody');
    console.log(tBody);
    for (let row of tBody.rows) {
        let x = parseFloat(row.cells.item(0).innerText.trim());
        let y = parseFloat(row.cells.item(1).innerText.trim());
        let r = parseInt(row.cells.item(2).innerText.trim());
        console.log(r);
        let hit = row.cells.item(5).innerText.trim() === 'true';
        if (r)
            points[r].push(createPoint(board, x, y, hit));
    }
}

