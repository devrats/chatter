/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 05-Sep-21
 *   Time: 5:49 PM
 *   File: ScreenController.java
 */

package com.brahmastra.chatter.controller;
import com.brahmastra.chatter.entity.Person;
import com.brahmastra.chatter.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ScreenController {


    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping("/book")
    public String book() {
        return "book";
    }

}