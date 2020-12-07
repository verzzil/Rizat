package ru.itis.models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Long id;
    private String creationDate;
    private Long userId;
    private String userName;
    private String name;
    private String text;
}
