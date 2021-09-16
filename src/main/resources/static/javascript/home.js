let stompClient = null

$("document").ready((e)=>{
    $(".enter-chat-room").click(()=>{
        let name = $("#name").val()
        localStorage.setItem("name",name)
        connect()
    })
})


function scroller(){
    $(".chatter").css("height","auto")
}

function connect(){
    let socket = new SockJS('/server1')
    stompClient = Stomp.over(socket)
    stompClient.connect({},function(frame) {
        $(".greet").prepend(localStorage.getItem("name"))
        $.ajax({
            url: "/enter",
            data: JSON.stringify({name: localStorage.getItem("name")}),
            contentType: 'application/json',
            type: 'POST',
            dataType: 'json',
            success: function(response) {
                localStorage.setItem("id",response.idea)
                window.location.replace("http://localhost:8080/book/page")
                stompClient.subscribe("/users/queue/messages",function (response) {
                    showMessage(JSON.parse(response.body))
                })
            },
            error: function(error) {

            }
        });
    })
}

function showMessage(message) {
    $(".table12").prepend(`<span> <b><i>${message.name} : </i></b>${message.text}</span> <br>`)
}

function sendMessage() {
    let message = $("#message").val()
    let jasonOb = {
        name:localStorage.getItem("name"),
        text:message
    }
    showMessage(jasonOb)
    stompClient.send("/chatting/message/" + localStorage.getItem("urls"),{},JSON.stringify(jasonOb))
}

function logout(){
    if(stompClient!==null){
        localStorage.removeItem("name")
        stompClient.disconnect()
        $.ajax({
            url: "/exit",
            data: JSON.stringify({id: localStorage.getItem("id")}),
            contentType: 'application/json',
            type: 'POST',
            dataType: 'json',
            success: function(response) {
                window.location.replace("http://localhost:8080/")
            },
            error: function(error) {

            }
        });
    }
}


function start(){
    let socket = new SockJS('/server1')
    stompClient = Stomp.over(socket)
    stompClient.connect({},function(frame) {
        $(".greet").prepend(localStorage.getItem("name"))
        $.ajax({
            url: "/book/enter",
            data: JSON.stringify({id: localStorage.getItem("id")}),
            contentType: 'application/json',
            type: 'POST',
            dataType: 'json',
            success: function(response) {
                $(".loading").css("display","none")
                localStorage.setItem("urls",response.url)
                stompClient.subscribe("/user/queue/messages",function (response) {
                    showMessage(JSON.parse(response.body))
                })
            },
            error: function(error) {

            }
        });
    })
}