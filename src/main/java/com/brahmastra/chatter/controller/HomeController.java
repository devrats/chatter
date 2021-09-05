/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 05-Sep-21
 *   Time: 5:37 PM
 *   File: HomeController.java
 */

package com.brahmastra.chatter.controller;

import com.brahmastra.chatter.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class HomeController {

    @MessageMapping("message")
    @SendTo("/chat/chatBox")
    @ResponseBody
    public Message message(@RequestBody Message message){
        return message;
    }
}