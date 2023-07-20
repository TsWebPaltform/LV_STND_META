function arcClickEvent(pgm, obj) {
    ARCPLAN.acSendClickEvent(pgm, obj, 1, 1);
}
function arcInputEvent(pgm, obj, value) {
    ARCPLAN.arcInputEvent(pgm, obj, value);
}