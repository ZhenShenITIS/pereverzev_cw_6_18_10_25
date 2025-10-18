package server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserRegistrationDto {
    private String name;
    private String lastName;
    private String login;
    private String password;
    private String imagePath;

}
