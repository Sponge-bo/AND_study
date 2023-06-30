package com.example.test4_2.entity;

public class JobInfo {
    private Integer username;
    private Integer password;
    private String time;
    private String salary;
    private String location;
    private String requirement;

    private byte[] imageData;

    public JobInfo(Integer username,Integer password,String time, String salary, String location, String requirement, byte[] imageData) {
        this.username = username;
        this.password = password;
        this.time = time;
        this.salary = salary;
        this.location = location;
        this.requirement = requirement;
        this.imageData = imageData;
    }

    public Integer getUsername() {
        return username;
    }

    public void setUsername(Integer username) {
        this.username = username;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

