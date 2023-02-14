package com.SberProjectUEN.java13springTU.libraryproject.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "films")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "films_seq", allocationSize = 1)
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@json_id")
public class Film
      extends GenericModel {
    
    @Column(name = "title", nullable = false)
    private String filmTitle;
    
    @Column(name = "premier_year", nullable = false)
    private LocalDate premierDate;

    @Column(name = "country", nullable = false)
    private String country;
    
    @Column(name = "genre", nullable = false)
    @Enumerated
    private Genre genre;
    
    @ManyToMany
    @JoinTable(name = "filmsDirectors",
               joinColumns = @JoinColumn(name = "film_id"), foreignKey = @ForeignKey(name = "FK_FILMS_DIRECTORS"),
               inverseJoinColumns = @JoinColumn(name = "director_id"), inverseForeignKey = @ForeignKey(name = "FK_DIRECTORS_FILMS"))
    //@JsonBackReference
    private Set<Director> directors;
    
    @OneToMany(mappedBy = "film")
    private Set<FilmRentInfo> filmRentInfos;
}