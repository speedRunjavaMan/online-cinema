package com.SberProjectUEN.java13springTU.libraryproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleDTO {
    private Long id;
    private String title;
    private String description;
}
