// 1. 로그인 여부 확인
function checkLogin() {

    if (isNotLogin) {

        alert("회원가입 및 로그인 후 이용해 주세요^^");

        location.href = contextPath + "/signup";

        return false;
    }

    return true;
}


// 2. 비로그인 사용자 기능 접근 제한
document.addEventListener("click", function(event) {

    const target = event.target.closest("a, button, .playlist");

    // 버튼이나 링크가 아니면 검사하지 않음
    if (target == null) return;

    // 헤더의 회원가입 및 로그인 및 로고 버튼 제외
    if (target.matches(".header-signup, a.login, a.logo")) return;

    // 로그인 및 회원가입 입력 폼 제외
    if (target.closest(".signup-form")) return;

    // 로그인 및 회원가입 하단 이동 링크 제외
    if (target.closest(".signup-bottom")) return;

    // 비로그인 사용자 접근 제한
    if (!checkLogin()) {

        event.preventDefault();
        event.stopImmediatePropagation();
    }

}, true);


// 4. 플레이리스트 구경하기
function scrollToPlaylists() {

    document.querySelector("#playlists").scrollIntoView({
        behavior: "smooth"
    });

}
