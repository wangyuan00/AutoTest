package com.course.model;

public class AddUserCase {
    private int id;
    private String userName;
    private String password;
    private String sex;
    private String age;
    private String permission;
    private String isDelete;
    private String expected;

    @Override
    public String toString() {
        return (
                "{id=" + id + "," +
                        "userName=" + userName + "," +
                        "password=" + password + "," +
                        "sex=" + sex + "," +
                        "age=" + age + "," +
                        "permission=" + permission + "," +
                        "isDelete=" + isDelete + "," +
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }
}
