// 1. 로그인 여부 확인
function checkLogin() {

    if (isNotLogin) {

        alert("회원가입 및 로그인 후 이용해 주세요^^");

        location.href = contextPath + "/signup";

        return false;
    }

    return true;
}


// 2. 메인페이지 전체 버튼 및 링크 클릭 검사
document.addEventListener("click", function(event) {

    const target = event.target.closest("a, button, .playlist");

    // 버튼이나 링크, 플레이리스트가 아니면 실행하지 않음
    if (target == null) return;

    // 회원가입과 로그인 링크는 정상적으로 이용 가능
    if (target.matches(".header-signup, a.login")) return;

    // 비로그인 사용자 접근 제한
    // if (!checkLogin()) {
    //
    //     event.preventDefault();
    //     event.stopImmediatePropagation();
    //
    // }

}, true);


// 3. 아직 구현되지 않은 기능
function comingSoon(event) {

    if (event) event.preventDefault();

    alert("이 기능은 추후 연결할 예정입니다.");

    return false;
}


// 4. 플레이리스트 구경하기
function scrollToPlaylists() {

    document.querySelector("#playlists").scrollIntoView({
        behavior: "smooth"
    });

}


// 5. 플레이리스트 상세보기
document.querySelectorAll(".playlist").forEach(playlist => {

    playlist.addEventListener("click", function(event) {

        event.stopPropagation();

        alert(
            this.dataset.title +
            "\n\n플레이리스트 상세페이지는 추후 연결할 예정입니다."
        );

    });

});