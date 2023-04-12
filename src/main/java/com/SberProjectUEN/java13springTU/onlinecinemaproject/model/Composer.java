package com.SberProjectUEN.java13springTU.onlinecinemaproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@Entity
@Table(name = "composers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "composers_seq", allocationSize = 1)
//@Where(clause = "is_deleted = false")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
//@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@json_id")
public class Composer
        extends GenericModel {
    @Column(name = "composers_fio", nullable = false)
    private String composersFio;

    @Column(name = "position")
    private String position;

//    @ManyToMany(mappedBy = "directors")
//    private Set<Film> films;

    //чтобы не было главной/не главной таблицы
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    //@JsonIgnore //убирает рекурсию
    //@JsonManagedReference //убирает рекурсию в связке с JsonBackReference, но не будет работать десериализация
    @JoinTable(
            name = "films_composers",
            joinColumns = @JoinColumn(name = "composer_id"), foreignKey = @ForeignKey(name = "FK_COMPOSERS_FILMS"),
            inverseJoinColumns = @JoinColumn(name = "film_id"), inverseForeignKey = @ForeignKey(name = "FK_FILMS_COMPOSERS"))
    private Set<Film> films;
}
