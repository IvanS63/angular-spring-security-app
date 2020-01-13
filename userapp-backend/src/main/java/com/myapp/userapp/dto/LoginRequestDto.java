package com.myapp.userapp.dto;

/**
 * LoginRequest.
 *
 * @author Ivan_Semenov
 */
public class LoginRequestDto {
    private String login;
    private String password;

    public LoginRequestDto() {
    }

    public LoginRequestDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
