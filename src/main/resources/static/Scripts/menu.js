function setLeftMenuSize() {

    var menu_top = $("#" + OPTION.menu.top.id)
    var menu_top_height = $(menu_top).outerHeight();
    var real_w_height = window.innerHeight / OPTION.view.scale;


    $("#" + OPTION.menu.left.id).css("height", real_w_height - menu_top_height);
    $("#lv_main").css("height", real_w_height - menu_top_height);
    $("#overlay").css("height", real_w_height - menu_top_height);

}

$(window).resize(function () {
    //setLeftMenuSize();
})


function parseMenuData(menu) {

    var menuObjs = [];

    var lv1_cds = distinctItem(menu, "LVL_1_CD");
    for (var i = 0; i < lv1_cds.length; i++) {
        var lv1_objs = menu.filter(menu => menu["LVL_1_CD"] === lv1_cds[i]);

        var menuObj = {
            "LVL_1_CD": lv1_objs[0]["LVL_1_CD"]
            , "LVL_1_NM": lv1_objs[0]["LVL_1_NM"]
            , "LVL_CD": lv1_objs[0]["LVL_CD"]
        };

        menuObjs.push(menuObj);

        if (lv1_objs.length > 0 && lv1_objs[0]["LVL_2_CD"].length > 0) {
            
            menuObj["SUB"] = [];

            var lv2_cds = distinctItem(lv1_objs, "LVL_2_CD");

            for (var i2 = 0; i2 < lv2_cds.length; i2++) {
                var lv2_objs = lv1_objs.filter(lv1_objs => lv1_objs["LVL_2_CD"] === lv2_cds[i2]);
                if (lv2_objs.length > 0) {
                    var menuObj2 = {
                        "LVL_2_CD": lv2_objs[0]["LVL_2_CD"]
                        , "LVL_2_NM": lv2_objs[0]["LVL_2_NM"]
                        
                    };

                    menuObj.SUB.push(menuObj2);

                    if (lv2_objs.length > 0 && lv2_objs[0]["LVL_3_CD"].length > 0) {

                        menuObj2["SUB"] = [];

                        var lv3_cds = distinctItem(lv2_objs, "LVL_3_CD");

                        for (var i3 = 0; i3 < lv3_cds.length; i3++) {
                            var lv3_objs = lv2_objs.filter(lv2_objs => lv2_objs["LVL_3_CD"] === lv3_cds[i3]);
                            if (lv3_objs.length > 0) {
                                var menuObj3 = {
                                    "LVL_3_CD": lv3_objs[0]["LVL_3_CD"]
                                    , "LVL_3_NM": lv3_objs[0]["LVL_3_NM"]
                                    , "SCR_ID" : lv3_objs[0]["SCR_ID"]
                                };

                                menuObj2.SUB.push(menuObj3);
                            }
                        }
                    }
                    else {
                        menuObj2["SCR_ID"] = lv2_objs[0]["SCR_ID"];
                    }
                }

            }
        }
        else {
            menuObj["SCR_ID"] = lv1_objs[0]["SCR_ID"];
        }
    }

    setMenu(menuObjs);
    var a = "";
}

function setMenu(menuJson) {

    /*
     *  top menu 만들기
     */
    var html = "";
    var obj = null;
    for (var i = 0; i < menuJson.length; i++) {

        obj = menuJson[i];
        html += "<div class='menu-top-item btn' cd='" + obj.LVL_1_CD + "'>" + obj.LVL_1_NM + "</div>";
    }

    $("#menu-top-div").html(html);


    /*
     * left menu 만들기
     */

    var html = "<ul class='lv1-ul'>";
    var obj = null;
    var obj2 = null;
    var obj3 = null;
    for (var i = 0; i < menuJson.length; i++) {

        obj = menuJson[i];
        if (obj.SCR_ID != null) {
            html += "<li class='menu-left-item lv1 btn' cd='" + obj.LVL_1_CD + "' SCR-ID='" + obj.SCR_ID + "'><div>" + obj.LVL_1_NM + "</div>";
        }
        else {
            html += "<li class='menu-left-item lv1 btn' cd='" + obj.LVL_1_CD + "'><div>" + obj.LVL_1_NM + "</div>";
        }
        if (obj.SUB != null) {


            html += "<ul class='lv2-ul menu-ul' >";

            for (var i2 = 0; i2 < obj.SUB.length; i2++) {
                obj2 = obj.SUB[i2];

                if (obj2.SCR_ID != null) {
                    html += "<li class='menu-left-item btn lv2'  cd='" + obj2.LVL_2_CD + "'  SCR-ID='" + obj2.SCR_ID + "'><div><span class='lv2-icon'></span>" + obj2.LVL_2_NM + "</div>";
                }
                else {
                    html += "<li class='menu-left-item btn lv2'  cd='" + obj2.LVL_2_CD + "'><div><span class='lv2-icon'></span>" + obj2.LVL_2_NM + "</div>";
                }
               

                if (obj2.SUB != null) {
                    html += "<ul class='lv3-ul menu-ul'>";

                    for (var i3 = 0; i3 < obj2.SUB.length; i3++) {
                        obj3 = obj2.SUB[i3];

   
                        if (obj3.SCR_ID != null) {
                            html += "<li class='menu-left-item btn lv3'  cd='" + obj3.LVL_3_CD + "'  SCR-ID='" + obj3.SCR_ID + "'>" + obj3.LVL_3_NM;
                        }
                        else {
                            html += "<li class='menu-left-item btn lv3'  cd='" + obj3.LVL_3_CD + "'>" + obj3.LVL_3_NM;
                        }


                    }

                    html += "</ul>";
                }
                html + "</li>";

            }

            html += "</ul>";
        }

        html + "</li>";
    }
    html += "</ul>";

    $("#menu-left-div").html(html);


    $(".lv3-ul.menu-ul").css("height", 0);



    $(".menu-top-item").on("click", function (e) {

        topItemClick(e.target, true);

    })

    $(".menu-left-item.lv1,.menu-left-item.lv3").on("click", function () {

        var SCR_ID = $(this).attr("SCR-ID");

        if (SCR_ID != null) {
            setLongviewEvent("MENU_OPEN", SCR_ID);
            leftMenuView(false);
        }
        return false;
    })
    $(".menu-left-item.lv2").on("click", function () {

        var SCR_ID = $(this).attr("SCR-ID");
        var old_ul = $(".menu-left-item.active").find("ul");

        $(".menu-left-item.active").removeClass("active");

        $(this).addClass("active");
        if (SCR_ID != null) {
            setLongviewEvent("MENU_OPEN", SCR_ID);
            leftMenuView(false);

        }
        else {

            $(old_ul).stop().animate({ "height": 0 }, OPTION.menu.animate_time);

            var target_ul = $(this).find("ul");

            if ($(old_ul).closest("li").attr("cd") == $(target_ul).closest("li").attr("cd")) {
                $(this).removeClass("active");
                return false;
            }

            $(target_ul).css("height", "auto");
            var h = $(target_ul).height();
            $(target_ul).css("height", 0);

            $(target_ul).stop().animate({ "height": h }, OPTION.menu.animate_time);

        }
        return false;
    })

    firstMenuClick();
}

function firstMenuClick() {

    var target_top_item = $(".menu-top-item")[0];
    var target_cd = $(target_top_item).attr("cd");
    topItemClick(target_top_item, false);
    var target_left_item = $(".menu-left-item.lv1[cd='" + target_cd + "']");
    if ($(target_left_item).attr("scr-id") != null) {
        $(target_left_item).trigger("click");
    }
    else {
        var target_li = $(target_left_item).find("li[scr-id!='']");
    }
}

function topItemClick(target, viewflag) {
    $(".menu-top-item.active").removeClass("active");
    $(target).addClass("active");

    $(".lv3-ul.menu-ul").css("height", 0);
    $(".menu-left-item.lv2").removeClass("active");

    var cd = $(target).attr("cd");
    $(".menu-left-item.lv1").css("display", "none");

    $(".menu-left-item.lv1[cd='" + cd + "']").css("display", "block");

    leftMenuView(viewflag);
    console.log("selected : " + $(target).attr("cd"));

}
function setMenuActive() {
    
}
function distinctItem(data, field) {

    var lookup = {};
    var items = data;
    var result = [];

    for (var item, i = 0; item = items[i++];) {
        var name = item[field];

        if (!(name in lookup)) {
            lookup[name] = 1;
            result.push(name);
        }
    }
    return result;
}