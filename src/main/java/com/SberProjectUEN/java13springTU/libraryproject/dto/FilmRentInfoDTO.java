package com.SberProjectUEN.java13springTU.libraryproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmRentInfoDTO
        extends GenericDTO {

    //    private BookDTO book;
//    private UserDTO user;
    private Long filmId;
    private Long userId;
    private LocalDateTime rentDate;
    private LocalDateTime rentPeriod;
    private Boolean purchase;

}
