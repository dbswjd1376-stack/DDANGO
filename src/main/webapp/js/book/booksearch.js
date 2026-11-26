
// 추천 방식 선택
function selectMode(mode, button) {

    // 선택한 추천 방식 저장
    document.getElementById("mode").value = mode;

    // 모든 버튼의 선택 효과 제거
    const buttons = document.querySelectorAll(".mode-btn");

    buttons.forEach(function(btn) {
        btn.classList.remove("active");
    });

    // 선택한 버튼만 강조
    button.classList.add("active");

    // YOUR BOOK JOURNEY 갱신
    updateJourney();
}


// 현재 책 제목과 추천 방식을 화면에 표시
function updateJourney() {

    const title = document.getElementById("bookTitle").value.trim();
    const mode = document.getElementById("mode").value;

    // 책 제목이 비어 있으면 기본 문구
    const bookName = title === "" ? "책 선택 전" : title;

    // 추천 방식에 따른 문구
    const modeName = mode === "all" ? "전체 플리" : "챕터별 플리";

    // 화면에 출력
    document.getElementById("journeyText").textContent =
        bookName + " → " + modeName;
}


// 책 제목을 입력할 때마다 갱신
document.getElementById("bookTitle").addEventListener("input", updateJourney);


// 검색 버튼 클릭 시 AJAX 실행
document.getElementById("searchForm").addEventListener("submit", function(event) {

    // 기본 페이지 이동 막기
    event.preventDefault();

    const form = this;
    const formData = new FormData(form);
    const params = new URLSearchParams(formData);

    fetch(form.action + "?" + params.toString())
        .then(function(response) {
            return response.text();
        })
        .then(function(result) {

            // 검색 결과를 현재 페이지에 출력
            document.getElementById("searchResult").innerHTML = result;

        })
        .catch(function(error) {
            console.log("검색 오류 : ", error);
        });

});
