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
            url: "/login",
            data: JSON.stringify({name: localStorage.getItem("name")}),
            contentType: 'application/json',
            type: 'POST',
            dataType: 'json',
            success: function(response) {
                localStorage.setItem("id",response.idea)
                $(".home").css("display","none")
                $(".chat-box").css("display","block")
                stompClient.subscribe(response.url,function (response) {
                    showMessage(JSON.parse(response.body))
                })
            },
            error: function(error) {

            }
        });
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
        $.ajax({
            url: "/logout",
            data: JSON.stringify({id: localStorage.getItem("id")}),
            contentType: 'application/json',
            type: 'POST',
            dataType: 'json',
            success: function(response) {
                console.log(response)
                window.location.replace("http://localhost:8080/")
            },
            error: function(error) {

            }
        });
    }
}
