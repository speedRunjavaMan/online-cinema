package com.SberProjectUEN.java13springTU.libraryproject.dto;

import lombok.*;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DirectorWithFilmsDTO
        extends DirectorDTO {
    private Set<FilmDTO> films;
}
