/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 05-Sep-21
 *   Time: 5:49 PM
 *   File: ScreenController.java
 */

package com.brahmastra.chatter.controller;
import com.brahmastra.chatter.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScreenController {


    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping("/book/page")
    public String book() {
        return "book";
    }

}