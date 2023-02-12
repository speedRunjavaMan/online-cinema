package com.SberProjectUEN.java13springTU.libraryproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
//https://ondras.zarovi.cz/sql/demo/?keyword=default - онлайн рисовалка диаграмм
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class GenericModel implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1;
    
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Long id;
    
    @Column(name = "created_when")
    private LocalDateTime createdWhen;
    
    @Column(name = "created_by")
    private String createdBy;
}
