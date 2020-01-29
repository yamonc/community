function post() {
    let id = $("#question_id").val();
    let content = $("#comment_content").val();
    console.log(id + content);
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": id,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            //成功后：将回复框隐藏
            if (response.code === 200) {
                $("#comment_section").hide();
            } else {
                if (response.code === 2003) {
                    let isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open('https://github.com/login/oauth/authorize?client_id=484d48afe89d71f9ffd2&redirect_uri=http://localhost:80/callback&scope=user&state=1');
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
            console.log(response)

        },
        dataType: "json"
    })

}