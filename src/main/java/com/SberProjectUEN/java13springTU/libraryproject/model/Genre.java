package com.SberProjectUEN.java13springTU.libraryproject.model;

public enum Genre {
    FANTASY("Фантастика"),
    HORROR("Ужасы"),
    DRAMA("Драма"),
    COMEDY("Комедия");
    
    private final String genreTextDisplay;
    
    Genre(String text) {
        this.genreTextDisplay = text;
    }
    
    public String getGenreTextDisplay() {
        return this.genreTextDisplay;
    }
}
