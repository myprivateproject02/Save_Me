package com.example.save_me.model;

public class Name  {

    private String name;
    private String sname;

    public Name(String name, String sname) {
        this.name = name;
        this.sname = sname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\'' +
                ", sname='" + sname + '\'' +
                '}';
    }
}
