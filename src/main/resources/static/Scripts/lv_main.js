
function arcClickEvent(pgm, obj) {

    var cnt = 0;
    var timer = setInterval(function () {

        try {
            ARCPLAN.arcclient.acSendClickEvent(pgm, obj, 1, 1);
            clearInterval(timer);
            timer = null;
        }
        catch (e) {
            console.log(" pgm : " + pgm + " / obj : " + obj);

        }
        if (cnt++ > 50) {
            console.log(" timeover : " + pgm + " / obj : " + obj);
            clearInterval(timer);
            timer = null;
        }

    }, 100);


}
function arcInputEvent(pgm, obj, value) {



    var cnt = 0;
    var timer = setInterval(function () {

        try {
            ARCPLAN.arcclient.acSendInputEvent(pgm, obj, value);
            clearInterval(timer);
            timer = null;
        }
        catch (e) {
            console.log(" pgm : " + pgm + " / obj : " + obj);

        }
        if (cnt++ > 50) {
            console.log(" timeover : " + pgm + " / obj : " + obj);
            clearInterval(timer);
            timer = null;
        }

    }, 100);


}