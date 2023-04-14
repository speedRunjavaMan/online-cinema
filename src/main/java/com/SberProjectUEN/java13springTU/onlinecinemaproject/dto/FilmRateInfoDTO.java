package com.SberProjectUEN.java13springTU.onlinecinemaproject.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FilmRateInfoDTO
        extends GenericDTO {

    private LocalDateTime rateDate;
    private LocalDateTime returnDate;
    private Boolean returned;
    private Integer rate;
    private Long filmId;
    private Long userId;
    private FilmDTO filmDTO;

}

