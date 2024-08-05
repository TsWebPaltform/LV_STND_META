
function setEvent() {
    $(document).on("click", ".btn", function (e) {

        var id = $(e.target).attr("id");

        console.log("id = " + id);

        if (id == "logo") {
            // 'menu-top-div' ID를 가진 요소 안의 첫 번째 'menu-top-item' 클래스를 가진 요소를 선택
            var firstMenuItem = document.querySelector('#menu-top-div .menu-top-item');

            // 해당 요소가 존재하는지 확인
            if (firstMenuItem) {
                // 요소를 클릭하도록 시뮬레이션
                firstMenuItem.click();
            } else {
                console.log('menu-top-div 안에 "menu-top-item" 클래스를 가진 요소가 없습니다.');
            }
        }
        else if (id == "left-menu-toggle") {
            var menu_position = $("#menu-left").position();
            console.log(menu_position);
            if (menu_position.left != 0) {

                leftMenuView(true);
            }
            else {
                leftMenuView(false);
            }
        }

        else {

            if ($(e.target).hasClass("top-icon")) {

                var keyword = $(e.target).attr("keyword");

                if (keyword != null) {
                    setLongviewEvent(keyword);
                }

            }
        }

        

    })

}

function leftMenuView(flag) {

    var target = $("#menu-left");
    var left = 0;
    var opacity = 1;
    $(target).stop();

    if (flag == false) {
        left = $(target).width() * -1;
        opacity = 0;
    }
    $(target).animate({ "left": left, "opacity": opacity }, OPTION.menu.animate_time);


    var target = $("#overlay");
    $(target).animate({ "opacity": opacity * 0.1 }, OPTION.menu.animate_time);

}