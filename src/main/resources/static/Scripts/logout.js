$(document).on("click", ".logout", function (evt) {
    var result = confirm('로그아웃 하시겠습니까?');
    if(result == true)
    {
        nanosService.nanosServiceAuth.logout();
    }
});