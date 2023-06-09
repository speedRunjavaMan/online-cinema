package com.SberProjectUEN.java13springTU.onlinecinemaproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "films_seq", allocationSize = 1)
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
//@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@json_id")
public class Film
        extends GenericModel {

    @Column(name = "title", nullable = false)
    private String filmTitle;

    @Column(name = "premier_year", nullable = false)
    private LocalDate premierYear;

    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "online_copy_path")
    private String onlineCopyPath;
    @Column(name = "average_grade")
    private Double averageGrade;
    @Column(name = "amount_grades")
    private Integer amountGrades;
    @Column(name = "genre", nullable = false)
    @Enumerated
    private Genre genre;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY)
    @JoinTable(name = "films_directors",
            joinColumns = @JoinColumn(name = "film_id"), foreignKey = @ForeignKey(name = "FK_FILMS_DIRECTORS"),
            inverseJoinColumns = @JoinColumn(name = "director_id"), inverseForeignKey = @ForeignKey(name = "FK_DIRECTORS_FILMS"))
    //@JsonBackReference
    private Set<Director> directors;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY)
    @JoinTable(name = "films_composers",
            joinColumns = @JoinColumn(name = "film_id"), foreignKey = @ForeignKey(name = "FK_FILMS_COMPOSERS"),
            inverseJoinColumns = @JoinColumn(name = "composer_id"), inverseForeignKey = @ForeignKey(name = "FK_COMPOSERS_FILMS"))
    //@JsonBackReference
    private Set<Composer> composers;
    @OneToMany(mappedBy = "film", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<FilmRentInfo> filmRentInfos;
}

