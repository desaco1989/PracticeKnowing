package com.desaco.practiceknowing.data_parse;

/**
 * Created by desaco on 2018/5/24.
 */

public class Person {
    private int id;
    private String name;
    private String blog;

    public Person(){

    }
    public Person(int id,String name,String blog){
        this.id = id;
        this.name = name;
        this.blog = blog;
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

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }
}
