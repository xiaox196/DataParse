package com.ming.data.entity;

/**
 * @author alun
 * @data 2019/12/11
 */
public class Person {
    String phoneNumber;
    String cnid;
    String name;
    String email;
    String addr;

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCnid(String cnid) {
        this.cnid = cnid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCnid() {
        return cnid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddr() {
        return addr;
    }
}
