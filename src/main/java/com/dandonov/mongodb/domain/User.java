package com.dandonov.mongodb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.document.mongodb.mapping.Document;

@Document
public class User {

    @Id
    private String userId;

    private String name;
    private String homeTown;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(final String homeTown) {
        this.homeTown = homeTown;
    }

    @Override
    public String toString() {
        return "User [id=" + userId + ", name=" + name + ", age=" + age + ", home town=" + homeTown + "]";
    }

}