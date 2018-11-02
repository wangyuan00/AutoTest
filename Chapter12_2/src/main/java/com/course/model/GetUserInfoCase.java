package com.course.model;

public class GetUserInfoCase {
    private int id;
    private String userId;
    private String expected;

    @Override
    public String toString() {
        return (
                "{id=" + id + "," +
                        "userId=" + userId + "," +
                        "expected=" + expected + "}"
        );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }
}
