package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {
    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    private String name;
    private Integer age; // null 포함 가능
}
