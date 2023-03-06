package com.SberProjectUEN.java13springTU.libraryproject.service;


import com.SberProjectUEN.java13springTU.libraryproject.dto.DirectorDTO;
import com.SberProjectUEN.java13springTU.libraryproject.mapper.DirectorMapper;
import com.SberProjectUEN.java13springTU.libraryproject.model.Director;
import com.SberProjectUEN.java13springTU.libraryproject.repository.DirectorRepository;
import org.springframework.stereotype.Service;

@Service
public class DirectorService
        extends GenericService<Director, DirectorDTO> {
    protected DirectorService(DirectorRepository directorRepository,
                              DirectorMapper directorMapper) {
        super(directorRepository, directorMapper);
    }
}

