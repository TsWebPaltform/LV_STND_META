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

const lv_action = {
    "MENU_OPEN": { //화면 오픈: ARCPLAN.arcclient.acSendInputEvent("COM_CMN1", 3, "화면명");
        "action": "INPUT",
        "pgm": "COM_CMN1",
        "obj": 3
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
    , "FAVORIT": {//즐겨찾기: ARCPLAN.acSendClickEvent("COM_CMN1", 64, 1, 1);
        "action": "CLICK",
        "pgm": "COM_CMN1",
        "obj": 64
    }
    , "CARD_FAV": {//카드 즐겨찾기: ARCPLAN.acSendClickEvent("COM_CMN1", 65, 1, 1);
        "action": "CLICK",
        "pgm": "COM_CMN1",
        "obj": 65
    }
    , "CARD_ROT": {//카드 로테이션: ARCPLAN.arcclient.acSendInputEvent("COM_PORTLET", 65, "오픈할 페이지");
        "action": "INPUT",
        "pgm": "COM_PORTLET",
        "obj": 65
    }
}

function setLongviewEvent(keyword, value) {
    var action = lv_action[keyword];

    if (action != null) {
        try {
            if (action.action == "CLICK") {
                ARCPLAN.acSendClickEvent(action.pgm, action.obj, 1, 1);
            }
            else if (action.action == "INPUT") {
                if (value != null) {
                    ARCPLAN.arcclient.acSendInputEvent(action.pgm, action.obj, value);
                }
            }
        }
        catch (e) {
            alert(" keyword : " + keyword + " / value : " + value);
        }
    }

}