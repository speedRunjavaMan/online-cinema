package com.SberProjectUEN.java13springTU.libraryproject.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "orders_seq", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class FilmRentInfo
        extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "orders_id", foreignKey = @ForeignKey(name = "FK_ORDERS_FILM"))
    private Film film;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_ORDERS_USER"))
    private User user;

    @Column(name = "rentDate", nullable = false)
    private LocalDateTime rentDate;
    //поле автоматически должно рассчитываться из rent_date + rent_period
    @Column(name = "rentPeriod", nullable = false)
    private LocalDateTime rentPeriod;
    //rent_period - количество дней аренды, если не указано, то по-умолчанию - 14 дней
    @Column(name = "purchase")
    private Boolean purchase;


}