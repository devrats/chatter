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
        $(".home").css("display","none")
        $(".chat-box").css("display","block")
        stompClient.subscribe("/chat/chatBox",function (response) {
            showMessage(JSON.parse(response.body))
        })
    })
}

function showMessage(message) {
    console.log(message.name)
    console.log(message.text)
    $(".table12").prepend(`<span> <b><i>${message.name} : </i></b>${message.text}</span> <br>`)
}

function sendMessage() {
    let message = $("#message").val()
    let jasonOb = {
        name:localStorage.getItem("name"),
        text:message
    }
    $("#message").val("")
    stompClient.send("/chatting/message",{},JSON.stringify(jasonOb))
}

function logout(){
    if(stompClient!==null){
        localStorage.removeItem("name")
        stompClient.disconnect()
        window.location.replace("http://localhost:8080/")
    }
}
