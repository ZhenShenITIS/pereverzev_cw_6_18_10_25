package server.dto;

import lombok.Builder;

@Builder
public class UserDto {
    private String name;
    private String login;
    private String lastName;

    public UserDto(String name, String lastName, String login) {
        this.name = name;
        this.login = login;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }
}
