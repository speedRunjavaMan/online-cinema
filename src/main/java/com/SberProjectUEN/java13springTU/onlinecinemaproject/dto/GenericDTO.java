package com.SberProjectUEN.java13springTU.onlinecinemaproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public abstract class GenericDTO {
    
    private Long id;
    private String createdBy;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createdWhen;
//  private boolean isDeleted;
//  private LocalDateTime deletedWhen;
//  private String deletedBy;
}
