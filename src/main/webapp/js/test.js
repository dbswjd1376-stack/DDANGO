document.querySelector("#ajax-test").addEventListener("click", function() {
    fetch("/ai/mood", {}).then(response => response.json()).then((data) => {
        console.log(data);
    })
})