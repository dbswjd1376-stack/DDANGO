function delPlayList(no) {
    let ok = confirm("진짜로 지우실건가요?")
    if (ok) {
        location.href = "/plDel-c?no=" + no
    }
}