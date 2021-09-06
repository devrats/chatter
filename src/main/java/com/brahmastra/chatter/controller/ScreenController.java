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
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ScreenController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody HashMap<String, Object> map) {
        Person person = new Person((String) map.get("name"), "waiting");
        Person save = personRepository.save(person);
        return service(save);
    }

    @ResponseBody
    @RequestMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody HashMap<String, Object> map) {
        Optional<Person> persons = Optional.ofNullable(personRepository.findById(Integer.parseInt((String) map.get("id"))));
        Person person = persons.get();
        personRepository.delete(person);
        return ResponseEntity.ok(Map.of("msg", "updated"));
    }

    public synchronized ResponseEntity<?> service(Person save){
        synchronized (this) {
            Person personRepositoryById;
            Person personRepositoryById1;
            while (true) {
                System.out.println(personRepository.findById(save.getId()));
                System.out.println(personRepository.findById(save.getId()).getStatus().equals("login"));
                System.out.println(personRepository.findById(save.getId()).getStatus());
                System.out.println(personRepository.findById(save.getId()).getName());
                if (personRepository.findById(save.getId()).getStatus().equals("login")) {
                    try {
                        System.out.println("dev");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("pikachu");
                    String url = personRepository.findById(save.getId()).getUrl();
                    System.out.println(url);
                    HashMap hashMap = new HashMap<String, String>();
                    hashMap.put("idea", save.getId());
                    hashMap.put("url", url);
                    return ResponseEntity.ok(hashMap);
                } else {
                    List<Person> waiting = personRepository.findAllByStatusAndIdIsNot("waiting", save.getId());
                    if (!waiting.isEmpty()) {
                        personRepositoryById1 = personRepository.findById(waiting.get(0).getId());
                        personRepositoryById1.setStatus("login");
                        personRepositoryById = personRepository.findById(save.getId());
                        personRepositoryById.setStatus("login");
                        personRepository.save(personRepositoryById);
                        personRepository.save(personRepositoryById1);
                        break;
                    } else {
                        continue;
                    }
                }
            }
            String url = "/chat/chatBox/" + personRepositoryById.getName() + personRepositoryById.getId() + "/" +
                    personRepositoryById1.getName() + personRepositoryById1.getId();
            personRepositoryById.setUrl(url);
            personRepositoryById1.setUrl(url);
            System.out.println("hello world" + save.getName());
            personRepository.save(personRepositoryById);
            personRepository.save(personRepositoryById1);
            HashMap hashMap = new HashMap<String, String>();
            hashMap.put("idea", save.getId());
            hashMap.put("url", url);
            return ResponseEntity.ok(hashMap);
        }
    }
}