package com.SberProjectUEN.java13springTU.libraryproject.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FilmRentInfoDTO
        extends GenericDTO {

    private LocalDateTime rentDate;
    private LocalDateTime returnDate;
    private Boolean returned;
    private Integer rentPeriod;
    private Long filmId;
    private Long userId;
    private FilmDTO filmDTO;
    private Boolean purchase;
}
