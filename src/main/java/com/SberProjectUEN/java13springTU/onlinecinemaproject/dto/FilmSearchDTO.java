package com.SberProjectUEN.java13springTU.onlinecinemaproject.dto;

import com.SberProjectUEN.java13springTU.onlinecinemaproject.model.Genre;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class FilmSearchDTO {
    private String filmTitle;
    private String directorsFio;
    private String composersFio;
    private Genre genre;
}

