$(document).ready(function () {

    getMenu();

    $("body").html("<div id='" + OPTION.view.id + "'></div>");
    $("#contents").append("<div id='" + OPTION.menu.top.id + "'></div>");
    $("#contents").append("<div id='" + OPTION.menu.left.id + "'></div>");

    $("body").append("<iframe id='lv_main' src='/lv_main'></iframe>");
    $("body").append("<div id='overlay'></div>");

    // left menu 생성 후 감추기
    $("#" + OPTION.menu.left.id).css({ "left": $("#" + OPTION.menu.left.id).width() * -1 , "opacity": 0});

    var html = "<div id='menu-top-contents'>";
    html += "<div id='left-menu-toggle' class='btn'></div>";
    html += "<div id='logo'  class='btn'></div>";
    html += "<div id='menu-top-div'></div>";
    html += "</div > ";

    $("#" + OPTION.menu.top.id).html(html);


    var html = "<div id='menu-left-contents'>";
    html += "<div id='menu-left-div'></div>";
    html += "</div > ";

    $("#" + OPTION.menu.left.id).html(html);



    ScreenResize();

    setElement();

    setEvent();

});


function getMenu() {

    $.ajax({
        url: "/menu", // 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소
        data: "",//JSON.stringify({ "token": token }),  // HTTP 요청과 함께 서버로 보낼 데이터
        method: "POST",   // HTTP 요청 메소드(GET, POST 등)
        dataType: "json", // 서버에서 보내줄 데이터의 타입
        contentType: "application/json; charset=utf-8",
    })
        // HTTP 요청이 성공하면 요청한 데이터가 done() 메소드로 전달됨.
        .done(function (data) {
            console.log(data);
            if (data != null) {
                parseMenuData(data);
            }
        })

        // HTTP 요청이 실패하면 오류와 상태에 관한 정보가 fail() 메소드로 전달됨.
        .fail(function (xhr, status, errorThrown) {

            console.log("Error : " + status);

        })
        .always(function (xhr, status) {

        });

}


function setElement() {

    $("#" + OPTION.menu.left.id).css({
        "height": setLeftMenuSize()
    })

}

$(window).resize(function () {
    ScreenResize();
});
function ScreenResize() {

    var width = window.innerWidth;
    var contents_width = $("#" + OPTION.view.id).width();
    OPTION.view.scale = width / contents_width;

    $("body").css("transform", "scale(" + OPTION.view.scale + ")");

    setLeftMenuSize();
}


