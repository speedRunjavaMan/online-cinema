package com.SberProjectUEN.java13springTU.dbexample.model;

import com.SberProjectUEN.java13springTU.libraryproject.model.GenericModel;
import lombok.*;

import java.util.Date;
//POJO - Plain Old Java Object
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book extends GenericModel {
    @Setter(AccessLevel.NONE)
    //@Getter(AccessLevel.NONE)
    private Integer filmId;
    private String filmTitle;
    private String filmAuthor;
    private Date dateAdded;
}
