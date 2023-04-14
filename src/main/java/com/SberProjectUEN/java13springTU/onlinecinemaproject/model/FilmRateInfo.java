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
@Table(name = "rates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "rates_seq", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class FilmRateInfo
        extends GenericModel {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "film_id", foreignKey = @ForeignKey(name = "FK_RATES_FILM"))
    private Film film;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_RATES_USER"))
    private User user;

    @Column(name = "rate_date", nullable = false)
    private LocalDateTime rateDate;
    //поле автоматически должно рассчитываться из rate_date + rate_period
    @Column(name = "return_date", nullable = false)
    private LocalDateTime returnDate;
    @Column(name = "returned", nullable = false)
    private Boolean returned;
    @Column(name = "rate", nullable = false)
    private Integer rate;
}

