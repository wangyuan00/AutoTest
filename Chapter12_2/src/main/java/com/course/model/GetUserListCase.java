package com.course.model;

public class GetUserListCase {
    private int id;
    private String userName;
    private int age;
    private String sex;
    private String expected;

    @Override
    public String toString() {
        return (
                "{id=" + id + "," +
                        "userName=" + userName + "," +
                        "age=" + age + "," +
                        "sex=" + sex + "," +
                        "expected=" + expected + "}"
        );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }
}
