/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 05-Sep-21
 *   Time: 7:13 PM
 *   File: PersonRepository.java
 */

package com.brahmastra.chatter.repository;

import com.brahmastra.chatter.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Period;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Integer> {

    public List<Person> findAllByStatusAndIdIsNot(String status,int id);
    public Person findById(int id);
    public Person findPersonByName(String s);
}