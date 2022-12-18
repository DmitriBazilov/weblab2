function setTimezone() {
    $('[id="formSend:timezone"]').val(new Date().getTimezoneOffset() / (-60));
    console.log(new Date().getTimezoneOffset());
}

function check_r() {
    let choosed_r = $('[name="formSend:r_value"]:checked').val();
    if (validate_r(choosed_r))
        return false;
    return true;
}
