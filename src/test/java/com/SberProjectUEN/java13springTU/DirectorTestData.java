package com.SberProjectUEN.java13springTU;



import com.SberProjectUEN.java13springTU.libraryproject.dto.DirectorDTO;
import com.SberProjectUEN.java13springTU.libraryproject.model.Director;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public interface DirectorTestData {
    DirectorDTO DIRECTOR_DTO_1 = new DirectorDTO(
            "directorsFio1",
            "position1",
            new HashSet<>(),
            false);

    DirectorDTO DIRECTOR_DTO_2 = new DirectorDTO(
            "directorsFio2",
            "position2",
            new HashSet<>(),
            false);

    DirectorDTO DIRECTOR_DTO_3_DELETED = new DirectorDTO(
            "directorsFio3",
            "position3",
            new HashSet<>(),
            true);

    List<DirectorDTO> DIRECTOR_DTO_LIST = Arrays.asList(DIRECTOR_DTO_1, DIRECTOR_DTO_2, DIRECTOR_DTO_3_DELETED);


    Director DIRECTOR_1 = new Director(
            "directorsFio1",
            "position1",
            null);

    Director DIRECTOR_2 = new Director(
            "directorsFio2",
            "position2",
            null);

    Director DIRECTOR_3 = new Director(
            "directorsFio2",
            "position3",
            null);

    List<Director> DIRECTOR_LIST = Arrays.asList(DIRECTOR_1, DIRECTOR_2, DIRECTOR_3);
}