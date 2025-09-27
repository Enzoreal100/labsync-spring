package com.dto;

public class UserDTO {
    private int id;
    private String name;
    private String email;

    public UserDTO() {}

    public UserDTO(int id, String name, Postion position) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}