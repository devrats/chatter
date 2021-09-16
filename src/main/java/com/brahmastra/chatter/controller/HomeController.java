/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 05-Sep-21
 *   Time: 5:37 PM
 *   File: HomeController.java
 */

package com.brahmastra.chatter.controller;

import com.brahmastra.chatter.entity.Message;
import com.brahmastra.chatter.entity.Person;
import com.brahmastra.chatter.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message/{urls}")
    @ResponseBody
    public void message(@DestinationVariable String urls, @Payload Message message, Principal principal){
        Person byId = personRepository.findById(Integer.parseInt(urls));
        System.out.println(byId);
        simpMessagingTemplate.convertAndSendToUser(byId.getName(), "/queue/messages",message);
    }

    @ResponseBody
    @RequestMapping("/enter")
    public ResponseEntity<?> login(@RequestBody HashMap<String, Object> map) {
        Person person = new Person((String) map.get("name"), "waiting",bCryptPasswordEncoder.encode(" "),"ROLE_USER");
        Person save = personRepository.save(person);
        return ResponseEntity.ok(Map.of("idea",save.getId()));
    }

    @ResponseBody
    @RequestMapping("/exit")
    public ResponseEntity<?> logout(@RequestBody HashMap<String, Object> map) {
        Optional<Person> persons = Optional.ofNullable(personRepository.findById(Integer.parseInt((String) map.get("id"))));
        Person person = persons.get();
        personRepository.delete(person);
        return ResponseEntity.ok(Map.of("msg", "updated"));
    }

    @ResponseBody
    @RequestMapping("/book/enter")
    public synchronized ResponseEntity<?> service(@RequestBody HashMap<String, Object> map) {
        Person personRepositoryById;
        Person personRepositoryById1;
        while (true) {
            if (personRepository.findById(Integer.parseInt((String) map.get("id"))).getStatus().equals("login")) {
                String url = personRepository.findById(Integer.parseInt((String) map.get("id"))).getUrl();
                HashMap hashMap = new HashMap<String, String>();
                hashMap.put("idea", Integer.parseInt((String) map.get("id")));
                hashMap.put("url", url);
                return ResponseEntity.ok(hashMap);
            } else {
                List<Person> waiting = personRepository.findAllByStatusAndIdIsNot("waiting", Integer.parseInt((String) map.get("id")));
                if (!waiting.isEmpty()) {
                    personRepositoryById1 = personRepository.findById(waiting.get(0).getId());
                    personRepositoryById = personRepository.findById(Integer.parseInt((String) map.get("id")));
                    personRepositoryById1.setStatus("login");
                    personRepositoryById.setStatus("login");
                    break;
                } else {
                    continue;
                }
            }
        }
        String url1 = String.valueOf(personRepositoryById1.getId());
        String url2 = String.valueOf(personRepositoryById.getId());
        personRepositoryById.setUrl(url1);
        personRepositoryById1.setUrl(url2);
        personRepository.save(personRepositoryById);
        personRepository.save(personRepositoryById1);
        HashMap hashMap = new HashMap<String, String>();
        hashMap.put("idea", Integer.parseInt((String) map.get("id")));
        hashMap.put("url", url1);
        return ResponseEntity.ok(hashMap);
    }

}