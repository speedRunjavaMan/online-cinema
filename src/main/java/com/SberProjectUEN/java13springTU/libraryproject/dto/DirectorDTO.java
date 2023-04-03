package com.SberProjectUEN.java13springTU.libraryproject.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class DirectorDTO
      extends GenericDTO {
    private String directorsFio;
    private String position;
    private Set<Long> filmsIds;
    private boolean isDeleted;
}
