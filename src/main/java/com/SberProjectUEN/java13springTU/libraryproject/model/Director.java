package com.SberProjectUEN.java13springTU.libraryproject.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@Entity
@Table(name = "directors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "directors_seq", allocationSize = 1)
//@Where(clause = "is_deleted = false")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
//@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@json_id")
public class Director
        extends GenericModel {
    @Column(name = "directors_fio", nullable = false)
    private String directorsFio;

    @Column(name = "position")
    private String position;

//    @ManyToMany(mappedBy = "directors")
//    private Set<Film> films;

    //чтобы не было главной/не главной таблицы
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    //@JsonIgnore //убирает рекурсию
    //@JsonManagedReference //убирает рекурсию в связке с JsonBackReference, но не будет работать десериализация
    @JoinTable(
            name = "films_directors",
            joinColumns = @JoinColumn(name = "director_id"), foreignKey = @ForeignKey(name = "FK_DIRECTORS_FILMS"),
            inverseJoinColumns = @JoinColumn(name = "film_id"), inverseForeignKey = @ForeignKey(name = "FK_FILMS_DIRECTORS"))
    private Set<Film> films;
}
