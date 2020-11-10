package com.blood.dbframe.greendao.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {

    @Id
    private Long id;

    @Property(nameInDb = "NAME")
    private String name;

    @Property(nameInDb = "ADDRESS")
    private String addr;

//    @Property
//    private String address;

    @Property
    private int age;

//    @Property
//    private int phone;

    public User() {
    }

    @Generated(hash = 1663452608)
    public User(Long id, String name, String addr, int age) {
        this.id = id;
        this.name = name;
        this.addr = addr;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", age=" + age +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.addr;
    }

    public void setAddress(String address) {
        this.addr = address;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
