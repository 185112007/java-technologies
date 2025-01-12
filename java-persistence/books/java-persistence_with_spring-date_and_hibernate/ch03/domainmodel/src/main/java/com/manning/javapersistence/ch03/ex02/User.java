package com.manning.javapersistence.ch03.ex02;

import java.util.StringTokenizer;

public class User {
    private String firstName;
    private String lastName;

    public String getName(){
        return firstName + ' ' + lastName;
    }

    public void setName(String name){
        StringTokenizer tokenizer = new StringTokenizer(name);
        firstName = tokenizer.nextToken();
        lastName = tokenizer.nextToken();
    }
}
