package com.SberProjectUEN.java13springTU.onlinecinemaproject.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "orders_seq", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class FilmRentInfo
        extends GenericModel {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "film_id", foreignKey = @ForeignKey(name = "FK_ORDERS_FILM"))
    private Film film;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_ORDERS_USER"))
    private User user;

    @Column(name = "rent_date", nullable = false)
    private LocalDateTime rentDate;
    //поле автоматически должно рассчитываться из rent_date + rent_period
    @Column(name = "return_date", nullable = false)
    private LocalDateTime returnDate;
    @Column(name = "returned", nullable = false)
    private Boolean returned;
    @Column(name = "purchase")
    private Boolean purchase;
    @Column(name = "rentPeriod", nullable = false)
    private Integer rentPeriod;
    //rent_period - количество дней аренды, если не указано, то по-умолчанию - 14 дней
}
