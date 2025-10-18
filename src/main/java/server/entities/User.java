package server.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class User {

    private Integer id;

    private String name;

    private String lastName;

    private String login;

    private String password;

    private String imagePath;
}
