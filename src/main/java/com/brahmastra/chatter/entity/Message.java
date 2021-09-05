/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 05-Sep-21
 *   Time: 5:38 PM
 *   File: Message.java
 */

package com.brahmastra.chatter.entity;

import java.util.Objects;

public class Message {
    private String name;
    private String text;

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(getName(), message.getName()) && Objects.equals(getText(), message.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getText());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public Message(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Message() {
    }

    public Message(String name, String text) {
        this.name = name;
        this.text = text;
    }
}