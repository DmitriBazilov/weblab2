function setTimezone() {
    $('[id="formSend:timezone"]').val(new Date().getTimezoneOffset() / (-60));
    console.log(new Date().getTimezoneOffset());
}

function check_r() {
	var all_r = document.querySelectorAll('input[type="checkbox"][name="R"]:checked');
    all_r.forEach(function (r) {
        if (validate_r(r.value)) return false;
    });
    return true;
}
