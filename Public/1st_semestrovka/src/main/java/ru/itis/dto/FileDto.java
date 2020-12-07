package ru.itis.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FileDto {
    private Integer id;
    private String content;
    private String filename;
}
