/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 23-Jul-21
 *   Time: 8:53 PM
 *   File: PublicDestailsService.java
 */

package com.brahmastra.chatter.security;


import com.brahmastra.chatter.entity.Person;
import com.brahmastra.chatter.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PersonDestailsService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person personByUserName = personRepository.findPersonByName(s);
        System.out.println(s);
        System.out.println(personByUserName);
        if (personByUserName == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        PersonDetail personDetail = new PersonDetail(personByUserName);
        return personDetail;
    }
}