package com.antoniosgarbi.dto;

import com.antoniosgarbi.entities.User;

public class UserDTO {
    private Integer id;
    private String login;
    private String name;
    private String password;

    public UserDTO() {
    }

    public UserDTO(Integer id, String login, String name, String password) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.login = entity.getLogin();
        this.name = entity.getName();
        this.password = entity.getPassword();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
