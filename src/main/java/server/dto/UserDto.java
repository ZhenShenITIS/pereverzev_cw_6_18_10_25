package server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserDto {
    private String name;
    private String login;
    private String lastName;
    private String imagePath;
}
