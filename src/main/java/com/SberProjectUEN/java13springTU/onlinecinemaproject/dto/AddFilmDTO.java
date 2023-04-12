package com.SberProjectUEN.java13springTU.onlinecinemaproject.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class AddFilmDTO {
    Long filmId;
    Long directorId;
    Long composerId;
}
