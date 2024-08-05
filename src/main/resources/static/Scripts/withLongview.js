//화면 오픈: ARCPLAN.arcclient.acSendInputEvent("COM_CMN1", 3, "화면명");
//HOME: ARCPLAN.acSendClickEvent("COM_CMN1", 57, 1, 1);
//BACK: ARCPLAN.acSendClickEvent("COM_CMN1", 58, 1, 1);
//EXCEL: ARCPLAN.acSendClickEvent("COM_CMN1", 59, 1, 1);
//PPT: ARCPLAN.acSendClickEvent("COM_CMN1", 60, 1, 1);
//PDF: ARCPLAN.acSendClickEvent("COM_CMN1", 61, 1, 1);
//COMMENT(게시판) : ARCPLAN.acSendClickEvent("COM_CMN1", 62, 1, 1);
//INFO(도움말) : ARCPLAN.acSendClickEvent("COM_CMN1", 63, 1, 1);
//즐겨찾기: ARCPLAN.acSendClickEvent("COM_CMN1", 64, 1, 1);
//카드 즐겨찾기: ARCPLAN.acSendClickEvent("COM_CMN1", 65, 1, 1);
//카드 로테이션: ARCPLAN.arcclient.acSendInputEvent("COM_PORTLET", 65, "오픈할 페이지");
var top_ico =
    [
        {
            "id": "top-icon-back",
            "lv_action": "BACK"
        },
        {
            "id": "top-icon-bookmark",
            "lv_action": "BOOKMARK"
        },
        {
            "id": "top-icon-info",
            "lv_action": "INFO"
        },
        {
            "id": "top-icon-pwch",
            "lv_action": "PWCH"
        },
        {
            "id": "top-icon-logout",
            "lv_action": ""
        }
    ]

const lv_action = {
    "MENU_OPEN": { //화면 오픈: ARCPLAN.arcclient.acSendInputEvent("COM_CMN1", 3, "화면명");
        "action": "INPUT",
        "pgm": "COM_CMN1",
        "obj": 2
    },
    "HOME": {//HOME: ARCPLAN.acSendClickEvent("COM_CMN1", 57, 1, 1);
        "action": "CLICK",
        "pgm": "COM_CMN1",
        "obj": 57
    },
    "BACK": {//BACK: ARCPLAN.acSendClickEvent("COM_CMN1", 58, 1, 1);
        "action": "CLICK",
        "pgm": "COM_CMN1",
        "obj": 58
    }
    , "EXCEL": {//EXCEL: ARCPLAN.acSendClickEvent("COM_CMN1", 59, 1, 1);
        "action": "CLICK",
        "pgm": "COM_CMN1",
        "obj": 59
    }
    , "PPT": {//PPT: ARCPLAN.acSendClickEvent("COM_CMN1", 60, 1, 1);
        "action": "CLICK",
        "pgm": "COM_CMN1",
        "obj": 60
    }
    , "PDF": {//PDF: ARCPLAN.acSendClickEvent("COM_CMN1", 61, 1, 1);
        "action": "CLICK",
        "pgm": "COM_CMN1",
        "obj": 61
    }
    , "COMMENT": {//COMMENT(게시판) : ARCPLAN.acSendClickEvent("COM_CMN1", 62, 1, 1);
        "action": "CLICK",
        "pgm": "COM_CMN1",
        "obj": 62
    }
    , "INFO": {//INFO(도움말) : ARCPLAN.acSendClickEvent("COM_CMN1", 63, 1, 1);
        "action": "CLICK",
        "pgm": "COM_CMN1",
        "obj": 63
    }
    , "BOOKMARK": {//즐겨찾기: ARCPLAN.acSendClickEvent("COM_CMN1", 64, 1, 1);
        "action": "CLICK",
        "pgm": "COM_CMN1",
        "obj": 64
    }
    , "CARD_BOOKMARK": {//카드 즐겨찾기: ARCPLAN.acSendClickEvent("COM_CMN1", 65, 1, 1);
        "action": "CLICK",
        "pgm": "COM_CMN1",
        "obj": 65
    }
    , "CARD_ROT": {//카드 로테이션: ARCPLAN.arcclient.acSendInputEvent("COM_PORTLET", 65, "오픈할 페이지");
        "action": "INPUT",
        "pgm": "COM_PORTLET",
        "obj": 65
    }
    , "PWCH": {//INFO(도움말) : ARCPLAN.acSendClickEvent("COM_CMN1", 63, 1, 1);
        "action": "WEB",
    }
}

function setLongviewEvent(keyword, value) {
    var action = lv_action[keyword];

    if (action != null) {

        if (action.action == "CLICK") {

            $("#lv_main")[0].contentWindow.arcClickEvent(action.pgm, action.obj);

        }
        else if (action.action == "INPUT") {
            if (value != null) {
                $("#lv_main")[0].contentWindow.arcInputEvent(action.pgm, action.obj, value);
            }
        }else if (action.action == "WEB") {

            $("#top-icon-pwch").click(function() {
                // 해당 코드는 jQuery를 사용하고 있으므로, jQuery를 로드한 HTML 페이지에서 사용 가능합니다.
                window.location.href = "/change-password";
            });
        }
/*

        var cnt = 0;
        var timer = setInterval(function () {

            try {
                if (action.action == "CLICK") {

                    $("#lv_main")[0].contentWindow.arcClickEvent(action.pgm, action.obj);
                }
                else if (action.action == "INPUT") {
                    if (value != null) {
                        $("#lv_main")[0].contentWindow.arcInputEvent(action.pgm, action.obj, value);
                    }
                }

                clearInterval(timer);
                timer = null;
            }
            catch (e) {
                console.log(" keyword : " + keyword + " / value : " + value);

            }
            if (cnt++ > 50) {
                console.log(" timeover : " + keyword + " / value : " + value);
                clearInterval(timer);
                timer = null;
            }

        }, 100);
        */
    }

}