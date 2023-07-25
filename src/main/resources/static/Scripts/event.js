
function setEvent() {
    $(document).on("click", ".btn", function (e) {

        var id = $(e.target).attr("id");

        console.log("id = " + id);

        if (id == "logo") {
            alert("logo click !!!")
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