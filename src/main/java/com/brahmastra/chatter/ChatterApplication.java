package com.brahmastra.chatter;

import com.brahmastra.chatter.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatterApplication.class, args);
    }

}
