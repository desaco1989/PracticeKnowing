package com.desaco.practiceknowing.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by desaco on 2018/5/24.
 */

public class Group {
    private int id;
    private String name;
    private List<User> list = new ArrayList<User>();
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
    public List<User> getList() {
        return list;
    }
    public void setList(List<User> list) {
        this.list = list;
    }
}
