package com.example.blog.enums;

public enum UserType {
    Blogger("1"),
    Admin("2");
    private String value;

    UserType(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return value;
    }

}
