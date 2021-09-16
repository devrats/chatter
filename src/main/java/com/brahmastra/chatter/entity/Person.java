/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 05-Sep-21
 *   Time: 7:10 PM
 *   File: Person.java
 */

package com.brahmastra.chatter.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String status;
    private String url;
    private String password;
    private String role;

    public Person(String name, String status, String password, String role) {
        this.name = name;
        this.status = status;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Person(String name, String status, String role) {
        this.name = name;
        this.status = status;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId() == person.getId() && Objects.equals(getName(), person.getName()) && Objects.equals(getStatus(), person.getStatus()) && Objects.equals(getUrl(), person.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStatus(), getUrl());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Person(int id, String name, String status, String url) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Person(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public Person() {
    }

    public Person(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}