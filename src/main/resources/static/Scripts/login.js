$(document).ready(function() {
    $('#loginForm').submit(function(e) {
        e.preventDefault(); // 폼 기본 동작 방지

        // 선택한 회사 정보 가져오기
        var selectedCompCd = $("#compCd").val();

        // 사용자 입력값 가져오기
        var empNo = $('#empNo').val();
        var password = $('#password').val();


        var requestData = {
            empNo: empNo,
            password: password,
            compCd: selectedCompCd
        };

        $.ajax({
            url: '/login',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(requestData),
            success: function(response) {
                // 로그인 성공 시 처리 로직
                window.location.href = '/menus'; // 로그인 후 이동할 페이지
            },
            error: function(xhr, status, error) {
                // 로그인 실패 시 처리 로직
                alert("아이디 패스워드,회사정보를 확인하세요.")
            }
        });
    });
});

window.addEventListener('DOMContentLoaded', (event) => {
    // ID와 PW 입력 필드를 가져옵니다.
    const empNoInput = document.getElementById('empNo');
    const passwordInput = document.getElementById('password');

    // 로그인 버튼을 가져옵니다.
    const loginButton = document.getElementById('signin');

    // ID저장 체크박스를 가져옵니다.
    const idSaveCheckbox = document.getElementById('idSave');

    // ID를 로컬 스토리지에서 불러와 입력 필드에 설정합니다.
    const savedId = localStorage.getItem('savedId');
    if (savedId) {
        empNoInput.value = savedId;
        idSaveCheckbox.checked = true;
    }

    // ID저장 체크박스의 변경 이벤트를 처리합니다.
    idSaveCheckbox.addEventListener('change', (event) => {
        if (idSaveCheckbox.checked) {
            localStorage.setItem('savedId', empNoInput.value);
        } else {
            localStorage.removeItem('savedId');
        }
    });

    // PW저장 체크박스를 가져옵니다.
    const pwSaveCheckbox = document.getElementById('pwSave');

    // PW를 로컬 스토리지에서 불러와 입력 필드에 설정합니다.
    const savedPw = localStorage.getItem('savedPw');
    if (savedPw) {
        passwordInput.value = savedPw;
        pwSaveCheckbox.checked = true;
    }

    // PW저장 체크박스의 변경 이벤트를 처리합니다.
    pwSaveCheckbox.addEventListener('change', (event) => {
        if (pwSaveCheckbox.checked) {
            localStorage.setItem('savedPw', passwordInput.value);
        } else {
            localStorage.removeItem('savedPw');
        }
    });

    // 로그인 버튼의 클릭 이벤트를 처리합니다.
    loginButton.addEventListener('click', (event) => {
        // 로그인이 성공적으로 이루어졌을 때, ID와 PW를 저장합니다.
        if (idSaveCheckbox.checked) {
            localStorage.setItem('savedId', empNoInput.value);
        }
        if (pwSaveCheckbox.checked) {
            localStorage.setItem('savedPw', passwordInput.value);
        }
    });


});
