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
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class ScreenController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @ResponseBody
    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody HashMap<String, Object> map){
        Person person = new Person((String) map.get("name"),"waiting");
        personRepository.save(person);
        return ResponseEntity.ok(Map.of("idea", 1));
    }

    @ResponseBody
    @RequestMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody HashMap<String, Object> map){
        Optional<Person> persons = Optional.ofNullable(personRepository.findById(Integer.parseInt((String) map.get("id"))));
        Person person = persons.get();
        personRepository.delete(person);
        return ResponseEntity.ok(Map.of("msg", "updated"));
    }

}