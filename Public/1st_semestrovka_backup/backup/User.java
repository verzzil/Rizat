package ru.itis.models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashPassword;
    private Integer age;

}

