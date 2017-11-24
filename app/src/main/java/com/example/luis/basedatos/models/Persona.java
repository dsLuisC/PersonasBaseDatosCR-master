package com.example.luis.basedatos.models;

/**
 * Created by luis on 29/09/17.
 */

public class Persona {
    private int id;
    private String name;
    private String lastname;
    private int age;
    private String phone;

    public Persona(int id, String name, String lastname, int age, String phone) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.phone = phone;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
